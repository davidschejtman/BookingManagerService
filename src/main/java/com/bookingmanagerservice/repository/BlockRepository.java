package com.bookingmanagerservice.repository;

import com.bookingmanagerservice.model.Block;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

/**
 * Repositório JPA para a entidade Block.
 * Esta interface gerencia as operações de banco de dados para a entidade Block,
 * que representa períodos de tempo durante os quais uma propriedade está indisponível para reserva.
 */
@Repository
public interface BlockRepository extends JpaRepository<Block, Long> {

    /**
     * Encontra bloqueios que começam em uma data específica.
     *
     * @param startDate A data de início dos bloqueios a serem encontrados.
     * @return Lista de bloqueios que começam na data especificada.
     */
    List<Block> findByStartDate(LocalDate startDate);

    /**
     * Encontra bloqueios que terminam em uma data específica.
     *
     * @param endDate A data de término dos bloqueios a serem encontrados.
     * @return Lista de bloqueios que terminam na data especificada.
     */
    List<Block> findByEndDate(LocalDate endDate);

    /**
     * Encontra bloqueios que ocorrem dentro de um intervalo de datas.
     *
     * @param startDate Data de início do intervalo de busca.
     * @param endDate Data de término do intervalo de busca.
     * @return Lista de bloqueios dentro do intervalo de datas especificado.
     */
    @Query("SELECT b FROM Block b WHERE b.startDate <= :endDate AND b.endDate >= :startDate")
    List<Block> findBlocksInDateRange(LocalDate startDate, LocalDate endDate);

    /**
     * Encontra bloqueios que se sobrepõem a um intervalo de datas.
     * Isso inclui bloqueios que começam antes e terminam dentro do intervalo,
     * começam dentro e terminam depois, ou totalmente dentro do intervalo.
     *
     * @param startDate Data de início do intervalo de busca.
     * @param endDate Data de término do intervalo de busca.
     * @return Lista de bloqueios que se sobrepõem ao intervalo especificado.
     */
    @Query("SELECT b FROM Block b WHERE b.startDate < :endDate AND b.endDate > :startDate")
    List<Block> findOverlappingBlocks(LocalDate startDate, LocalDate endDate);
}
