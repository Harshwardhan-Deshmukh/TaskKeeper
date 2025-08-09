const { Router } = require("express");
const router = Router();

// list all the user tasks (filterable & paginated route)
router.get("/tasks", (req, res) => {});

// create a new task
router.post("/tasks", (req, res) => {});

// get details of a specific task
router.get("/tasks/:id", (req, res) => {});

// update a specific task
router.put("/tasks/:id", (req, res) => {});

// delete a specific task
router.delete("/tasks/:id", (req, res) => {});

// delete all the tasks
router.delete("/tasks", (req, res) => {});

module.exports = router;
