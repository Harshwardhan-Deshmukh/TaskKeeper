const jwt = require("jsonwebtoken");
const { JWT_SECRET } = require("../configs/app.configs");

async function authMiddleware(req, res, next) {
  const authorization = req.headers.authorization;
  const token = authorization.split(" ")[1];

  try {
    const decodedUserInfo = jwt.verify(token, JWT_SECRET);
    req.userId = decodedUserInfo.userId;
    req.username = decodedUserInfo.username;
    next();
  } catch (err) {
    const authError = new Error(err.message);
    authError.status = 401;
    next(authError);
  }
  
}

module.exports = authMiddleware;
