# ✈️ Airport Management System

A full-stack web application for managing airport operations, built with **Spring Boot**. The system handles flight management, passenger check-in, boarding pass generation, gate assignments, baggage tracking, plane maintenance, and employee dashboards.

## 🚀 Features

- **Authentication** — Secure login system for employees and passengers
- **Flight Management** — Create, update, and track flights with gate and terminal assignments
- **Passenger Check-In** — Complete check-in workflow with status tracking
- **Boarding Pass Generation** — Automated boarding pass creation linked to reservations
- **Baggage Tracking** — Baggage registration with size classification
- **Gate & Terminal Management** — Real-time gate status updates and terminal oversight
- **Plane & Engine Maintenance** — Maintenance history logging and technical support tracking
- **Employee Dashboard** — Dedicated dashboard for airport staff operations
- **Reservation System** — End-to-end flight reservation with seat selection

## 🛠️ Tech Stack

| Layer | Technology |
|-------|-----------|
| Backend | Java, Spring Boot |
| Frontend | Thymeleaf, HTML/CSS |
| Database | H2 (embedded) |
| ORM | Spring Data JPA / Hibernate |
| Build Tool | Maven |
| Architecture | MVC (Model-View-Controller) |

## 📁 Project Structure

```
src/main/java/org/example/project/
├── controllers/        # MVC Controllers (Auth, CheckIn, Passenger, Employee)
├── model/              # JPA Entities (Flight, Passenger, Plane, Gate, etc.)
├── repository/         # Spring Data JPA Repositories
├── service/            # Business Logic Layer
└── ProjectApplication.java
```

## ⚙️ Getting Started

### Prerequisites

- Java 17+
- Maven 3.8+

### Run the application

```bash
# Clone the repository
git clone git@github.com:michaalj/airport-management-system.git
cd airport-management-system

# Build and run
./mvnw spring-boot:run
```

The application will be available at `http://localhost:8080`

## 📊 Data Model

The system manages **18 entities** including:

`Person` → `Passenger` / `Employee` (inheritance), `Flight`, `Plane`, `Engine`, `Gate`, `GateStatus`, `Terminal`, `Reservation`, `BoardingPass`, `CheckIn`, `CheckInStatus`, `Baggage`, `BaggageSize`, `MaintenanceHistory`, `TechnicalSupport`, `TechnicalSupportStatus`

## 📝 License

This project was developed as part of the **MAS (Modeling and Analysis of Information Systems)** course at **Polish-Japanese Academy of Information Technology (PJATK)**.
