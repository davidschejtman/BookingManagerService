package com.bookingmanagerservice.repository;

import com.bookingmanagerservice.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    // Aqui você pode adicionar consultas personalizadas se necessário
}
