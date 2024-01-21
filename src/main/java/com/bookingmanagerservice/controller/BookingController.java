package com.bookingmanagerservice.controller;

import com.bookingmanagerservice.model.Block;
import com.bookingmanagerservice.service.BlockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;

// Importação da lista

/**
 * Controlador REST para operações relacionadas a reservas (Bookings).
 */
@RestController
@RequestMapping("/bookings")
public class BookingController {

    private final BookingService bookingService;
    /**
     * Construtor para injeção de dependência do BookingService.
     *
     * @param bookingService Serviço que contém a lógica de negócios para reservas.
     */
    @Autowired
    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }
    /**
     * Endpoint para criar uma nova reserva.
     * @param booking Dados da reserva.
     * @return ResponseEntity contendo a reserva criada.
     */
    @PostMapping
    public ResponseEntity<Booking> addBooking(@Valid @RequestBody Booking booking) {
        try {
            Booking savedBooking = bookingService.createBooking(booking);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedBooking);
        } catch (DateUnavailableException e) {
            // Retorna uma resposta indicando que as datas estão indisponíveis.
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }
    }
    /**
     * Endpoint para atualizar uma reserva existente.
     * A anotação @PutMapping mapeia solicitações PUT para /bookings/{id}.
     * @param id O ID da reserva que está sendo atualizada.
     * @param booking Os dados atualizados da reserva.
     * @return Uma ResponseEntity contendo a reserva atualizada.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Booking> updateBooking(@PathVariable Long id, @Valid @RequestBody Booking booking) {
        Booking updatedBooking = bookingService.updateBooking(id, booking);
        if(updatedBooking == null) {
            // Se o serviço retorna null, a reserva com o ID fornecido não foi encontrada.
            return ResponseEntity.notFound().build();
        }
        // Se a reserva foi atualizada com sucesso, retorna a reserva atualizada com status OK (200).
        return ResponseEntity.ok(updatedBooking);
    }
    /**
     * Endpoint para deletar uma reserva.
     *
     * @param id Identificador da reserva a ser deletada.
     * @return ResponseEntity sem conteúdo.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBooking(@PathVariable Long id) {
        bookingService.deleteBooking(id);
        return ResponseEntity.ok().build();
    }
    /**
     * Endpoint para listar todas as reservas.
     *
     * @return Lista de todas as reservas.
     */
    @GetMapping
    public ResponseEntity<List<Booking>> getAllBookings() {
        List<Booking> bookings = bookingService.getAllBookings();
        return ResponseEntity.ok(bookings);
    }
    @PatchMapping("/{id}/cancel")
    public ResponseEntity<Void> cancelBooking(@PathVariable Long id) {
        boolean isCancelled = bookingService.cancelBooking(id);
        if (!isCancelled) {
            // Se a reserva não pode ser cancelada ou não foi encontrada, retorna um status de não encontrado.
            return ResponseEntity.notFound().build();
        }
        // Se a reserva foi cancelada com sucesso, retorna um status OK (200).
        return ResponseEntity.ok().build();
    }
    /**
     * Método para reagendar uma reserva existente.
     * A anotação @PutMapping mapeia solicitações PUT para /bookings/{id}/reschedule.
     * @param id O ID da reserva que está sendo reagendada.
     * @param newDates Objeto contendo as novas datas de início e fim para a reserva.
     * @return Uma ResponseEntity com a reserva reagendada ou um erro se não puder ser reagendada.
     */
    @PutMapping("/{id}/reschedule")
    public ResponseEntity<Booking> rescheduleBooking(@PathVariable Long id, @RequestBody Booking newDates) {
        Booking rescheduledBooking = bookingService.rescheduleBooking(id, newDates);
        if (rescheduledBooking == null) {
            // A reserva não foi encontrada ou não pôde ser reagendada.
            return ResponseEntity.notFound().build();
        }
        // A reserva foi reagendada com sucesso.
        return ResponseEntity.ok(rescheduledBooking);
    }
    /**
     * Método para deletar uma reserva existente.
     * A anotação @DeleteMapping mapeia solicitações DELETE para /bookings/{id}.
     * @param id O ID da reserva que está sendo deletada.
     * @return Uma ResponseEntity indicando o resultado da operação.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBooking(@PathVariable Long id) {
        boolean isDeleted = bookingService.deleteBooking(id);
        if (!isDeleted) {
            // Se o serviço retorna false, a reserva com o ID fornecido não foi encontrada ou não pôde ser deletada.
            return ResponseEntity.notFound().build();
        }
        // Se a reserva foi deletada com sucesso, retorna um status OK (200) sem conteúdo.
        return ResponseEntity.ok().build();
    }
    /**
     * Método para listar todas as reservas.
     * A anotação @GetMapping mapeia solicitações GET para /bookings.
     * @return Uma lista de todas as reservas e uma ResponseEntity.
     */
    @GetMapping
    public ResponseEntity<List<Booking>> getAllBookings() {
        List<Booking> bookings = bookingService.getAllBookings();
        // Retorna a lista de reservas com status OK (200).
        return ResponseEntity.ok(bookings);
    }

}
