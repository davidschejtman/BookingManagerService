package com.bookingmanagerservice.repository;

import com.bookingmanagerservice.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.time.LocalDate;
import java.util.List;

/**
 * JPA Repository for the Booking entity.
 * This interface manages the database operations for the Booking entity.
 */
public interface BookingRepository extends JpaRepository<Booking, Long> {

    /**
     * Finds bookings that overlap with a given date range.
     * Uses the JPQL (Java Persistence Query Language) to select bookings with overlapping dates.
     *
     * @param startDate The start date of the search range.
     * @param endDate   The end date of the search range.
     * @return List of bookings that overlap with the specified range.
     */
    @Query("SELECT b FROM Booking b WHERE b.startDate <= :endDate AND b.endDate >= :startDate")
    List<Booking> findOverlappingBookings(LocalDate startDate, LocalDate endDate);

    /**
     * Finds bookings by guest details.
     * This method leverages Spring Data JPA's ability to generate queries from method names.
     *
     * @param guestDetails The guest details.
     * @return List of bookings associated with the specified guest.
     */
    List<Booking> findByGuestDetails(String guestDetails);

    /**
     * Finds bookings by a specific date.
     * Returns all bookings that include the provided date.
     *
     * @param date The specific date for searching bookings.
     * @return List of bookings that include the specified date.
     */
    @Query("SELECT b FROM Booking b WHERE b.startDate <= :date AND b.endDate >= :date")
    List<Booking> findBookingsByDate(LocalDate date);
}
