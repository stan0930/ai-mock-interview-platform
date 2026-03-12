# AI Mock Interview & Capability Enhancement Platform

A full-stack web application designed for AI-driven mock interviews and capability assessment. 
The system consists of a robust Java Spring Boot backend (`ai-mock-interview-server`) and a modern, responsive Vue 3 frontend (`ai-mock-interview-web`).

## System Architecture
* **Frontend:** Vue 3, Vite, Element Plus, Vue Router, Axios
* **Backend:** Java 17, Spring Boot 2.7, MyBatis-Plus, MySQL 8
* **Build Tools:** npm (Frontend), Maven (Backend)

## Core Features
* Role-based Access Control (RBAC) Admin Dashboard
* Centralized API Request Interception and Error Handling
* Standardized Response Formatting & Global Exception Management

## Getting Started

### Prerequisites
* Java 17+
* Node.js 18+
* MySQL 8.0+

### Backend Setup (`admin-server`)
1. Configure your MySQL credentials in `admin-server/src/main/resources/application.yml`.
2. Open the project in your IDE (IntelliJ IDEA or Eclipse) and run `AdminApplication.java`.
3. The server will start on port `8081`.

### Frontend Setup (`admin-web`)
1. Navigate to the frontend directory: `cd admin-web`
2. Install dependencies: `npm install`
3. Run the development server: `npm run dev`
4. Access the application at `http://localhost:3000`
