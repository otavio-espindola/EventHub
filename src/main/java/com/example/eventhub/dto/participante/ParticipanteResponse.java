package com.example.eventhub.dto.participante;

public record ParticipanteResponse (
        Long id,
        String nome,
        String cpf,
        String email,
        String telefone
) {
}
