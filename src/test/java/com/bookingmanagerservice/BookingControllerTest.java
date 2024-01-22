package com.bookingmanagerservice;

import com.bookingmanagerservice.controller.BookingController;
import com.bookingmanagerservice.model.Booking;
import com.bookingmanagerservice.service.BookingService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Optional;
import java.time.LocalDate;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;


@WebMvcTest(BookingController.class)
public class BookingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookingService bookingService;

    @Test
    public void testGetAllBookings() throws Exception {
        mockMvc.perform(get("/bookings"))
                .andExpect(status().isOk());
    }

    @Test
    public void testCreateBooking() throws Exception {
        // Preparando o objeto Booking para o teste
        Booking mockBooking = new Booking();
        mockBooking.setStartDate(LocalDate.of(2024, 1, 25));
        mockBooking.setEndDate(LocalDate.of(2024, 1, 26));
        mockBooking.setGuestDetails("John Doe");

        // Configurando o mock do BookingService para retornar o objeto mockBooking
        when(bookingService.createBooking(any(Booking.class))).thenReturn(Optional.of(mockBooking));

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        String bookingJson = objectMapper.writeValueAsString(mockBooking);

        // Realizando a chamada POST para o endpoint /bookings
        mockMvc.perform(post("/bookings")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(bookingJson))
                .andExpect(status().isOk()); // Verificando se a resposta tem o status HTTP 200 OK
    }

    @Test
    public void testUpdateBooking() throws Exception {
        Booking mockBookingToUpdate = new Booking();
        mockBookingToUpdate.setStartDate(LocalDate.of(2024, 1, 25));
        mockBookingToUpdate.setEndDate(LocalDate.of(2024, 1, 26));
        mockBookingToUpdate.setGuestDetails("Jane Doe");

        // Suponha que o serviço retorne o objeto atualizado encapsulado em um Optional
        doReturn(Optional.of(mockBookingToUpdate)).when(bookingService).updateBooking(any(Long.class), any());

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        String bookingJson = objectMapper.writeValueAsString(mockBookingToUpdate);

        mockMvc.perform(put("/bookings/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(bookingJson))
                .andExpect(status().isOk());
    }


    @Test
    public void testCancelBooking() throws Exception {
        doReturn(true).when(bookingService).cancelBooking(any(Long.class));

        mockMvc.perform(patch("/bookings/1/cancel"))
                .andExpect(status().isOk()); // ou outro status esperado
    }

    @Test
    public void testDeleteBooking() throws Exception {
        doReturn(true).when(bookingService).deleteBooking(any(Long.class));

        mockMvc.perform(delete("/bookings/1"))
                .andExpect(status().isOk()); // ou outro status esperado
    }

    // Adicione mais testes para outros endpoints conforme necessário
}
