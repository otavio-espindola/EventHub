package com.example.eventhub.repository;

import com.example.eventhub.model.Local;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocalRepository extends JpaRepository <Local, Long> {
}
