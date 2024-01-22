package com.bookingmanagerservice.repository;

import com.bookingmanagerservice.model.Block;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositório JPA para a entidade Block.
 * Esta interface gerencia as operações de banco de dados para a entidade Block.
 */
@Repository
public interface BlockRepository extends JpaRepository<Block, Long> {
    // Aqui você pode adicionar consultas personalizadas se necessário
    // Exemplos:
    // List<Block> findByStartDate(LocalDate startDate);
    // List<Block> findByEndDate(LocalDate endDate);
}
