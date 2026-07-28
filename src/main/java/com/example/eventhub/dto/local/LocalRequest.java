package com.example.eventhub.dto.local;

public record LocalRequest (
        String nome,
        String cidade,
        String endereco,
        Integer capacidadeMaxima
) {}
