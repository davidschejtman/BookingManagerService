package com.bookingmanagerservice.controller;

import com.bookingmanagerservice.model.Block;
import com.bookingmanagerservice.service.BlockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

/**
 * Controlador REST para gerenciar os bloqueios de propriedades.
 */
@RestController
@RequestMapping("/blocks")
public class BlockController {

    private final BlockService blockService;
    /**
     * Construtor para injeção do serviço de bloqueios.
     * @param blockService Serviço que gerencia as operações de bloqueio.
     */
    @Autowired
    public BlockController(BlockService blockService) {
        this.blockService = blockService;
    }
    /**
     * Cria um novo bloqueio.
     * @param block Objeto Block contendo informações sobre o bloqueio.
     * @return ResponseEntity com o bloqueio criado e status CREATED.
     */
    @PostMapping
    public ResponseEntity<Block> createBlock(@Valid @RequestBody Block block) {
        Block savedBlock = blockService.createBlock(block);
        // Retorna o bloqueio salvo com status CREATED (201).
        return new ResponseEntity<>(savedBlock, HttpStatus.CREATED);
    }
    /**
     * Método para deletar um bloqueio existente.
     * A anotação @DeleteMapping mapeia solicitações DELETE para /blocks/{id}.
     * @param id O ID do bloqueio que está sendo deletado.
     * @return Uma ResponseEntity indicando o resultado da operação.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBlock(@PathVariable Long id) {
        boolean isDeleted = blockService.deleteBlock(id);
        if (!isDeleted) {
            // Se o serviço retorna false, o bloqueio com o ID fornecido não foi encontrado ou não pôde ser deletado.
            return ResponseEntity.notFound().build();
        }
        // Se o bloqueio foi deletado com sucesso, retorna um status OK (200) sem conteúdo.
        return ResponseEntity.ok().build();
    }
    /**
     * Método para listar todos os bloqueios.
     * A anotação @GetMapping mapeia solicitações GET para /blocks.
     * @return Uma lista de todos os bloqueios e uma ResponseEntity.
     */
    @GetMapping
    public ResponseEntity<List<Block>> getAllBlocks() {
        List<Block> blocks = blockService.getAllBlocks();
        // Retorna a lista de bloqueios com status OK (200).
        return ResponseEntity.ok(blocks);
    }


}
