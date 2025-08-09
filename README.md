# TaskKeeper API
A simple **backend-only RESTful API** for managing personal tasks with JWT-based authentication.  
Built with **Node.js**, **Express.js**, **MongoDB (Mongoose)**, and **Zod** for validation.

---

## Features
- User authentication (JWT)
- CRUD operations for tasks
- Pagination for listing tasks
- Per-user data isolation
- Input validation with Zod
- Centralized error handling

---

## Tech Stack
- **Node.js** + **Express.js** (server & routing)
- **MongoDB** + **Mongoose** (database & models)
- **jsonwebtoken** (authentication)
- **bcrypt** (password hashing)
- **Zod** (request validation)

---

## Setup

```bash
npm install
```

## API Endpoints

All **`/tasks`** routes require a valid **JWT token** in the `Authorization: Bearer <token>` header.

---

### **Auth Routes**
| Method | Endpoint       | Description           | Auth Required |
|--------|---------------|-----------------------|--------------|
| POST   | `/auth/register` | Register a new user   | ❌ |
| POST   | `/auth/login`    | Log in and get JWT    | ❌ |

---

### **Task Routes**
| Method | Endpoint        | Description |
|--------|----------------|-------------|
| GET    | `/tasks`       | List all user tasks (paginated). Query params: `page` (default 1), `limit` (default 3) |
| POST   | `/tasks`       | Create a new task (validated with Zod) |
| GET    | `/tasks/:id`   | Get specific task details *(owner only)* |
| PUT    | `/tasks/:id`   | Update a task *(validated with Zod, owner only)* |
| DELETE | `/tasks/:id`   | Delete a specific task *(owner only)* |
| DELETE | `/tasks`       | Delete all tasks for the current user |

---