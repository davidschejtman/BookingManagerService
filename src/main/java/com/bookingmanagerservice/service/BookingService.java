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

    // Métodos adicionais para atualizar, cancelar, etc.
}
