package com.example.eventhub.dto.participante;

public record ParticipanteRequest (
       String nome,
       String cpf,
       String email,
       String telefone
) {}
