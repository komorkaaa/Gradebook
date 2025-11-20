### **README**

# Gradebook

A simple Gradebook application built with Spring Boot, PostgreSQL, and Redis. This project allows managing students, courses, and grades.

## Features

- Add, view, and manage Students
- Add, view, and manage Courses
- Add, view, and manage Grades
- Basic frontend UI for interaction

## Technologies Used

- Java 21
- Spring Boot 3.5.7
- Spring Data JPA
- PostgreSQL 15
- Redis 7-alpine
- Docker & Docker Compose

## Notes

* Currently, DTOs for students are added but not yet used in the service layer.
* Backend validation and error handling are partially implemented via `GlobalExceptionHandler`.
* The frontend is basic and can be improved.


