package com.example.eventhub.dto.evento;

import java.time.LocalDate;
import java.time.LocalTime;

public record EventoRequest(
        String nome,
        String descricao,
        LocalDate data,
        Integer limiteParticipantes,
        LocalTime horario,
        Long categoriaId,
        Long localId

) {
}
