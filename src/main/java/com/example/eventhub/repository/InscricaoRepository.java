package com.example.eventhub.repository;

import com.example.eventhub.model.Evento;
import com.example.eventhub.model.Inscricao;
import com.example.eventhub.model.Participante;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InscricaoRepository extends JpaRepository <Inscricao, Long> {

    boolean existsByParticipanteAndEvento (
            Participante participante,
            Evento evento
            );
}
