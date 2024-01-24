package com.bookingmanagerservice.model;

import jakarta.persistence.*; // Importações JPA para mapeamento objeto-relacional
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

/**
 * Classe representando a entidade Booking (Reserva).
 * Esta entidade é mapeada para uma tabela no banco de dados com JPA.
 * Representa uma reserva, com datas de início e término e detalhes do hóspede.
 */
@Entity // Indica que esta classe é uma entidade JPA.
public class Booking {

    @Id // Marca o campo como chave primária.
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Estratégia de geração de ID.
    private Long id;

    @NotNull(message = "Data de início não pode ser nula") // Validação: a data de início não deve ser nula.
    @Column(nullable = false) // Marca a coluna como não nula no banco de dados.
    private LocalDate startDate;

    @NotNull(message = "Data de término não pode ser nula") // Validação: a data de término não deve ser nula.
    @Column(nullable = false) // Marca a coluna como não nula no banco de dados.
    private LocalDate endDate;

    @NotBlank(message = "Detalhes do hóspede não podem estar vazios") // Validação: detalhes do hóspede não devem estar vazios.
    @Column(length = 200) // Define o tamanho máximo da coluna.
    private String guestDetails;

    /**
     * Construtor vazio para uso pelo JPA.
     */
    public Booking() {
        // Construtor vazio necessário para o funcionamento adequado do JPA.
    }

    /**
     * Construtor para criar uma nova reserva.
     *
     * @param startDate Data de início da reserva.
     * @param endDate Data de término da reserva.
     * @param guestDetails Detalhes do hóspede.
     */
    public Booking(LocalDate startDate, LocalDate endDate, String guestDetails) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.guestDetails = guestDetails;
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

    public String getGuestDetails() {
        return guestDetails;
    }

    public void setGuestDetails(String guestDetails) {
        this.guestDetails = guestDetails;
    }

    // Sobrescrita dos métodos equals e hashCode...

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
