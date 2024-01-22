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
- ** POST /bookings** - Create a new booking.
- ** PUT /bookings/{id} **- Update an existing booking.
- ** PATCH /bookings/{id}/cancel **- Cancel a booking.
- ** DELETE /bookings/{id} **- Delete a booking.
- **GET /bookings** - Retrieve all bookings.
- **POST /blocks** - Create a new block.
- **PUT /blocks/{id}** - Update an existing block.
- **DELETE /blocks/{id}** - Delete a block.
- **GET /blocks** - Retrieve all blocks.

## Testing
Run the automated tests using:
`mvn test`


Implementation of Features:
1. Create Reservations (POST /bookings)
Enable to edit task name
Implementation of the addBooking method in BookingController to create a new reservation.
2. Update Reservations (PUT /bookings/{id})
Enable to edit task name
Implementation of the updateBooking method in BookingController to update an existing reservation.
3. Cancel Reservations (PATCH /bookings/{id}/cancel)
Enable to edit task name
Implementation of a method to cancel an existing reservation (not previously specified in detail).
4. Reschedule Reservations (PUT /bookings/{id}/reschedule)
Enable to edit task name
Implementation of a method to reschedule an existing reservation (not previously specified in detail).
5. Delete Reservations (DELETE /bookings/{id})
Enable to edit task name
Implementation of the deleteBooking method in BookingController to delete a reservation.
6. List Reservations (GET /bookings)
Enable to edit task name
Implementation of the getAllBookings method in BookingController to list all reservations.
7. Create Blocks (POST /blocks)
Enable to edit task name
Implementing the addBlock method in BlockController to create a new block
8. Update Blocks (PUT /blocks/{id})
Enable to edit task name
Implemented the updateBlock method in BlockController to update an existing block.
9. Delete Blocks (DELETE /blocks/{id})
Enable to edit task name
Implementation of the deleteBlock method in BlockController to delete a block.
10. List Blocks (GET /blocks)
Enable to edit task name
Implementation of getAllBlocks method in BlockController to list all blocks.
11. Data Validation
Enable to edit task name
Addition of validations in Booking and Block to guarantee the integrity of the data entered.
12. Prevent Reservation Overlaps and Blocks
Enable to edit task name
Inclusion of business logic to ensure reservations and locks do not overlap.
13. Test Endpoints
Enable to edit task name
Manual testing of endpoints with tools like Postman or cURL and automated testing using integration tests with MockMvc.
