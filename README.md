# Candidate Management API

![Java](https://img.shields.io/badge/Java-25-orange)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5-brightgreen)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-Database-blue)
![Liquibase](https://img.shields.io/badge/Liquibase-Migration-yellow)
![Docker](https://img.shields.io/badge/Docker-Ready-2496ED)
![Swagger](https://img.shields.io/badge/OpenAPI-Swagger-85EA2D)

A RESTful API for managing candidate information and interview schedules built with Spring Boot.

This project demonstrates modern backend development practices using REST APIs, JPA Specification for dynamic queries, Liquibase for database versioning, Docker for containerization, Swagger/OpenAPI for API documentation, and JUnit with Mockito for unit testing.

---

# Features

## Candidate Management

- Create Candidate
- Retrieve Candidate Details
- Retrieve Candidate List
- Update Candidate Information
- Delete Candidate
- Update Candidate Status

## Interview Schedule

- Create Interview Schedule
- Retrieve Interview Schedule

## API Features

- Search Candidates
- Filter by Status
- Pagination
- Dynamic Sorting
- Bean Validation
- Global Exception Handling
- Standardized API Response
- OpenAPI Documentation

---

# Tech Stack

- Java 25
- Spring Boot 3.5
- Spring Data JPA
- PostgreSQL
- Liquibase
- Spring Validation
- Swagger / OpenAPI
- Maven
- Docker
- JUnit 5
- Mockito

---

# Project Structure

```text
src
├── controller
├── service
├── repository
├── entity
├── dto
├── config
├── exception
├── resources
│   ├── db
│   │   └── changelog
│   └── application.yml
└── test
```

---

# Quick Start (Docker)

Clone the repository:

```bash
git clone https://github.com/parapai/candidate-app.git
```

Navigate to the project directory:

```bash
cd candidate-app
```

Build and start the application:

```bash
docker compose up --build -d
```

Verify that the containers are running:

```bash
docker ps
```

Docker Compose will start:

- Candidate Management API
- PostgreSQL Database

When the application starts for the first time, Liquibase automatically executes all database migrations, so no manual SQL setup is required.

Once the containers are running successfully, the API is ready to use.

### Swagger UI

```
http://localhost:9021/swagger-ui/index.html
```

### OpenAPI Specification

```
http://localhost:9021/v3/api-docs
```

---

# Running Without Docker

Create a PostgreSQL database:

```sql
CREATE DATABASE sandbox_db;
```

Configure the datasource in `application.yml`:

```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5435/sandbox_db
    username: admin
    password: admin
```

Build the project:

```bash
mvn clean install
```

Run the application:

```bash
mvn spring-boot:run
```

or

```bash
java -jar target/candidate-app.jar
```

---

# Database Migration

This project uses **Liquibase** for database version control.

All schema changes are managed through versioned migration scripts to ensure consistency across different environments.

Database migrations are executed automatically when the application starts.

---

# API Documentation

### Swagger UI

```
http://localhost:9021/swagger-ui/index.html
```

### OpenAPI JSON

```
http://localhost:9021/v3/api-docs
```

---

# Main Endpoints

## Candidate

| Method | Endpoint |
|---------|----------|
| POST | /api/candidates |
| GET | /api/candidates |
| GET | /api/candidates/{id} |
| PUT | /api/candidates/{id} |
| PATCH | /api/candidates/{id}/status |
| DELETE | /api/candidates/{id} |

## Interview Schedule

| Method | Endpoint |
|---------|----------|
| POST | /api/candidates/{id}/interviews |
| GET | /api/candidates/{id}/interviews |

---

# Query Parameters

### Search

```http
GET /api/candidates?search=john
```

### Filter

```http
GET /api/candidates?status=INTERVIEW
```

### Pagination

```http
GET /api/candidates?page=1&limit=10
```

### Sorting

```http
GET /api/candidates?sortBy=name&sortDirection=asc
```

---

# Running Tests

Run all unit tests:

```bash
mvn test
```

---

# Architecture

```text
                Client
                   │
                   ▼
        Spring Boot REST API
                   │
        ┌──────────┴──────────┐
        │                     │
   Controller             Validation
        │
        ▼
      Service
        │
        ▼
    Repository
        │
        ▼
    PostgreSQL
        ▲
        │
    Liquibase
```

---

# Future Improvements

- JWT Authentication
- Role-Based Authorization
- Refresh Token Support
- Email Notifications
- CV File Upload
- API Versioning
- CI/CD Pipeline
- Kubernetes Deployment

---

# Author

**Achmad Rivai**

Backend Engineer

GitHub: https://github.com/parapai