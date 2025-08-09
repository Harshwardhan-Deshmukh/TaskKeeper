const zod = require("zod");

const userRegistrationSchema = zod.object({
    username: zod.string().nonempty().nonoptional(),
    email: zod.email().nonempty().nonoptional(),
    password: zod.string().nonempty().nonoptional(),
})

const userLoginSchema = zod.object({
    username: zod.string().nonempty().nonoptional(),
    password: zod.string().nonempty().nonoptional(),
})

module.exports = {
    userRegistrationSchema,
    userLoginSchema
};