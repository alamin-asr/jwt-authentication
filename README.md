# JWT Authentication

A Spring Boot application demonstrating JWT (JSON Web Token) based authentication and authorization. This project provides a complete implementation of user authentication with secure password handling, token generation, and route protection.

## 📋 Table of Contents

- [Features](#features)
- [Technologies](#technologies)
- [Project Structure](#project-structure)
- [Prerequisites](#prerequisites)
- [Installation & Setup](#installation--setup)
- [Configuration](#configuration)
- [Running the Application](#running-the-application)
- [API Endpoints](#api-endpoints)
- [Usage Examples](#usage-examples)
- [Security Features](#security-features)
- [License](#license)

## ✨ Features

- **User Registration** - Create new user accounts with email validation
- **User Authentication** - Secure login with email and password
- **JWT Token Generation** - Generate secure JWT tokens with configurable expiration
- **Token Verification** - Validate and verify JWT tokens
- **Protected Routes** - Middleware to protect endpoints requiring authentication
- **Password Security** - Password hashing and validation
- **Role-Based Access Control** - Support for different user roles
- **Spring Security Integration** - Built on Spring Security framework
- **PostgreSQL Database** - Persistent user data storage

## 🛠️ Technologies

- **Java 17** - Programming language
- **Spring Boot 3.4.5** - Web framework
- **Spring Security 3.4.2** - Authentication & authorization
- **Spring Data JPA 3.4.3** - Data persistence
- **PostgreSQL** - Database
- **Auth0 Java JWT 4.4.0** - JWT library
- **Lombok** - Code generation library
- **Maven** - Build tool

## 📁 Project Structure

```
jwt-authentication/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/alamin/
│   │   │       ├── config/          # Configuration classes
│   │   │       ├── controller/      # REST endpoints
│   │   │       ├── dto/             # Data transfer objects
│   │   │       ├── entity/          # JPA entities
│   │   │       ├── repository/      # Database repositories
│   │   │       ├── security/        # Security-related classes
│   │   │       ├── service/         # Business logic
│   │   │       └── Application.java # Main application class
│   │   └── resources/
│   │       ├── application.properties
│   │       └── application-*.properties
│   └── test/                        # Test classes
├── .env                             # Environment variables
├── .gitignore
├── .mvn/                            # Maven wrapper
├── mvnw / mvnw.cmd                  # Maven wrapper scripts
├── pom.xml                          # Maven dependencies
└── README.md
```

## 📦 Prerequisites

- **Java Development Kit (JDK)** - Version 17 or higher
- **Apache Maven** - Version 3.8.0 or higher (or use included Maven wrapper)
- **PostgreSQL** - Version 12 or higher
- **Git** - For cloning the repository

## 🚀 Installation & Setup

### 1. Clone the Repository

```bash
git clone https://github.com/alamin-asr/jwt-authentication.git
cd jwt-authentication
```

### 2. Create PostgreSQL Database

```sql
CREATE DATABASE jwt_auth_db;
CREATE USER jwt_user WITH PASSWORD 'jwt_password';
ALTER ROLE jwt_user SET client_encoding TO 'utf8';
ALTER ROLE jwt_user SET default_transaction_isolation TO 'read committed';
ALTER ROLE jwt_user SET default_transaction_deferrable TO on;
GRANT ALL PRIVILEGES ON DATABASE jwt_auth_db TO jwt_user;
```

### 3. Configure Environment Variables

Create a `.env` file in the project root:

```env
JWT_SECRET="your-very-secret-key-min-32-characters"
JWT_EXPIRATION=86400000

DB_URL=jdbc:postgresql://localhost:5432/jwt_auth_db
DB_USERNAME=jwt_user
DB_PASSWORD=jwt_password

SERVER_PORT=8080
```

### 4. Update Application Properties

Edit `src/main/resources/application.properties`:

```properties
# Server Configuration
server.port=${SERVER_PORT:8080}

# Database Configuration
spring.datasource.url=${DB_URL:jdbc:postgresql://localhost:5432/jwt_auth_db}
spring.datasource.username=${DB_USERNAME:jwt_user}
spring.datasource.password=${DB_PASSWORD:jwt_password}
spring.datasource.driver-class-name=org.postgresql.Driver

# JPA/Hibernate Configuration
spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=false
spring.jpa.properties.hibernate.format_sql=true

# JWT Configuration
jwt.secret=${JWT_SECRET}
jwt.expiration=${JWT_EXPIRATION:86400000}
```

## ⚙️ Configuration

### JWT Configuration

The JWT configuration can be customized through environment variables:

| Variable | Description | Default |
|----------|-------------|---------|
| `JWT_SECRET` | Secret key for signing tokens (min 32 chars) | `yoursecret` |
| `JWT_EXPIRATION` | Token expiration time in milliseconds | `86400000` (24 hours) |

### Security Configuration

Spring Security is configured to:
- Allow unauthenticated access to `/register` and `/login` endpoints
- Require authentication for all other endpoints
- Use JWT bearer tokens in the `Authorization` header

## ▶️ Running the Application

### Using Maven Wrapper (Recommended)

```bash
# Linux/Mac
./mvnw spring-boot:run

# Windows
mvnw.cmd spring-boot:run
```

### Using Maven

```bash
mvn spring-boot:run
```

### Build and Run JAR

```bash
mvn clean package
java -jar target/spring_auth-0.0.1-SNAPSHOT.jar
```

The application will start on `http://localhost:8080`

## 🔌 API Endpoints

### Authentication Endpoints

#### Register User
```http
POST /api/auth/register
Content-Type: application/json

{
  "email": "user@example.com",
  "password": "securePassword123",
  "firstName": "John",
  "lastName": "Doe"
}
```

**Response (201):**
```json
{
  "id": 1,
  "email": "user@example.com",
  "firstName": "John",
  "lastName": "Doe",
  "createdAt": "2026-05-22T10:30:00Z"
}
```

#### Login User
```http
POST /api/auth/login
Content-Type: application/json

{
  "email": "user@example.com",
  "password": "securePassword123"
}
```

**Response (200):**
```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
  "type": "Bearer",
  "expiresIn": 86400000,
  "user": {
    "id": 1,
    "email": "user@example.com",
    "firstName": "John",
    "lastName": "Doe"
  }
}
```

### Protected Endpoints

#### Get User Profile
```http
GET /api/users/profile
Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...
```

**Response (200):**
```json
{
  "id": 1,
  "email": "user@example.com",
  "firstName": "John",
  "lastName": "Doe",
  "createdAt": "2026-05-22T10:30:00Z"
}
```

## 💡 Usage Examples

### Using cURL

#### Register
```bash
curl -X POST http://localhost:8080/api/auth/register \
  -H "Content-Type: application/json" \
  -d '{
    "email": "test@example.com",
    "password": "Test@1234",
    "firstName": "Test",
    "lastName": "User"
  }'
```

#### Login
```bash
curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{
    "email": "test@example.com",
    "password": "Test@1234"
  }'
```

#### Access Protected Route
```bash
curl -X GET http://localhost:8080/api/users/profile \
  -H "Authorization: Bearer YOUR_JWT_TOKEN_HERE"
```

### Using Postman

1. **Register**: POST to `http://localhost:8080/api/auth/register` with user details
2. **Login**: POST to `http://localhost:8080/api/auth/login` - Copy the token from response
3. **Protected Request**: GET to `http://localhost:8080/api/users/profile`
   - Add header: `Authorization: Bearer <token>`

## 🔒 Security Features

- **Password Hashing**: Passwords are hashed using bcrypt with configurable strength
- **JWT Tokens**: Stateless authentication with signed JWT tokens
- **Token Expiration**: Tokens expire after configurable duration
- **CORS Protection**: Configured to prevent unauthorized cross-origin requests
- **Spring Security**: Integrated with Spring Security framework for robust protection
- **Input Validation**: Request validation using annotations
- **SQL Injection Prevention**: Uses parameterized queries via JPA

## 🧪 Testing

Run the test suite:

```bash
mvn test
```

## 📝 License

This project is licensed under the MIT License - see the LICENSE file for details.

## 👤 Author

**Alamin**
- GitHub: [@alamin-asr](https://github.com/alamin-asr)

## 🤝 Contributing

Contributions are welcome! Please follow these steps:

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## 📧 Support

For support, email alamin@example.com or open an issue on GitHub.

---

**Last Updated**: May 2026
