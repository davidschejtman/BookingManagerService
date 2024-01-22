package com.bookingmanagerservice.repository;

import com.bookingmanagerservice.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.time.LocalDate;
import java.util.List;

/**
 * Repositório JPA para a entidade Booking.
 * Esta interface gerencia as operações de banco de dados para a entidade Booking.
 */
public interface BookingRepository extends JpaRepository<Booking, Long> {

    /**
     * Encontra reservas que se sobrepõem a um determinado intervalo de datas.
     *
     * @param startDate Data de início do intervalo de busca.
     * @param endDate Data de término do intervalo de busca.
     * @return Lista de reservas que se sobrepõem ao intervalo especificado.
     */
    @Query("SELECT b FROM Booking b WHERE b.startDate <= :endDate AND b.endDate >= :startDate")
    List<Booking> findOverlappingBookings(LocalDate startDate, LocalDate endDate);
}
