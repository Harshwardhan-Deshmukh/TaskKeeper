const { Router } = require("express");
const {
  userRegistrationSchema,
  userLoginSchema,
} = require("../validations/userSchema");
const User = require("../models/User");
const { JWT_SECRET } = require("../configs/app.configs");
const {
  generatePasswordHash,
  compareHashedPasswords,
} = require("../utils/password.hash");
const jwt = require("jsonwebtoken");
const router = Router();

// signup a user
router.post("/register", async (req, res) => {
  const { username, email, password } = req.body;
  const data = userRegistrationSchema.safeParse({
    username,
    email,
    password,
  });

  if (data.success) {
    const user = await User.create({
      username: data.data.username,
      email: data.data.email,
      password: await generatePasswordHash(data.data.password),
    });

    return res.status(201).json({
      success: true,
      userId: user._id,
      message: "User created successfully",
    });
  } else {
    return res.status(400).json({
      success: false,
      errors: JSON.parse(data.error.message),
    });
  }
});

// signin a user
router.post("/login", async (req, res) => {
  const { username, password } = req.body;
  const data = userLoginSchema.safeParse({
    username,
    password,
  });

  if (data.success) {
    const user = await User.find({
      username: data.data.username,
    });
    const comparePasswordResult = await compareHashedPasswords(
      user[0].password,
      data.data.password
    );
    if (comparePasswordResult) {
      const token = jwt.sign(
        {
          success: true,
          userId: user[0]._id,
          username: user[0].username,
        },
        JWT_SECRET,
        {expiresIn : "1h"}
      );

      return res.status(200).json({
        success: true,
        token,
        message: "Login Success",
      });
    } else {
      return res.status(401).json({
        success: false,
        message: "Invalid Credentials",
      });
    }
  } else {
    return res.status(400).json({
      success: false,
      errors: JSON.parse(data.error.message),
    });
  }
});

module.exports = router;
