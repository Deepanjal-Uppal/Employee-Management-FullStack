# Employee Management System

This project is a Spring Boot-based RESTful web application designed to manage employee data. It demonstrates key backend development concepts such as entity modeling, data validation, manual DTO mapping, and centralized exception handling.

## Project Architecture

The application follows a layered architecture:

- Entity Layer: Represents the domain model and maps to the database.
- DTO Layer: Used to transfer data between layers, especially for API requests and responses.
- Mapper Layer: Contains logic to manually convert between Entity and DTO objects.
- Service Layer: Implements business logic and interacts with the repository.
- Controller Layer: Exposes RESTful endpoints to handle HTTP requests.
- Exception Layer: Manages application-wide error handling.

## Core Concepts

### Entity Modeling

The `Employee` entity defines the structure of employee data. It includes fields like first name, last name, email, phone number, age, date of joining, and department. It uses JPA annotations to map fields to database columns and includes an enum for department classification.

### Validation

Validation ensures data integrity using annotations such as:

- `@NotBlank` for mandatory string fields.
- `@Email` for email format validation.
- `@Pattern` for phone number format.
- `@Min` and `@Max` for age constraints.

These validations are applied at the DTO or Entity level and enforced during request processing.

### Manual Mapping

Instead of using automated tools like ModelMapper, the project uses custom logic to convert between Entity and DTO. This approach provides better control and transparency over the transformation process.

### Exception Handling

Global exception handling is implemented using `@RestControllerAdvice`. It handles:

- Resource not found exceptions.
- Validation errors.
- Other runtime exceptions.

Structured error responses are returned with timestamps, messages, and details to improve API reliability and client-side debugging.

### RESTful API Design

The application follows REST principles to expose endpoints for CRUD operations. Common HTTP methods used include:

- `GET` for retrieving data.
- `POST` for creating new records.
- `PUT` for updating existing records.
- `DELETE` for removing records.

## Technologies Used

- Java 17+
- Spring Boot
- Spring Web
- Spring Validation
- Jakarta Persistence (JPA)
- Maven

## Learning Outcomes

This project helps understand:

- How to model real-world entities using JPA.
- How to enforce data integrity using validation annotations.
- How to manually map between different object types.
- How to design clean and maintainable REST APIs.
- How to implement robust error handling in Spring Boot applications.
