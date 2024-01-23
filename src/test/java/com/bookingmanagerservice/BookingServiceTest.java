package com.bookingmanagerservice;

import com.bookingmanagerservice.model.Booking;
import com.bookingmanagerservice.repository.BlockRepository;
import com.bookingmanagerservice.repository.BookingRepository;
import com.bookingmanagerservice.service.BookingService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class BookingServiceTest {

    // Mock das dependências do BookingService
    @Mock
    private BookingRepository bookingRepository;

    @Mock
    private BlockRepository blockRepository;

    // Injeta os mocks no BookingService para teste
    @InjectMocks
    private BookingService bookingService;

    @Test
    public void testCreateBooking() {
        // Configuração inicial do teste
        Booking booking = new Booking();
        booking.setStartDate(LocalDate.of(2024, 1, 1));
        booking.setEndDate(LocalDate.of(2024, 1, 10));
        booking.setGuestDetails("John Doe");

        // Configura o mock para retornar o booking quando salvar
        when(bookingRepository.save(any(Booking.class))).thenReturn(booking);

        // Executa o método a ser testado
        Optional<Booking> savedBooking = bookingService.createBooking(booking);

        // Verifica se a reserva foi criada com sucesso
        assertTrue(savedBooking.isPresent(), "A reserva deve ser criada com sucesso");
        assertEquals(booking, savedBooking.get(), "A reserva salva deve ser igual à reserva criada");
    }

    @Test
    public void testUpdateBooking() {
        // Configuração inicial para o teste de atualização
        Long bookingId = 1L;
        Booking existingBooking = new Booking();
        existingBooking.setId(bookingId);
        existingBooking.setStartDate(LocalDate.of(2024, 1, 1));
        existingBooking.setEndDate(LocalDate.of(2024, 1, 5));
        existingBooking.setGuestDetails("John Doe");

        // Detalhes atualizados para a reserva
        Booking updatedDetails = new Booking();
        updatedDetails.setStartDate(LocalDate.of(2024, 1, 3));
        updatedDetails.setEndDate(LocalDate.of(2024, 1, 8));
        updatedDetails.setGuestDetails("Jane Doe");

        // Configura o mock para simular o comportamento do repositório
        when(bookingRepository.existsById(bookingId)).thenReturn(true);
        when(bookingRepository.save(any(Booking.class))).thenReturn(updatedDetails);

        // Executa o método a ser testado
        Optional<Booking> updatedBooking = bookingService.updateBooking(bookingId, updatedDetails);

        // Verifica se a reserva foi atualizada com sucesso
        assertTrue(updatedBooking.isPresent(), "A reserva deve ser atualizada com sucesso");
        assertEquals(updatedDetails.getGuestDetails(), updatedBooking.get().getGuestDetails());
        assertEquals(updatedDetails.getStartDate(), updatedBooking.get().getStartDate());
        assertEquals(updatedDetails.getEndDate(), updatedBooking.get().getEndDate());
    }

    // Adicione aqui mais métodos de teste para cancelar, reagendar, etc.

    // Lembre-se de configurar o comportamento dos mocks de acordo com o necessário para cada teste
}
