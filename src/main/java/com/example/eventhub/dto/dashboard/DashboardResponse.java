package com.example.eventhub.dto.dashboard;

import com.example.eventhub.dto.evento.EventoResponse;
import com.example.eventhub.model.Categoria;

import java.util.List;

public record DashboardResponse(
        Long totalEventos,
        List<EventoResponse> eventosHoje,
        List<EventoResponse> eventosFuturos,
        Long totalParticipantes,
        Long totalInscricoes,
        EventoResponse eventoMaisPopular,
        Categoria categoriaMaisUtilizada,
        List<ParticipantesPorEventoResponse> participantesPorEvento,
        List<InscricoesPorMesResponse> inscricoesPorMes
) {}
