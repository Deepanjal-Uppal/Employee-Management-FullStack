# Employee Management Full Stack Project

## Overview
This is a **Full Stack Employee Management System** built using **Spring Boot** for the backend and **React.js** for the frontend.  
It allows users to **create, read, update, and delete employee records** through a responsive web interface, with a secure REST API managing data on the backend.

---

## Features
- Add new employees
- View employee list
- Update employee details
- Delete employees
- Responsive UI for web browsers
- CORS-enabled backend for seamless frontend integration
- Error handling with descriptive messages

---

## Tech Stack

### Backend
- **Language:** Java  
- **Framework:** Spring Boot  
- **Database:** H2 / MySQL (depending on configuration)  
- **Other:** Maven, JPA, RESTful API, Exception Handling  

### Frontend
- **Library:** React.js  
- **Styling:** CSS  
- **HTTP Requests:** Axios / Fetch API  
- **Other:** React Components for Employee Form & Employee List  

---

## Project Structure

### Backend (`Employee-Management-Backend`)
src/
├─ main/java/com/EmployeeManagement
│ ├─ Config/ # CORS configuration
│ ├─ Controller/ # REST API controllers
│ ├─ DTO/ # Data Transfer Objects
│ ├─ Entity/ # Employee entity
│ ├─ Exceptions/ # Custom exceptions & global handlers
│ ├─ Mapper/ # Manual DTO to Entity mapping
│ ├─ Repository/ # JPA Repositories
│ ├─ Service/ # Service interfaces
│ └─ Service/Impl/ # Service implementations
