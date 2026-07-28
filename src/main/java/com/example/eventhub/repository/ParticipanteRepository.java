package com.example.eventhub.repository;

import com.example.eventhub.model.Participante;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ParticipanteRepository extends JpaRepository <Participante, Long> {

    List<Participante> findByNomeContainingIgnoreCase(String nome);
    List<Participante> findByCpfContainingIgnoreCase(String cpf);
    List<Participante> findByEmailContainingIgnoreCase(String email);


}
