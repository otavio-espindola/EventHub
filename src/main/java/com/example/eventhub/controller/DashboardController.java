package com.example.eventhub.controller;

import com.example.eventhub.dto.dashboard.InscricoesPorMesResponse;
import com.example.eventhub.dto.dashboard.ParticipantesPorEventoResponse;
import com.example.eventhub.dto.evento.EventoResponse;
import com.example.eventhub.model.Categoria;
import com.example.eventhub.service.DashboardService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/dashboard")
@Controller
public class DashboardController {
    private final DashboardService service;

    public DashboardController(DashboardService service){
        this.service = service;
    }

    @GetMapping
    public Long totalEventos(){
        return service.totalEventos();
    }

    @GetMapping
    public Long totalParticipantes(){
        return service.totalParticipantes();
    }

    @GetMapping
    public Long totalInscricoes(){
        return service.totalInscricoes();
    }

    @GetMapping
    public List<EventoResponse> eventosHoje(){
        return service.eventosHoje();
    }

    @GetMapping
    public List<EventoResponse> eventosFuturos(){
        return service.eventosFuturos();
    }

    @GetMapping
    public EventoResponse eventoMaisPopular(){
        return service.eventoMaisPopular();
    }

    @GetMapping
    public Categoria categoriaMaisUsada(){
        return service.categoriaMaisUtilizada();
    }

    @GetMapping
    public List<ParticipantesPorEventoResponse> participantesPorEvento(){
        return service.participantesPorEvento();
    }

    @GetMapping
    public List<InscricoesPorMesResponse> inscricoesPorMes(){
        return service.inscricoesPorMes();
    }
}
