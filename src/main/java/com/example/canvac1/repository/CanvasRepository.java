package com.example.canvac1.repository;

import com.example.canvac1.model.Canvas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CanvasRepository extends JpaRepository<Canvas, Long> {
    Optional<Canvas> findById(Long id);
    Optional<Canvas> findByNameAndUsername(String name, String username);
    Optional<Canvas> findByUsernameAndId(String username, Long id);
    List<Canvas> findByUsername(String username);
}
