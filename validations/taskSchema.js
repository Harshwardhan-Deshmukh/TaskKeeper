const zod = require("zod");

const createTaskSchema = zod.object({
  title: zod.string(),
  description: zod.string().optional(),
  dueDate: zod.coerce.date().optional(),
  completed: zod.boolean().optional(),
});

module.exports = {
  createTaskSchema,
};
