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
