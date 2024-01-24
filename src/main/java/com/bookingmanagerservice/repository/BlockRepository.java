package com.bookingmanagerservice.repository;

import com.bookingmanagerservice.model.Block;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

/**
 * JPA Repository for the Block entity.
 * This interface manages database operations for the Block entity,
 * which represents periods when a property is unavailable for booking.
 */
@Repository // Indicates that this interface is a repository component in the Spring framework.
public interface BlockRepository extends JpaRepository<Block, Long> {

    /**
     * Finds blocks that start on a specific date.
     *
     * @param startDate The start date of the blocks to be found.
     * @return List of blocks that start on the specified date.
     */
    List<Block> findByStartDate(LocalDate startDate);

    /**
     * Finds blocks that end on a specific date.
     *
     * @param endDate The end date of the blocks to be found.
     * @return List of blocks that end on the specified date.
     */
    List<Block> findByEndDate(LocalDate endDate);

    /**
     * Finds blocks that occur within a date range.
     *
     * @param startDate The start date of the search range.
     * @param endDate   The end date of the search range.
     * @return List of blocks within the specified date range.
     */
    @Query("SELECT b FROM Block b WHERE b.startDate <= :endDate AND b.endDate >= :startDate")
    List<Block> findBlocksInDateRange(LocalDate startDate, LocalDate endDate);

    /**
     * Finds blocks that overlap with a date range.
     * This includes blocks that start before and end within the range,
     * start within and end after, or are entirely within the range.
     *
     * @param startDate The start date of the search range.
     * @param endDate   The end date of the search range.
     * @return List of blocks that overlap with the specified range.
     */
    @Query("SELECT b FROM Block b WHERE b.startDate < :endDate AND b.endDate > :startDate")
    List<Block> findOverlappingBlocks(LocalDate startDate, LocalDate endDate);
}
