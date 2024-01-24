package com.bookingmanagerservice.service;

import com.bookingmanagerservice.model.Booking;
import com.bookingmanagerservice.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Service class that manages operations related to bookings.
 * This class contains the business logic for handling bookings, such as creation, updating, cancellation, and rescheduling.
 */
@Service // Marks this class as a service component in the Spring framework.
public class BookingService {

    private final BookingRepository bookingRepository; // Dependency on the booking repository.

    /**
     * Constructor for dependency injection of the BookingRepository.
     *
     * @param bookingRepository Repository that handles booking operations.
     */
    @Autowired
    public BookingService(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    /**
     * Creates a new booking, first checking if the dates are available.
     *
     * @param booking The booking to be created.
     * @return An Optional containing the saved booking, or an empty Optional if the dates are not available.
     */
    // Returns an Optional of Booking if creation is successful, or an Optional of String if there's an error (dates overlap).
    public Optional<Booking> createBooking(Booking booking) {
        if (!areDatesAvailable(booking.getStartDate(), booking.getEndDate())) {
            // Handle the case where dates are unavailable, possibly by throwing an exception or using another approach.
            return Optional.empty();
        }
        return Optional.of(bookingRepository.save(booking));
    }


    /**
     * Checks if the dates of a booking are available.
     *
     * @param startDate The start date of the booking.
     * @param endDate The end date of the booking.
     * @return true if the dates are available, false otherwise.
     */
    public boolean areDatesAvailable(LocalDate startDate, LocalDate endDate) {
        List<Booking> allBookings = bookingRepository.findAll(); // Retrieve all existing bookings.

        for (Booking existingBooking : allBookings) {
            if (dateRangesOverlap(existingBooking.getStartDate(), existingBooking.getEndDate(), startDate, endDate)) {
                return false; // The dates are not available as they overlap with an existing booking.
            }
        }
        return true; // The dates are available.
    }

    /**
     * Helper method to check if two date ranges overlap.
     *
     * @param start1 Start date of the first range.
     * @param end1 End date of the first range.
     * @param start2 Start date of the second range.
     * @param end2 End date of the second range.
     * @return true if the ranges overlap, false otherwise.
     */
    private boolean dateRangesOverlap(LocalDate start1, LocalDate end1, LocalDate start2, LocalDate end2) {
        return !start1.isAfter(end2) && !start2.isAfter(end1);
    }

    /**
     * Updates an existing booking.
     *
     * @param id The ID of the booking to update.
     * @param booking The updated booking details.
     * @return An Optional containing the updated booking, or an empty Optional if the booking is not found.
     */
    public Optional<Booking> updateBooking(Long id, Booking booking) {
        if (!bookingRepository.existsById(id)) {
            return Optional.empty(); // The booking with the provided ID was not found.
        }

        booking.setId(id); // Ensure the booking has the correct ID.
        Booking updatedBooking = bookingRepository.save(booking);
        return Optional.of(updatedBooking);
    }

    /**
     * Cancels an existing booking.
     *
     * @param id The ID of the booking to be canceled.
     * @return true if the booking was successfully canceled, false otherwise.
     */
    public boolean cancelBooking(Long id) {
        Optional<Booking> bookingOpt = bookingRepository.findById(id);
        if (bookingOpt.isPresent()) {
            Booking booking = bookingOpt.get();
            // Add additional logic if necessary, such as changing the status of the booking to 'canceled'.
            bookingRepository.delete(booking);
            return true;
        }
        return false; // The booking with the provided ID was not found.
    }

    /**
     * Reschedules an existing booking.
     *
     * @param id The ID of the booking to be rescheduled.
     * @param newDates The new booking details for rescheduling.
     * @return An Optional containing the rescheduled booking, or an empty Optional if the booking cannot be rescheduled.
     */
    // Returns an Optional of Booking if rescheduling is successful, or an Optional of String if there's an error (dates overlap).
    public Optional<Booking> rescheduleBooking(Long id, Booking newDates) {
        Optional<Booking> existingBooking = bookingRepository.findById(id);
        if (!existingBooking.isPresent() || !areDatesAvailable(newDates.getStartDate(), newDates.getEndDate())) {
            return Optional.empty();
        }

        Booking bookingToUpdate = existingBooking.get();
        bookingToUpdate.setStartDate(newDates.getStartDate());
        bookingToUpdate.setEndDate(newDates.getEndDate());
        // Update other necessary details
        return Optional.of(bookingRepository.save(bookingToUpdate));
    }

    /**
     * Retrieves all bookings.
     *
     * @return A list of all bookings.
     */
    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    /**
     * Deletes a booking based on the provided ID.
     *
     * @param id The ID of the booking to be deleted.
     * @return true if the booking was successfully deleted, false otherwise.
     */
    public boolean deleteBooking(Long id) {
        if (bookingRepository.existsById(id)) {
            bookingRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
