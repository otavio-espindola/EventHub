package com.example.eventhub.dto.evento;

import com.example.eventhub.model.Categoria;
import com.example.eventhub.model.Local;
import com.example.eventhub.status.StatusEvento;
import java.time.LocalDate;
import java.time.LocalTime;

public record EventoResponse(
        Long id,
        String nome,
        String descricao,
        LocalDate data,
        LocalTime horario,
        StatusEvento status,
        Categoria categoria,
        Local local,
        Integer limiteParticipantes,
        Integer vagasRestantes
        )
{}
