package com.bookingmanagerservice;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.bookingmanagerservice.model.Booking;
import com.bookingmanagerservice.repository.BookingRepository;
import com.bookingmanagerservice.service.BookingService;
import java.time.LocalDate;
import java.util.Optional;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;

@ExtendWith(MockitoExtension.class)
public class BookingServiceTest {

    @Mock
    private BookingRepository bookingRepository;

    @InjectMocks
    private BookingService bookingService;

    @Test
    public void testCreateBooking() {
        // Criando um objeto Booking com dados fictícios
        Booking booking = new Booking();
        booking.setStartDate(LocalDate.of(2024, 1, 1));
        booking.setEndDate(LocalDate.of(2024, 1, 10));
        booking.setGuestDetails("John Doe");

        // Configurando o comportamento do mock do repositório
        when(bookingRepository.save(any(Booking.class))).thenReturn(booking);

        // Executando o teste
        Optional<Booking> savedBooking = bookingService.createBooking(booking);

        // Verificando os resultados
        assertTrue(savedBooking.isPresent(), "A reserva deve ser criada com sucesso");
        assertEquals(booking, savedBooking.get(), "A reserva salva deve ser igual à reserva criada");

        // Verificando se o método save foi chamado com o objeto Booking correto
        verify(bookingRepository).save(booking);
    }
    @Test
    public void testUpdateBooking() {
        // Dados da reserva existente
        Long bookingId = 1L;
        Booking existingBooking = new Booking();
        existingBooking.setId(bookingId);
        existingBooking.setStartDate(LocalDate.of(2024, 1, 1));
        existingBooking.setEndDate(LocalDate.of(2024, 1, 5));
        existingBooking.setGuestDetails("John Doe");

        // Dados para atualização
        Booking updatedDetails = new Booking();
        updatedDetails.setStartDate(LocalDate.of(2024, 1, 3));
        updatedDetails.setEndDate(LocalDate.of(2024, 1, 8));
        updatedDetails.setGuestDetails("Jane Doe");

        // Configurando o comportamento do mock
        when(bookingRepository.existsById(bookingId)).thenReturn(true);
        when(bookingRepository.save(any(Booking.class))).thenReturn(updatedDetails);

        // Executando o método de atualização
        Optional<Booking> updatedBooking = bookingService.updateBooking(bookingId, updatedDetails);

        // Verificações
        assertTrue(updatedBooking.isPresent(), "A reserva deve ser atualizada com sucesso");
        assertSame(updatedDetails, updatedBooking.get(), "O objeto retornado deve ser o mesmo que o objeto atualizado");
        assertEquals(updatedDetails.getGuestDetails(), updatedBooking.get().getGuestDetails(), "Os detalhes do hóspede devem ser atualizados");
        assertEquals(updatedDetails.getStartDate(), updatedBooking.get().getStartDate(), "A data de início deve ser atualizada");
        assertEquals(updatedDetails.getEndDate(), updatedBooking.get().getEndDate(), "A data de término deve ser atualizada");

        // Verificar se os métodos do repository foram chamados corretamente
        verify(bookingRepository, times(1)).existsById(bookingId);
        verify(bookingRepository, times(1)).save(updatedDetails);
    }
    @Test
    public void testCancelBooking() {
        Long bookingId = 1L; // ID fictício da reserva
        Booking mockBooking = new Booking(); // Crie uma instância mock de Booking

        // Configuração do comportamento do mock
        when(bookingRepository.findById(bookingId)).thenReturn(Optional.of(mockBooking));

        // Executando o método de cancelamento
        boolean result = bookingService.cancelBooking(bookingId);

        // Verificações
        assertTrue(result, "A reserva deve ser cancelada com sucesso");

        // Verificar se os métodos do repository foram chamados
        verify(bookingRepository, times(1)).findById(bookingId);
        verify(bookingRepository, times(1)).delete(mockBooking);
    }
    // Teste para caso em que a reserva não existe
    @Test
    public void testCancelNonExistingBooking() {
        Long bookingId = 2L; // ID fictício da reserva

        // Configuração do comportamento do mock para simular uma reserva não existente
        when(bookingRepository.findById(bookingId)).thenReturn(Optional.empty());

        // Executando o método de cancelamento
        boolean result = bookingService.cancelBooking(bookingId);

        // Verificações
        assertFalse(result, "O cancelamento deve falhar se a reserva não existir");

        // Verificar se o método delete não foi chamado
        verify(bookingRepository, never()).delete(any(Booking.class));
    }
    @Test
    public void testRescheduleBooking() {
        Long bookingId = 1L;
        LocalDate newStartDate = LocalDate.of(2024, 1, 10);
        LocalDate newEndDate = LocalDate.of(2024, 1, 15);

        Booking existingBooking = new Booking();
        existingBooking.setId(bookingId);
        existingBooking.setStartDate(LocalDate.of(2024, 1, 1));
        existingBooking.setEndDate(LocalDate.of(2024, 1, 5));
        existingBooking.setGuestDetails("John Doe");

        Booking newBookingDetails = new Booking();
        newBookingDetails.setStartDate(newStartDate);
        newBookingDetails.setEndDate(newEndDate);
        newBookingDetails.setGuestDetails("Jane Doe");

        when(bookingRepository.findById(bookingId)).thenReturn(Optional.of(existingBooking));
        when(bookingRepository.save(any(Booking.class))).thenReturn(newBookingDetails);

        Optional<Booking> rescheduledBooking = bookingService.rescheduleBooking(bookingId, newBookingDetails);

        assertTrue(rescheduledBooking.isPresent(), "The booking should be successfully rescheduled");
        assertEquals(newStartDate, rescheduledBooking.get().getStartDate(), "The start date should be updated");
        assertEquals(newEndDate, rescheduledBooking.get().getEndDate(), "The end date should be updated");
        assertEquals("Jane Doe", rescheduledBooking.get().getGuestDetails(), "The guest details should be updated");

        verify(bookingRepository).findById(bookingId);
        verify(bookingRepository).save(any(Booking.class));
    }
    @Test
    public void testCancellationAndRebooking() {
        Long bookingId = 1L;
        Booking bookingToCancel = new Booking(); // set up this booking with required details
        Booking newBooking = new Booking(); // set up this booking with new details

        // Mock the repository behavior
        when(bookingRepository.findById(bookingId)).thenReturn(Optional.of(bookingToCancel));
        when(bookingRepository.save(any(Booking.class))).thenReturn(newBooking);

        // Perform cancellation
        boolean cancellationResult = bookingService.cancelBooking(bookingId);
        assertTrue(cancellationResult, "Booking should be cancelled successfully");

        // Perform rebooking
        Optional<Booking> rebooked = bookingService.createBooking(newBooking);
        assertTrue(rebooked.isPresent(), "Rebooking should be successful");

        // Verify interactions with repository
        verify(bookingRepository).delete(bookingToCancel);
        verify(bookingRepository).save(newBooking);
    }

    @Test
    public void testBookingWithOverlappingDates() {
        Long bookingId = 1L;
        Booking existingBooking = new Booking();
        existingBooking.setId(bookingId);
        existingBooking.setStartDate(LocalDate.of(2024, 1, 5));
        existingBooking.setEndDate(LocalDate.of(2024, 1, 10));

        // Mock the findAll method to return a list with an existing booking
        when(bookingRepository.findAll()).thenReturn(Arrays.asList(existingBooking));

        Booking newBooking = new Booking();
        newBooking.setStartDate(LocalDate.of(2024, 1, 7)); // These dates overlap with the existing booking
        newBooking.setEndDate(LocalDate.of(2024, 1, 12));

        // Attempt to create a booking
        Optional<Booking> result = bookingService.createBooking(newBooking);

        assertFalse(result.isPresent(), "Booking should not be successful due to overlapping dates");

        // Verify that save method was never called since dates overlap
        verify(bookingRepository, never()).save(newBooking);
    }
}