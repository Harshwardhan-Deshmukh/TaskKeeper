const mongoose = require("mongoose");
const User = require("./User");

const TaskSchema = new mongoose.Schema({
  title: { type: String, unique: true, index: true, required: true },
  description: { type: String },
  dueDate: { type: Date },
  createdAt: { type: Date, default: Date.now },
  completed: Boolean,
  userId: {
    type: mongoose.Schema.Types.ObjectId,
    ref: User,
  },
});

const Task = mongoose.model("Tasks", TaskSchema);

module.exports = Task;
