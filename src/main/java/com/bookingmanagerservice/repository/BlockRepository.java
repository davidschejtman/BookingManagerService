package com.bookingmanagerservice.repository;

import com.bookingmanagerservice.model.Block;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlockRepository extends JpaRepository<Block, Long> {
    // Aqui você pode adicionar consultas personalizadas se necessário
}
