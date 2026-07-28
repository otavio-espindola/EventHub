package com.example.eventhub.dto.local;

public record LocalResponse (
        Long id,
        String nome,
        String cidade,
        String endereco,
        Integer capacidadeMaxima
) {}
