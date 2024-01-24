package com.bookingmanagerservice;

import com.bookingmanagerservice.model.Booking;
import com.bookingmanagerservice.repository.BookingRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.time.LocalDate;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class BookingRepositoryTest {

    @Autowired
    private BookingRepository bookingRepository;

    @Test
    public void findOverlappingBookings() {
        // Properly initializing the Booking object with required details
        Booking booking = new Booking();
        booking.setStartDate(LocalDate.now());
        booking.setEndDate(LocalDate.now().plusDays(2));
        booking.setGuestDetails("Test Guest");

        // Saving the properly initialized Booking object
        bookingRepository.save(booking);

        // Testing the findOverlappingBookings method
        List<Booking> results = bookingRepository.findOverlappingBookings(
                LocalDate.now(), LocalDate.now().plusDays(1));
        assertFalse(results.isEmpty());
    }

    // More tests can be added here
}
