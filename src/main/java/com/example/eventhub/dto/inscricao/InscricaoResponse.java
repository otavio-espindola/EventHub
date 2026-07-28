package com.example.eventhub.dto.inscricao;

import com.example.eventhub.model.Evento;
import com.example.eventhub.model.Participante;
import com.example.eventhub.status.StatusInscricao;
import java.time.LocalDate;

public record InscricaoResponse (
        Long idInscricao,
        LocalDate dataInscricao,
        StatusInscricao status,
        Participante participante,
        Evento evento
) {}
