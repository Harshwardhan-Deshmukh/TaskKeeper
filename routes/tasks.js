const { Router } = require("express");
const router = Router();
const authMiddleware = require("../middlewares/auth");
const Task = require("../models/Task");
const { createTaskSchema } = require("../validations/taskSchema");

// list all the user tasks (with pagination)
router.get("/", authMiddleware, async (req, res) => {
  try {
    let { page = 1, limit = 3 } = req.query;
    page = parseInt(page);
    limit = parseInt(limit);

    const skip = (page - 1) * limit;

    const userTasks = await Task.find({ userId: req.userId })
      .skip(skip)
      .limit(limit)
      .sort({ dueDate: 1 });

    const totalTasks = await Task.countDocuments({ userId: req.userId });

    res.status(200).json({
      success: true,
      message: `Fetched tasks for user ${req.username}`,
      pagination: {
        total: totalTasks,
        page,
        limit,
        totalPages: Math.ceil(totalTasks / limit),
      },
      tasks: userTasks.map((task) => ({
        id: task._id,
        title: task.title,
      })),
    });
  } catch (err) {
    res.status(500).json({ success: false, message: err.message });
  }
});

// create a new task
router.post("/", authMiddleware, async (req, res) => {
  const data = createTaskSchema.safeParse(req.body);

  if (data.success) {
    const newTask = await Task.create({
      title: data.data.title,
      description: data.data.description || "",
      dueDate: data.data.dueDate || null,
      completed: data.data.completed,
      userId: req.userId,
    });
    return res.status(201).json({
      success: true,
      message: "Task Created Successfully",
      task: newTask,
    });
  } else {
    return res.status(400).json({
      success: false,
      errors: JSON.parse(data.error.message),
    });
  }
});

// get details of a specific task
router.get("/:id", authMiddleware, async (req, res) => {
  const task = await Task.findById(req.params.id);
  return res.status(200).json({
    success: true,
    message: "Fetched Task Successfully",
    task,
  });
});

// update a specific task
router.put("/:id", authMiddleware, async (req, res) => {
  try {
    const { id } = req.params;

    const updateSchema = createTaskSchema.partial();
    const parseResult = updateSchema.safeParse(req.body);

    if (!parseResult.success) {
      return res.status(400).json({
        success: false,
        errors: JSON.parse(parseResult.error.message),
      });
    }

    const updatedTask = await Task.findOneAndUpdate(
      { _id: id, userId: req.userId },
      parseResult.data,
      { new: true }
    );

    if (!updatedTask) {
      return res.status(404).json({
        success: false,
        message: "Task not found or not authorized",
      });
    }

    return res.status(200).json({
      success: true,
      message: "Task Updated Successfully",
      task: updatedTask,
    });
  } catch (err) {
    res.status(500).json({
      success: false,
      message: err.message,
    });
  }
});

// delete a specific task
router.delete("/:id", authMiddleware, async (req, res) => {
  await Task.deleteOne({ _id: req.params.id, userId: req.userId });
  return res.status(200).json({
    success: true,
    message: "Task Deleted Successfully",
  });
});

// delete all the user tasks
router.delete("/", authMiddleware, async (req, res) => {
  const result = await Task.deleteMany({ userId: req.userId });
  return res.status(200).json({
    success: true,
    message: `${result.deletedCount} Tasks Deleted Successfully`,
  });
});

module.exports = router;
