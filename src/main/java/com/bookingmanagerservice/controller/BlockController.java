package com.bookingmanagerservice.controller;

import com.bookingmanagerservice.service.BlockService;

@RestController
@RequestMapping("/Blocks")

public class BlockController {
    private final BlockService blockService;

    @Autowired
    public BlockController(BlockService blockService) {
        this.BlockService = BlockService;

    }

    // Obter todas as reservas
    @GetMapping
    public List<Block> getAllBlocks() {
        return BlockService.getAllBlocks();
    }

    // Criar uma nova reserva
    @PostMapping
    public Block addBlock(@RequestBody Block Block) {
        return BlockService.addBlock(Block);
    }

    // Atualizar uma reserva existente
    @PutMapping("/{id}")
    public Block updateBlock(@PathVariable Long id, @RequestBody Block Block) {
        return BlockService.updateBlock(id, Block);
    }

    // Deletar uma reserva
    @DeleteMapping("/{id}")
    public void deleteBlock(@PathVariable Long id) {
        BlockService.deleteBlock(id);
    }

}
