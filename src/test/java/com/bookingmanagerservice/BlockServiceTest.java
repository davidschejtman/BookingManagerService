package com.bookingmanagerservice;

import com.bookingmanagerservice.model.Block;
import com.bookingmanagerservice.repository.BlockRepository;
import com.bookingmanagerservice.service.BlockService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Optional;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class BlockServiceTest {

    @Mock
    private BlockRepository blockRepository;

    @InjectMocks
    private BlockService blockService;

    private Block block;

    @BeforeEach
    public void setup() {
        block = new Block(LocalDate.now(), LocalDate.now().plusDays(10), "Maintenance");
    }

    @Test
    public void createBlock_Success() {
        when(blockRepository.save(any(Block.class))).thenReturn(block);
        Block savedBlock = blockService.createBlock(block);
        assertEquals(block, savedBlock);
        verify(blockRepository, times(1)).save(block);
    }

    @Test
    public void deleteBlock_NotFound() {
        Long blockId = 1L;
        when(blockRepository.findById(blockId)).thenReturn(Optional.empty());
        assertFalse(blockService.deleteBlock(blockId));
        verify(blockRepository, never()).delete(any(Block.class));
    }

    // More tests can be added here
}