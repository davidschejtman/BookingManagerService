package com.bookingmanagerservice.controller;

import com.bookingmanagerservice.model.Block;
import com.bookingmanagerservice.service.BlockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid; // Garanta que esta importação está presente para @Valid
import java.util.List;

/**
 * Controlador REST para gerenciar os bloqueios de propriedades.
 */
@RestController
@RequestMapping("/blocks")
public class BlockController {

    private final BlockService blockService;

    /**
     * Construtor para injeção do serviço de bloqueios.
     *
     * @param blockService Serviço que gerencia as operações de bloqueio.
     */
    @Autowired
    public BlockController(BlockService blockService) {
        this.blockService = blockService;
    }

    /**
     * Cria um novo bloqueio.
     *
     * @param block Objeto Block contendo informações sobre o bloqueio.
     * @return ResponseEntity com o bloqueio criado e status CREATED.
     */
    @PostMapping
    public ResponseEntity<Block> createBlock(@Valid @RequestBody Block block) {
        Block savedBlock = blockService.createBlock(block);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedBlock);
    }

    /**
     * Método para deletar um bloqueio existente.
     *
     * @param id O ID do bloqueio que está sendo deletado.
     * @return ResponseEntity indicando o resultado da operação.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBlock(@PathVariable Long id) {
        boolean isDeleted = blockService.deleteBlock(id);
        if (!isDeleted) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
    }

    /**
     * Método para listar todos os bloqueios.
     *
     * @return Uma lista de todos os bloqueios e uma ResponseEntity.
     */
    @GetMapping
    public ResponseEntity<List<Block>> getAllBlocks() {
        List<Block> blocks = blockService.getAllBlocks();
        return ResponseEntity.ok(blocks);
    }
}
