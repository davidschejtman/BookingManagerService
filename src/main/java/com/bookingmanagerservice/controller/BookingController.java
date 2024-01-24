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
 * This controller provides HTTP endpoints for various operations related to bookings,
 * like adding, updating, cancelling, rescheduling, retrieving, and deleting bookings.
 */
@RestController
@RequestMapping("/bookings")
public class BookingController {

    private final BookingService bookingService;

    /**
     * Constructor to inject the BookingService dependency.
     *
     * @param bookingService Service that handles the business logic related to bookings.
     */
    @Autowired
    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    /**
     * Adds a new booking.
     * Handles POST request to add a new booking.
     *
     * @param booking The booking object to be added.
     * @return ResponseEntity containing the saved booking or a conflict status.
     */
    @PostMapping
    public ResponseEntity<?> addBooking(@RequestBody Booking booking) {
        Optional<Booking> savedBooking = bookingService.createBooking(booking);
        if (!savedBooking.isPresent()) {
            // Conflict status if the booking dates are unavailable.
            return ResponseEntity.status(HttpStatus.CONFLICT).body("As datas da reserva estão indisponíveis.");
        }
        return ResponseEntity.ok(savedBooking.get());
    }

    /**
     * Updates an existing booking.
     * Handles PUT request for updating a booking by its ID.
     *
     * @param id The ID of the booking to update.
     * @param booking The updated booking object.
     * @return ResponseEntity with the updated booking or not found status.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Booking> updateBooking(@PathVariable Long id, @Valid @RequestBody Booking booking) {
        Optional<Booking> updatedBookingOpt = bookingService.updateBooking(id, booking);
        return updatedBookingOpt.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Cancels a booking.
     * Handles PATCH request for cancelling a booking by its ID.
     *
     * @param id The ID of the booking to cancel.
     * @return ResponseEntity indicating success or not found.
     */
    @PatchMapping("/{id}/cancel")
    public ResponseEntity<Void> cancelBooking(@PathVariable Long id) {
        boolean isCancelled = bookingService.cancelBooking(id);
        return isCancelled ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

    /**
     * Reschedules a booking.
     * Handles PATCH request for rescheduling a booking by its ID.
     *
     * @param id The ID of the booking to reschedule.
     * @param newDates The booking object with new dates.
     * @return ResponseEntity with the rescheduled booking or not found status.
     */
    @PatchMapping("/{id}/reschedule")
    public ResponseEntity<Booking> rescheduleBooking(@PathVariable Long id, @RequestBody Booking newDates) {
        Optional<Booking> rescheduledBookingOpt = bookingService.rescheduleBooking(id, newDates);
        return rescheduledBookingOpt.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Retrieves all bookings.
     * Handles GET request to get a list of all bookings.
     *
     * @return ResponseEntity with a list of all bookings.
     */
    @GetMapping
    public ResponseEntity<List<Booking>> getAllBookings() {
        List<Booking> bookings = bookingService.getAllBookings();
        return ResponseEntity.ok(bookings);
    }

    /**
     * Deletes a booking.
     * Handles DELETE request for deleting a booking by its ID.
     *
     * @param id The ID of the booking to delete.
     * @return ResponseEntity indicating success or not found.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBooking(@PathVariable Long id) {
        boolean isDeleted = bookingService.deleteBooking(id);
        return isDeleted ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }
}
