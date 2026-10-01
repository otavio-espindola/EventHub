package com.example.eventhub.repository;

import com.example.eventhub.model.Evento;
import com.example.eventhub.model.Inscricao;
import com.example.eventhub.model.Participante;
import com.example.eventhub.status.StatusInscricao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InscricaoRepository extends JpaRepository <Inscricao, Long> {

    boolean existsByParticipanteAndEvento (
            Participante participante,
            Evento evento
            );

    boolean existsByParticipanteAndEventoAndStatus(
            Participante participante,
            Evento evento,
            StatusInscricao status
    );

    long countByEventoAndStatus(
            Evento evento,
            StatusInscricao status
    );
}
