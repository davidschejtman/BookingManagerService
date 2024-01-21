package com.bookingmanagerservice.repository;

import com.bookingmanagerservice.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    // Dentro de BookingRepository

    @Query("SELECT b FROM Booking b WHERE b.startDate <= :endDate AND b.endDate >= :startDate")
    List<Booking> findOverlappingBookings(LocalDate startDate, LocalDate endDate);

// Dentro de BlockRepository

    @Query("SELECT b FROM Block b WHERE b.startDate <= :endDate AND b.endDate >= :startDate")
    List<Block> findOverlappingBlocks(LocalDate startDate, LocalDate endDate);

}
