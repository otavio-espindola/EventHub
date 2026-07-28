package com.example.eventhub.repository;

import com.example.eventhub.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository <Categoria, Long> {
}
