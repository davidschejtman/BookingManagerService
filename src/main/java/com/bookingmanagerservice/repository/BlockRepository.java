package com.bookingmanagerservice.repository;

import com.bookingmanagerservice.model.Block;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface BlockRepository extends JpaRepository<Block, Long> {

    // Encontrar bloqueios que começam em uma data específica
    List<Block> findByStartDate(LocalDate startDate);

    // Encontrar bloqueios que terminam em uma data específica
    List<Block> findByEndDate(LocalDate endDate);

    // Encontrar bloqueios que ocorrem dentro de um intervalo de datas
    @Query("SELECT b FROM Block b WHERE b.startDate <= :endDate AND b.endDate >= :startDate")
    List<Block> findBlocksInDateRange(LocalDate startDate, LocalDate endDate);

    // Encontrar bloqueios que se sobrepõem a um intervalo de datas
    // Isso inclui bloqueios que começam antes e terminam dentro do intervalo,
    // começam dentro e terminam depois ou totalmente dentro do intervalo
    @Query("SELECT b FROM Block b WHERE b.startDate < :endDate AND b.endDate > :startDate")
    List<Block> findOverlappingBlocks(LocalDate startDate, LocalDate endDate);
}
