package com.bookingmanagerservice.service;

import com.bookingmanagerservice.model.Block;
import com.bookingmanagerservice.repository.BlockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service that manages operations related to blocks.
 * This service class contains business logic for handling blocks, such as creating, deleting, and retrieving block information.
 */
@Service // Marks this class as a service in the Spring context.
public class BlockService {

    private final BlockRepository blockRepository; // Dependency on the repository for block operations.

    /**
     * Constructor for injecting the BlockRepository.
     * This setup uses dependency injection to provide the required repository.
     *
     * @param blockRepository Repository that manages block operations.
     */
    @Autowired // Autowires the BlockRepository dependency.
    public BlockService(BlockRepository blockRepository) {
        this.blockRepository = blockRepository;
    }

    /**
     * Creates and saves a new block in the database.
     * This method can include validations or business logic before saving the block.
     *
     * @param block The block to be saved.
     * @return The saved block.
     */
    public Block createBlock(Block block) {
        // Here, you can add validations or business logic before saving the block.
        return blockRepository.save(block);
    }

    /**
     * Deletes a block based on the provided ID.
     * It checks if the block exists before attempting to delete it.
     *
     * @param id The ID of the block to be deleted.
     * @return true if the block was successfully deleted, false otherwise.
     */
    public boolean deleteBlock(Long id) {
        Optional<Block> block = blockRepository.findById(id);
        if (block.isPresent()) {
            blockRepository.delete(block.get());
            return true;
        }
        return false;
    }

    /**
     * Lists all existing blocks.
     * Retrieves a list of all blocks from the database.
     *
     * @return A list of blocks.
     */
    public List<Block> getAllBlocks() {
        return blockRepository.findAll();
    }

    // Here, add other methods, such as a method to update a block.
}
