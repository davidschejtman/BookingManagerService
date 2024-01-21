package com.bookingmanagerservice.service;

import com.bookingmanagerservice.model.Block;
import com.bookingmanagerservice.repository.BlockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Serviço que gerencia operações relacionadas a bloqueios.
 */
@Service
public class BlockService {

    private final BlockRepository blockRepository;

    @Autowired
    public BlockService(BlockRepository blockRepository) {
        this.blockRepository = blockRepository;
    }

    /**
     * Cria e salva um novo bloqueio no banco de dados.
     * @param block O bloqueio a ser salvo.
     * @return O bloqueio salvo.
     */
    public Block createBlock(Block block) {
        // Aqui você pode adicionar lógica de negócios antes de salvar o bloqueio
        return blockRepository.save(block);
    }

    /**
     * Deleta um bloqueio com base no ID fornecido.
     * @param id O ID do bloqueio a ser deletado.
     * @return true se o bloqueio foi deletado com sucesso, false caso contrário.
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
     * Lista todos os bloqueios existentes.
     * @return Uma lista de bloqueios.
     */
    public List<Block> getAllBlocks() {
        return blockRepository.findAll();
    }

    // Aqui, você pode adicionar outros métodos conforme necessário, como atualizar um bloqueio.
}
