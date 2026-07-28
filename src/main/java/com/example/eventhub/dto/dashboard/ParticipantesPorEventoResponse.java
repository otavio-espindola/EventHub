package com.example.eventhub.dto.dashboard;

public record ParticipantesPorEventoResponse(
        Long idEvento,
        String nomeEvento,
        Long quantidadeParticipantes
) {

}
