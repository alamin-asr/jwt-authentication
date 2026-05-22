# JWT Authentication

This project provides a simple implementation of JWT (JSON Web Token) authentication in a Node.js application. It demonstrates how to secure routes using JWTs, manage user login and registration, and validate tokens on protected endpoints.

## Features
- User registration and login
- Password hashing with bcrypt
- Issuing and verifying JWT tokens
- Middleware for protecting routes
- Example protected and unprotected routes

## Technologies Used
- Node.js
- Express
- bcryptjs
- jsonwebtoken

## Getting Started

### Prerequisites
- Node.js (v14 or higher)
- npm

### Installation
1. Clone the repository:
   ```sh
   git clone https://github.com/alamin-asr/jwt-authentication.git
   cd jwt-authentication
   ```
2. Install dependencies:
   ```sh
   npm install
   ```

### Configuration
Create a `.env` file in the root directory and add:
```env
PORT=3000
JWT_SECRET=your_secret_key
```

### Running the App
```sh
npm start
```

The server will run on the port specified in your `.env` file.

## Example Endpoints
- `POST /register` — Register a new user
- `POST /login` — Log in and receive a JWT
- `GET /protected` — Example of a protected route (requires JWT)

Use tools like Postman to interact with the endpoints. Attach the token in the `Authorization` header as `Bearer <token>` for protected routes.

## License
MIT
