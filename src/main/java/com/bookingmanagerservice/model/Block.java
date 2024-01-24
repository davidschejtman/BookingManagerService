package com.bookingmanagerservice.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

/**
 * Class representing the 'Block' entity.
 * This entity is mapped to a table in the database using JPA annotations.
 * It represents a block of dates for a property during which no bookings can occur.
 */
@Entity // Specifies that the class is an entity and is mapped to a database table.
public class Block {

    @Id // Marks the id field as the primary key.
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Configures the way of increment of the specified column(field).
    private Long id; // Unique identifier for each block.

    @NotNull(message = "Start date cannot be null") // Ensures the start date is not null.
    @Column(nullable = false) // Marks the field as a column in the table with a constraint of not being null.
    private LocalDate startDate; // The start date of the block.

    @NotNull(message = "End date cannot be null") // Ensures the end date is not null.
    @Column(nullable = false) // Marks the field as a column in the table with a constraint of not being null.
    private LocalDate endDate; // The end date of the block.

    @NotBlank(message = "The reason for the block cannot be empty") // Ensures the reason is not blank.
    @Column(length = 500) // Marks the field as a column in the table and specifies the column length.
    private String reason; // The reason for the block.

    /**
     * Empty constructor for use by JPA.
     */
    public Block() {
        // Empty constructor required for JPA
    }

    /**
     * Constructor to create a new block.
     *
     * @param startDate Start date of the block.
     * @param endDate   End date of the block.
     * @param reason    Reason/motive for the block.
     */
    public Block(LocalDate startDate, LocalDate endDate, String reason) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.reason = reason;
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

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    // Overriding equals and hashCode methods...

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Block)) return false;
        Block block = (Block) o;
        return id != null && id.equals(block.id);
    }

    @Override
    public int hashCode() {
        return 31;
    }
}
