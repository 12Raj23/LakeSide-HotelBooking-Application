# LakeSide-HotelBooking-Application


# Lakeside Hotel Booking Application

Lakeside Hotel Booking is a web application built to manage hotel room bookings. The application provides a comprehensive booking management system, allowing users to search for available rooms, make bookings, view booking history, and more. This project is developed using Spring Boot for the backend and React for the frontend, along with MySQL for data storage.

## Table of Contents

- [Features](#features)
- [Technologies Used](#technologies-used)
- [Architecture](#architecture)
- [Project Structure](#project-structure)
- [Getting Started](#getting-started)
- [API Endpoints](#api-endpoints)
- [Frontend (React)](#frontend-react)
- [Testing](#testing)
- [Contributing](#contributing)
- [License](#license)

---

## Features

- User-friendly booking management system
- Secure login and signup using Spring Security
- RESTful API endpoints for handling booking operations
- Room booking with details on check-in/check-out dates, guest info, and booking confirmation code
- Database management using MySQL
- Responsive and interactive UI using React and Bootstrap
- Filterable bookings table with date-based filtering
- Comprehensive Postman test collection for API verification

## Technologies Used

### Backend (Java, Spring Boot)
- **Spring Web**: Provides RESTful web services
- **Spring MVC**: Manages application logic and routes requests
- **Spring Security**: Handles authentication and authorization
- **Spring JPA**: Simplifies database interactions
- **MySQL**: Relational database management
- **Java**: Programming language

### Frontend (JavaScript, React)
- **React**: Library for building interactive user interfaces
- **Bootstrap**: CSS framework for responsive and modern UI

---

## Architecture

The Lakeside Hotel Booking application follows the Model-View-Controller (MVC) architecture:

1. **Model**: Manages data entities and database interactions.
2. **View**: React-based UI rendering booking data and actions.
3. **Controller**: Handles HTTP requests and delegates to the Service layer.

---

## Project Structure

The project is organized into multiple packages:

### Backend (Spring Boot)

- **Controller**: Contains classes that handle HTTP requests and map to appropriate service methods. Ex: `BookingController`, `UserController`
- **Service**: Holds the business logic of the application. Ex: `BookingService`, `UserService`
- **Repository**: Interfaces for data access, extending Spring JPA's `CrudRepository`. Ex: `BookingRepository`, `UserRepository`
- **Model**: Entity classes representing database tables. Ex: `Booking`, `Room`, `User`
- **Config**: Contains security configurations and other necessary configurations. Ex: `WebSecurityConfig`

### Frontend (React)

- **Components**: Reusable UI components. Ex: `BookingForm`, `BookingList`, `BookingDetails`
- **Services**: API calls for accessing backend endpoints. Ex: `bookingService.js`
- **Assets**: Static files and Bootstrap styling

---

## Getting Started

### Prerequisites

- **Java 17** or later
- **Node.js** and **npm**
- **MySQL** database
- **Postman** for API testing


    ```


### UI Components

The application uses **Bootstrap** for styling, ensuring responsiveness and a polished appearance.

---

## Testing

### Postman Collection

To test the API endpoints, you can use the included Postman collection located in the `postman` directory. Import this collection into Postman to validate each endpoint.


