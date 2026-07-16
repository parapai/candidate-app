# Candidate Management API

![Java](https://img.shields.io/badge/Java-25-orange)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5-brightgreen)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-Database-blue)
![Liquibase](https://img.shields.io/badge/Liquibase-Migration-yellow)
![Docker](https://img.shields.io/badge/Docker-Ready-2496ED)
![Swagger](https://img.shields.io/badge/OpenAPI-Swagger-85EA2D)

RESTful API untuk mengelola data kandidat dan jadwal interview menggunakan Spring Boot.

Project ini dibuat sebagai implementasi backend modern dengan menerapkan REST API, JPA Specification, Liquibase, Docker, Swagger/OpenAPI, serta unit testing menggunakan JUnit dan Mockito.

---

# Features

### Candidate Management

- Create Candidate
- Get Candidate Detail
- Get Candidate List
- Update Candidate
- Delete Candidate
- Update Candidate Status

### Interview Schedule

- Create Interview Schedule
- Get Interview Schedule

### API Features

- Search Candidate
- Filter by Status
- Pagination
- Dynamic Sorting
- Bean Validation
- Global Exception Handler
- Standard API Response
- OpenAPI Documentation

---

# Tech Stack

- Java 25
- Spring Boot 3.5
- Spring Data JPA
- PostgreSQL
- Liquibase
- Spring Validation
- Swagger OpenAPI
- Maven
- Docker
- JUnit 5
- Mockito

---

# Project Structure

```
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

Clone repository

```bash
git clone https://github.com/parapai/candidate-app.git
```

Masuk ke project

```bash
cd candidate-app
```

Build dan jalankan aplikasi

```bash
docker compose up --build -d
```

Pastikan container berjalan

```bash
docker ps
```

Docker Compose akan menjalankan:

- Candidate API
- PostgreSQL Database

Saat aplikasi pertama kali dijalankan, Liquibase akan menjalankan database migration secara otomatis sehingga tabel akan dibuat tanpa perlu menjalankan SQL secara manual.

Setelah container berhasil berjalan, API dapat langsung digunakan.

Swagger UI

```
http://localhost:9021/swagger-ui/index.html
```

OpenAPI

```
http://localhost:9021/v3/api-docs
```

---

# Running Without Docker

Buat database PostgreSQL

```sql
CREATE DATABASE candidate_db;
```

Konfigurasi datasource pada `application.yml`

```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/candidate_db
    username: postgres
    password: postgres
```

Jalankan aplikasi

```bash
mvn clean install

mvn spring-boot:run
```

atau

```bash
java -jar target/candidate-app.jar
```

---

# Database Migration

Project ini menggunakan **Liquibase**.

Setiap perubahan database dilakukan menggunakan migration sehingga struktur database tetap konsisten di seluruh environment.

Migration akan dijalankan otomatis saat aplikasi startup.

---

# API Documentation

Swagger UI

```
http://localhost:9021/swagger-ui/index.html
```

OpenAPI JSON

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

## Interview

| Method | Endpoint |
|---------|----------|
| POST | /api/candidates/{id}/interviews |
| GET | /api/candidates/{id}/interviews |

---

# Query Parameters

### Search

```
GET /api/candidates?search=john
```

### Filter

```
GET /api/candidates?status=INTERVIEW
```

### Pagination

```
GET /api/candidates?page=1&limit=10
```

### Sorting

```
GET /api/candidates?sortBy=name&sortDirection=asc
```

---

# Running Tests

```bash
mvn test
```

---

# Architecture

```
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
- Role Based Authorization
- Refresh Token
- Email Notification
- File Upload CV
- API Versioning
- CI/CD Pipeline
- Kubernetes Deployment

---

# Author

**Achmad Rivai**

Backend Engineer

GitHub

https://github.com/parapai
