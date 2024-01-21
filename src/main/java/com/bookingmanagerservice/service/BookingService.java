package com.bookingmanagerservice.service;

import com.bookingmanagerservice.model.Booking;
import com.bookingmanagerservice.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;

    @Autowired
    public BookingService(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    public Booking createBooking(Booking booking) {
        // Implemente a lógica para criar uma reserva
        // Isso pode incluir a validação das datas e verificação de sobreposição
        return bookingRepository.save(booking);
    }

    /**
     * Verifica se as datas de uma reserva estão disponíveis (não se sobrepõem a outras reservas ou bloqueios).
     * @param newBooking A reserva que está sendo feita.
     * @return true se as datas estiverem disponíveis, false caso contrário.
     */
    public boolean areDatesAvailable(LocalDate startDate, LocalDate endDate) {
        // Verifica se há reservas existentes que se sobrepõem a estas datas.
        List<Booking> overlappingBookings = bookingRepository.findOverlappingBookings(startDate, endDate);
        if (!overlappingBookings.isEmpty()) {
            return false; // Existem reservas que se sobrepõem.
        }

        // Verifica se há bloqueios existentes que se sobrepõem a estas datas.
        List<Block> overlappingBlocks = blockRepository.findOverlappingBlocks(startDate, endDate);
        if (!overlappingBlocks.isEmpty()) {
            return false; // Existem bloqueios que se sobrepõem.
        }

        return true; // As datas estão disponíveis.
    }

    public Booking createBooking(Booking booking) {
        if (!areDatesAvailable(booking.getStartDate(), booking.getEndDate())) {
            // As datas não estão disponíveis. Lança uma exceção ou retorna null.
            throw new DateUnavailableException("As datas da reserva estão indisponíveis.");
        }
        return bookingRepository.save(booking); // Salva a reserva se as datas estiverem disponíveis.
    }

    @PostMapping
    public ResponseEntity<Booking> addBooking(@Valid @RequestBody Booking booking) {
        Booking savedBooking = bookingService.createBooking(booking);
        if (savedBooking == null) {
            // As datas não estão disponíveis ou houve outro erro.
            // Retorne uma resposta adequada, como 400 Bad Request ou 409 Conflict.
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(savedBooking);
    }
}
