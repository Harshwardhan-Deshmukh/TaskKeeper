const express = require("express");
const { PORT, MONGODB_CONNECTION_STRING } = require("./configs/app.configs");
const morgan = require("morgan");
const taskRoutes = require("./routes/tasks");
const authRoutes = require("./routes/auth");
const mongoose = require("mongoose");

const app = express();
const port = PORT;

app.use(morgan("dev"));
app.use(express.json());
app.use("/tasks", taskRoutes);
app.use("/auth", authRoutes);

mongoose
  .connect(MONGODB_CONNECTION_STRING)
  .then(() => console.log("Connected to MongoDB"))
  .catch((err) =>
    console.log("Failed to Connected to MongoDB with error " + err.message)
  );

app.get("/api/health", (req, res) => {
  return res.status(200).json({
    status: "Healthy",
  });
});

app.use((err, req, res, next) => {
  return res.status(err.status || 500).json({
    success: false,
    message: err.message || "Internal Server Error",
  });
});

app.listen(PORT, () => {
  console.log(`Listening on Port ${PORT}`);
});
