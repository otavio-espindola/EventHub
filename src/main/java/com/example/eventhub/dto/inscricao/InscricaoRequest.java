package com.example.eventhub.dto.inscricao;


public record InscricaoRequest(
        Long participanteId,
        Long eventoId
) {}
