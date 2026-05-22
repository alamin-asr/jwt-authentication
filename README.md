# JWT Authentication

A Spring Boot application for JWT-based user authentication and authorization with Spring Security.

## Features

- User registration and login
- JWT token generation and validation
- Secure password hashing with BCrypt
- Role-based access control
- Protected API endpoints
- Global exception handling

## Tech Stack

- Java 17
- Spring Boot 3.4.5
- Spring Security 3.4.2
- Spring Data JPA
- PostgreSQL
- Auth0 Java JWT 4.4.0
- Maven

## Prerequisites

- JDK 17+
- PostgreSQL 12+
- Maven 3.8.0+

## Setup

### 1. Clone Repository

```bash
git clone https://github.com/alamin-asr/jwt-authentication.git
cd jwt-authentication
```

### 2. Create Database

```sql
CREATE DATABASE jwt_auth_db;
CREATE USER jwt_user WITH PASSWORD 'jwt_password';
GRANT ALL PRIVILEGES ON DATABASE jwt_auth_db TO jwt_user;
```

### 3. Configure Environment

Create `.env` file in project root:

```env
JWT_SECRET=your-secret-key-min-32-characters
JWT_EXPIRATION=86400000

DB_URL=jdbc:postgresql://localhost:5432/jwt_auth_db
DB_USERNAME=jwt_user
DB_PASSWORD=jwt_password

SERVER_PORT=8080
```

### 4. Update application.properties

Edit `src/main/resources/application.properties`:

```properties
server.port=${SERVER_PORT:8080}

spring.datasource.url=${DB_URL:jdbc:postgresql://localhost:5432/jwt_auth_db}
spring.datasource.username=${DB_USERNAME:jwt_user}
spring.datasource.password=${DB_PASSWORD:jwt_password}
spring.datasource.driver-class-name=org.postgresql.Driver

spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=false

jwt.secret=${JWT_SECRET}
jwt.expiration=${JWT_EXPIRATION:86400000}
```

## Running the Application

```bash
# Using Maven wrapper
./mvnw spring-boot:run

# Or using Maven
mvn spring-boot:run
```

The app will start on `http://localhost:8080`

## API Endpoints

### Sign Up
```http
POST /api/v1/auth/signup
Content-Type: application/json

{
  "email": "user@example.com",
  "password": "password123"
}
```

### Sign In
```http
POST /api/v1/auth/signin
Content-Type: application/json

{
  "login": "user@example.com",
  "password": "password123"
}
```

**Response:**
```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
}
```

## Usage Examples

### Using cURL

```bash
# Sign up
curl -X POST http://localhost:8080/api/v1/auth/signup \
  -H "Content-Type: application/json" \
  -d '{"email":"test@example.com","password":"test123"}'

# Sign in
curl -X POST http://localhost:8080/api/v1/auth/signin \
  -H "Content-Type: application/json" \
  -d '{"login":"test@example.com","password":"test123"}'
```

## Security

- Passwords hashed with BCrypt
- Stateless JWT authentication
- CSRF protection disabled for API
- Input validation
- SQL injection prevention via JPA

## Build

```bash
mvn clean package
java -jar target/spring_auth-0.0.1-SNAPSHOT.jar
```

## License

MIT License

## Author

**Alamin** - [@alamin-asr](https://github.com/alamin-asr)
