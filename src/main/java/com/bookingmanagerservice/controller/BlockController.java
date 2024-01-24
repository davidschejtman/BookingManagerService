package com.bookingmanagerservice.controller;

import com.bookingmanagerservice.model.Block;
import com.bookingmanagerservice.service.BlockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid; // Ensure this import is present for @Valid
import java.util.List;

/**
 * REST controller for managing property blocks.
 * This controller provides endpoints for creating, deleting, and retrieving block entities.
 */
@RestController // Marks this class as a REST controller, meaning it's ready for use by Spring MVC to handle web requests.
@RequestMapping("/blocks") // Maps HTTP requests to handler methods of MVC and REST controllers.
public class BlockController {

    private final BlockService blockService; // Service layer dependency for block operations.

    /**
     * Constructor for dependency injection of block service.
     *
     * @param blockService Service that manages block operations.
     */
    @Autowired // Automatically injects the BlockService instance created by Spring.
    public BlockController(BlockService blockService) {
        this.blockService = blockService;
    }

    /**
     * Creates a new block.
     * This method will handle the POST request on "/blocks" endpoint.
     *
     * @param block Object Block containing information about the block.
     * @return ResponseEntity with the created block and HTTP status CREATED.
     */
    @PostMapping // Maps HTTP POST requests onto specific handler methods.
    public ResponseEntity<Block> createBlock(@Valid @RequestBody Block block) {
        Block savedBlock = blockService.createBlock(block); // Calls the service layer to save the block.
        return ResponseEntity.status(HttpStatus.CREATED).body(savedBlock); // Returns a response entity with CREATED status and saved block.
    }

    /**
     * Deletes an existing block.
     * This method will handle the DELETE request on "/blocks/{id}" endpoint.
     *
     * @param id The ID of the block being deleted.
     * @return ResponseEntity indicating the result of the operation.
     */
    @DeleteMapping("/{id}") // Maps HTTP DELETE requests onto specific handler methods.
    public ResponseEntity<Void> deleteBlock(@PathVariable Long id) {
        boolean isDeleted = blockService.deleteBlock(id); // Calls the service layer to delete the block.
        if (!isDeleted) {
            return ResponseEntity.notFound().build(); // Returns a NOT FOUND response if the block is not deleted.
        }
        return ResponseEntity.ok().build(); // Returns an OK response if the block is successfully deleted.
    }

    /**
     * Lists all blocks.
     * This method will handle the GET request on "/blocks" endpoint.
     *
     * @return A list of all blocks and a ResponseEntity.
     */
    @GetMapping // Maps HTTP GET requests onto specific handler methods.
    public ResponseEntity<List<Block>> getAllBlocks() {
        List<Block> blocks = blockService.getAllBlocks(); // Calls the service layer to retrieve all blocks.
        return ResponseEntity.ok(blocks); // Returns an OK response with the list of blocks.
    }
}
