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
     * Utiliza a linguagem de consulta JPQL para selecionar reservas com datas que se sobrepõem.
     *
     * @param startDate Data de início do intervalo de busca.
     * @param endDate Data de término do intervalo de busca.
     * @return Lista de reservas que se sobrepõem ao intervalo especificado.
     */
    @Query("SELECT b FROM Booking b WHERE b.startDate <= :endDate AND b.endDate >= :startDate")
    List<Booking> findOverlappingBookings(LocalDate startDate, LocalDate endDate);

    /**
     * Encontra reservas por detalhes do hóspede.
     * O método aproveita a capacidade do Spring Data JPA de gerar consultas a partir de nomes de métodos.
     *
     * @param guestDetails Detalhes do hóspede.
     * @return Lista de reservas associadas ao hóspede especificado.
     */
    List<Booking> findByGuestDetails(String guestDetails);

    /**
     * Encontra reservas por uma data específica.
     * Retorna todas as reservas que incluem a data fornecida.
     *
     * @param date Data específica para busca de reservas.
     * @return Lista de reservas que incluem a data especificada.
     */
    @Query("SELECT b FROM Booking b WHERE b.startDate <= :date AND b.endDate >= :date")
    List<Booking> findBookingsByDate(LocalDate date);
}
