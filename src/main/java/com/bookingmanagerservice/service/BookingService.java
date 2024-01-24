package com.bookingmanagerservice.service;

import com.bookingmanagerservice.model.Booking;
import com.bookingmanagerservice.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;

    @Autowired
    public BookingService(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    /**
     * Cria uma nova reserva, verificando primeiro se as datas estão disponíveis.
     *
     * @param booking A reserva a ser criada.
     * @return Um Optional contendo a reserva salva, ou um Optional vazio se as datas não estiverem disponíveis.
     */
    public Optional<Booking> createBooking(Booking booking) {
        if (!areDatesAvailable(booking.getStartDate(), booking.getEndDate())) {
            return Optional.empty();  // As datas não estão disponíveis
        }
        return Optional.of(bookingRepository.save(booking));
    }

    /**
     * Verifica se as datas de uma reserva estão disponíveis.
     *
     * @param startDate Data de início da reserva.
     * @param endDate Data de término da reserva.
     * @return true se as datas estiverem disponíveis, false caso contrário.
     */
    public boolean areDatesAvailable(LocalDate startDate, LocalDate endDate) {
        List<Booking> allBookings = bookingRepository.findAll();  // Retrieve all existing bookings

        for (Booking existingBooking : allBookings) {
            if (dateRangesOverlap(existingBooking.getStartDate(), existingBooking.getEndDate(), startDate, endDate)) {
                return false;  // The dates are not available as they overlap with an existing booking
            }
        }
        return true;  // The dates are available
    }

    private boolean dateRangesOverlap(LocalDate start1, LocalDate end1, LocalDate start2, LocalDate end2) {
        return !start1.isAfter(end2) && !start2.isAfter(end1);
    }

    public Optional<Booking> updateBooking(Long id, Booking booking) {
        if (!bookingRepository.existsById(id)) {
            return Optional.empty(); // A reserva com o ID fornecido não foi encontrada
        }

        booking.setId(id); // Certifique-se de que a reserva tem o ID correto
        Booking updatedBooking = bookingRepository.save(booking);
        return Optional.of(updatedBooking);
    }
    /**
     * Cancela uma reserva existente.
     *
     * @param id O ID da reserva a ser cancelada.
     * @return true se a reserva foi cancelada com sucesso, false caso contrário.
     */
    public boolean cancelBooking(Long id) {
        Optional<Booking> bookingOpt = bookingRepository.findById(id);
        if (bookingOpt.isPresent()) {
            Booking booking = bookingOpt.get();
            // Adicione lógica adicional se necessário, como alterar o status da reserva para 'cancelado'
            bookingRepository.delete(booking);
            return true;
        }
        return false; // A reserva com o ID fornecido não foi encontrada
    }
    /**
     * Reagenda uma reserva existente.
     *
     * @param id O ID da reserva a ser reagendada.
     * @param newDates Os novos dados da reserva para reagendamento.
     * @return Um Optional contendo a reserva reagendada, ou um Optional vazio se a reserva não puder ser reagendada.
     */
    public Optional<Booking> rescheduleBooking(Long id, Booking newDates) {
        Optional<Booking> existingBooking = bookingRepository.findById(id);
        if (existingBooking.isPresent() && areDatesAvailable(newDates.getStartDate(), newDates.getEndDate())) {
            Booking bookingToUpdate = existingBooking.get();
            bookingToUpdate.setStartDate(newDates.getStartDate());
            bookingToUpdate.setEndDate(newDates.getEndDate());
            // Atualize outras informações, se necessário
            return Optional.of(bookingRepository.save(bookingToUpdate));
        }
        return Optional.empty();
    }
    /**
     * Recupera todas as reservas.
     *
     * @return Lista de todas as reservas.
     */
    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }
    /**
     * Deleta uma reserva com base no ID fornecido.
     *
     * @param id O ID da reserva a ser deletada.
     * @return true se a reserva foi deletada com sucesso, false caso contrário.
     */
    public boolean deleteBooking(Long id) {
        if (bookingRepository.existsById(id)) {
            bookingRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
