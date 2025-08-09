const mongoose = require("mongoose");

const UserSchema = new mongoose.Schema({
  username: { type: String, unique: true, index: true, required: true },
  email: { type: String, unique: true, required: true },
  password: { type: String, unique: true, required: true },
});

const User = mongoose.model("Users", UserSchema);

module.exports = User;