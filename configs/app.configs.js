require("dotenv").config({ quiet: true });

const MONGODB_CONNECTION_STRING = process.env.MONGODB_CONNECTION_STRING;
const PORT = process.env.PORT || 3000;
const JWT_SECRET = process.env.JWT_SECRET;

module.exports = {
  MONGODB_CONNECTION_STRING,
  PORT,
  JWT_SECRET,
};
