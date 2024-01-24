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

/**
 * Test class for BookingController.
 * It tests the REST API endpoints defined in the BookingController.
 */
@WebMvcTest(BookingController.class) // Specifies that WebMvcTest is to be used for testing the BookingController class.
public class BookingControllerTest {

    @Autowired
    private MockMvc mockMvc; // MockMvc provides support for Spring MVC testing.

    @MockBean
    private BookingService bookingService; // Mock version of BookingService.

    /**
     * Test for the GET request to retrieve all bookings.
     */
    @Test
    public void testGetAllBookings() throws Exception {
        mockMvc.perform(get("/bookings")) // Perform a GET request.
                .andExpect(status().isOk()); // Expect HTTP 200 OK status.
    }

    /**
     * Test for the POST request to create a new booking.
     */
    @Test
    public void testCreateBooking() throws Exception {
        // Prepare Booking object for test.
        Booking mockBooking = new Booking();
        mockBooking.setStartDate(LocalDate.of(2024, 1, 25));
        mockBooking.setEndDate(LocalDate.of(2024, 1, 26));
        mockBooking.setGuestDetails("John Doe");

        // Mocking the service to return the mock booking.
        when(bookingService.createBooking(any(Booking.class))).thenReturn(Optional.of(mockBooking));

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        String bookingJson = objectMapper.writeValueAsString(mockBooking);

        // Perform a POST request to the /bookings endpoint.
        mockMvc.perform(post("/bookings")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(bookingJson))
                .andExpect(status().isOk()); // Expect HTTP 200 OK status.
    }

    /**
     * Test for the PUT request to update a booking.
     */
    @Test
    public void testUpdateBooking() throws Exception {
        // Prepare Booking object for update.
        Booking mockBookingToUpdate = new Booking();
        mockBookingToUpdate.setStartDate(LocalDate.of(2024, 1, 25));
        mockBookingToUpdate.setEndDate(LocalDate.of(2024, 1, 26));
        mockBookingToUpdate.setGuestDetails("Jane Doe");

        // Mock the service to return the updated object encapsulated in an Optional.
        doReturn(Optional.of(mockBookingToUpdate)).when(bookingService).updateBooking(any(Long.class), any());

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        String bookingJson = objectMapper.writeValueAsString(mockBookingToUpdate);

        // Perform a PUT request to update a booking.
        mockMvc.perform(put("/bookings/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(bookingJson))
                .andExpect(status().isOk()); // Expect HTTP 200 OK status.
    }

    /**
     * Test for the PATCH request to cancel a booking.
     */
    @Test
    public void testCancelBooking() throws Exception {
        doReturn(true).when(bookingService).cancelBooking(any(Long.class));

        // Perform a PATCH request to cancel a booking.
        mockMvc.perform(patch("/bookings/1/cancel"))
                .andExpect(status().isOk()); // Expect HTTP 200 OK or another expected status.
    }

    /**
     * Test for the DELETE request to delete a booking.
     */
    @Test
    public void testDeleteBooking() throws Exception {
        doReturn(true).when(bookingService).deleteBooking(any(Long.class));

        // Perform a DELETE request to delete a booking.
        mockMvc.perform(delete("/bookings/1"))
                .andExpect(status().isOk()); // Expect HTTP 200 OK or another expected status.
    }

    // Additional tests for other endpoints can be added as necessary.
}
