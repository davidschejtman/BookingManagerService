package com.bookingmanagerservice.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDate;

/**
 * Classe representando a entidade Block (Bloqueio).
 * Esta entidade é mapeada para uma tabela no banco de dados com JPA.
 */
@Entity
public class Block {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Data de início não pode ser nula")
    @Column(nullable = false)
    private LocalDate startDate;

    @NotNull(message = "Data de término não pode ser nula")
    @Column(nullable = false)
    private LocalDate endDate;

    @NotBlank(message = "A razão do bloqueio não pode estar vazia")
    @Column(length = 500)
    private String reason;

    /**
     * Construtor vazio para uso pelo JPA.
     */

    public Block() {
        // Construtor vazio necessário para JPA
    }

    /**
     * Construtor para criar um novo bloqueio.
     *
     * @param startDate Data de início do bloqueio.
     * @param endDate   Data de término do bloqueio.
     * @param reason    Razão/motivo do bloqueio.
     */
    public Block(LocalDate startDate, LocalDate endDate, String reason) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.reason = reason;
    }

    // Getters e setters

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

    // Sobrescrita dos métodos equals e hashCode...

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
