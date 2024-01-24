# BookingManagerService
## Overview
BookingManagerService is a Java-based RESTful web service designed to efficiently manage property bookings and blocks. Using Spring Boot framework, this application offers a range of features for creating, updating, canceling, and managing reservations and property blocks with conflict-free scheduling.

## Features
- Create, update, cancel, and delete bookings.
- Create, update, and delete blocks to prevent bookings on certain dates.
- In-memory database integration for quick setup and testing.
- RESTful API design for easy integration with front-end applications or other services.

## Technologies
- Java 11 or newer
- Spring Boot
- H2 In-Memory Database
- JPA/Hibernate
- Maven

## Setup and Installation
#### 1.  Clone the Repository:
`git clone https://github.com/davidschejtman/BookingManagerService.git`
#### 2.  Navigate to the Project Directory:
`cd BookingManagerService`
#### 3.  Build the Project:
`mvn clean install`
#### 4.  Run the Application:
`mvn spring-boot:run`
#### 5. Access the H2 Database Console (optional)::
- Navigate to http://localhost:8080/h2-console in your browser.
- Use the JDBC URL, username, and password as defined in application.properties.

## API Endpoints
- **POST /bookings** - Create a new booking.
- **PUT /bookings/{id}**- Update an existing booking.
- **PATCH /bookings/{id}/cancel**- Cancel a booking.
- **DELETE /bookings/{id}**- Delete a booking.
- **GET /bookings** - Retrieve all bookings.
- **POST /blocks** - Create a new block.
- **PUT /blocks/{id}** - Update an existing block.
- **DELETE /blocks/{id}** - Delete a block.
- **GET /blocks** - Retrieve all blocks.

## Testing
Run the automated tests using:
`mvn test`

## Database Choice

For this project, i have chosen the H2 Database for the following reasons:
- **Ease of Configuration:** H2 is an in-memory database, which means it requires no separate installation, making it ideal for rapid development and testing.
- **Speed for Testing:** The in-memory nature of H2 makes it incredibly fast for integration tests.
- **Compatibility with JPA/Hibernate:** H2 integrates seamlessly with Spring Boot and Hibernate, ensuring smooth development and testing processes.

This choice aligns with our project's need for a lightweight, efficient, and easy-to-set-up database solution.
