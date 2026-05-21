Please redirect to "https://github.com/shivam-616/expense_tracker.git" this only has the Authentication service

# Authentication Service (Expense Tracker)

A Spring Boot-based authentication and authorization microservice designed for the Expense Tracker application. This service handles user registration, secure login, stateless session management using JSON Web Tokens (JWT), and publishes user-related events to an Apache Kafka messaging queue.

## 🚀 Features

* **User Registration & Login:** Secure endpoints for creating new users and authenticating existing ones.
* **JWT-Based Authorization:** Implements stateless security using short-lived Access Tokens.
* **Refresh Token Mechanism:** Includes a database-backed refresh token flow to securely generate new access tokens without requiring the user to log in again.
* **Spring Security Integration:** Custom security filter chains, custom user details, and password encoding (BCrypt).
* **Event-Driven Architecture:** Includes an integrated Kafka producer (`UserInfoProducer`) designed to broadcast user creation events to other microservices in the system.

## 🛠️ Tech Stack

* **Framework:** Java 17, Spring Boot 3.5.x
* **Security:** Spring Security, JWT (`io.jsonwebtoken`)
* **Database & ORM:** Spring Data JPA (Hibernate)
* **Messaging:** Apache Kafka (`spring-kafka`)
* **Build Tool:** Maven

## 🏗️ Architecture Overview

1. **Authentication Flow:** Users authenticate via the `/auth/v1/login` endpoint using their credentials. The `AuthenticationManager` verifies these against the database.
2. **Token Generation:** Upon successful login or signup, the `JwtService` issues a JWT Access Token (valid for 1 minute in the current configuration) and the `RefreshTokenService` issues a UUID-based Refresh Token (valid for 10 minutes) saved in the database.
3. **Protected Routes:** The `JwtAuthFilter` intercepts incoming requests, validates the Bearer token, and populates the `SecurityContext` for downstream microservices or protected endpoints.
4. **Kafka Publishing:** Upon user registration, a `UserInfoEvent` can be serialized using the custom `UserInfoSerializer` and broadcasted to a configured Kafka topic to notify other services (like the core Expense service) of a new user.

## 📡 API Endpoints

### 1. User Signup
**Endpoint:** `POST /auth/v1/signup`
Creates a new user and returns access and refresh tokens.

**Request Body:**
```json
{
  "username": "johndoe",
  "password": "securepassword123",
  "firstName": "John",
  "lastName": "Doe",
  "email": "john@example.com",
  "phoneNumber": 1234567890
}
