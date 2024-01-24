package com.bookingmanagerservice.controller;

import com.bookingmanagerservice.model.Booking;
import com.bookingmanagerservice.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing bookings.
 * Provides HTTP endpoints for operations like adding, updating,
 * cancelling, rescheduling, retrieving, and deleting bookings.
 */
@RestController
@RequestMapping("/bookings")
public class BookingController {

    private final BookingService bookingService;

    /**
     * Constructor to inject the BookingService dependency.
     *
     * @param bookingService Service handling business logic related to bookings.
     */
    @Autowired
    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    /**
     * Endpoint to add a new booking.
     * Handles POST request to add a new booking.
     *
     * @param booking The booking object to be added.
     * @return ResponseEntity containing the saved booking or a conflict status.
     */

    @PostMapping
    public ResponseEntity<?> addBooking(@RequestBody Booking booking) {
        Optional<Booking> savedBooking = bookingService.createBooking(booking);
        if (!savedBooking.isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Booking dates are unavailable.");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(savedBooking.get());
    }


    /**
     * Endpoint to update an existing booking.
     * Handles PUT request to update a booking by its ID.
     *
     * @param id The ID of the booking to update.
     * @param booking Updated booking details.
     * @return ResponseEntity containing the updated booking or a not found status.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Booking> updateBooking(@PathVariable Long id, @Valid @RequestBody Booking booking) {
        Optional<Booking> updatedBookingOpt = bookingService.updateBooking(id, booking);
        return updatedBookingOpt.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Endpoint to cancel a booking.
     * Handles PATCH request to cancel a booking by its ID.
     *
     * @param id ID of the booking to cancel.
     * @return ResponseEntity indicating success or not found.
     */
    @PatchMapping("/{id}/cancel")
    public ResponseEntity<Void> cancelBooking(@PathVariable Long id) {
        boolean isCancelled = bookingService.cancelBooking(id);
        return isCancelled ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

    /**
     * Endpoint to reschedule an existing booking.
     *
     * @param id The ID of the booking to reschedule.
     * @param newDates The new booking details.
     * @return ResponseEntity containing the updated booking or an error message.
     */
    @PatchMapping("/{id}/reschedule")
    public ResponseEntity<?> rescheduleBooking(@PathVariable Long id, @RequestBody Booking newDates) {
        Optional<Booking> rescheduledBookingOpt = bookingService.rescheduleBooking(id, newDates);

        if (!rescheduledBookingOpt.isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Unable to reschedule the booking. Either the booking does not exist or the new dates are not available.");
        }

        return ResponseEntity.ok(rescheduledBookingOpt.get());
    }

    /**
     * Endpoint to retrieve all bookings.
     * Handles GET request for a list of all bookings.
     *
     * @return ResponseEntity with a list of all bookings.
     */
    @GetMapping
    public ResponseEntity<List<Booking>> getAllBookings() {
        List<Booking> bookings = bookingService.getAllBookings();
        return ResponseEntity.ok(bookings);
    }

    /**
     * Endpoint to delete a booking.
     * Handles DELETE request to delete a booking by its ID.
     *
     * @param id ID of the booking to delete.
     * @return ResponseEntity indicating success or not found.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBooking(@PathVariable Long id) {
        boolean isDeleted = bookingService.deleteBooking(id);
        return isDeleted ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }
}
