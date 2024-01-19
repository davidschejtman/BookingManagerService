package com.bookingmanagerservice.service;

import com.bookingmanagerservice.model.Block;
import com.bookingmanagerservice.repository.BlockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BlockService {

    private final BlockRepository blockRepository;

    @Autowired
    public BlockService(BlockRepository blockRepository) {
        this.blockRepository = blockRepository;
    }

    // Métodos para criar, atualizar e deletar bloqueios
}
