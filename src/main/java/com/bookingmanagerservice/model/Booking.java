package com.bookingmanagerservice.model;

import jakarta.persistence.*; // JPA imports for object-relational mapping
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

/**
 * Class representing the 'Booking' entity.
 * This entity is mapped to a database table using JPA annotations.
 * It represents a booking, with start and end dates, and guest details.
 */
@Entity // Indicates that this class is a JPA entity.
public class Booking {

    @Id // Marks this field as the primary key.
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Defines the ID generation strategy.
    private Long id; // Unique identifier for each booking.

    @NotNull(message = "Start date cannot be null") // Validation: start date must not be null.
    @Column(nullable = false) // Marks the column as non-null in the database.
    private LocalDate startDate; // The start date of the booking.

    @NotNull(message = "End date cannot be null") // Validation: end date must not be null.
    @Column(nullable = false) // Marks the column as non-null in the database.
    private LocalDate endDate; // The end date of the booking.

    @NotBlank(message = "Guest details cannot be empty") // Validation: guest details must not be blank.
    @Column(length = 200) // Sets the maximum size of the column.
    private String guestDetails; // Details about the guest.

    /**
     * Empty constructor for use by JPA.
     */
    public Booking() {
        // Empty constructor required for proper functioning of JPA.
    }

    /**
     * Constructor to create a new booking.
     *
     * @param startDate    Start date of the booking.
     * @param endDate      End date of the booking.
     * @param guestDetails Details of the guest.
     */
    public Booking(LocalDate startDate, LocalDate endDate, String guestDetails) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.guestDetails = guestDetails;
    }

    // Getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getGuestDetails() {
        return guestDetails;
    }

    public void setGuestDetails(String guestDetails) {
        this.guestDetails = guestDetails;
    }

    // Overriding equals and hashCode methods...

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Booking)) return false;
        Booking booking = (Booking) o;
        return id != null && id.equals(booking.id);
    }

    @Override
    public int hashCode() {
        return 31;
    }
}
