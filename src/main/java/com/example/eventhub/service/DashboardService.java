package com.example.eventhub.service;

import com.example.eventhub.dto.dashboard.DashboardResponse;
import com.example.eventhub.dto.dashboard.InscricoesPorMesResponse;
import com.example.eventhub.dto.dashboard.ParticipantesPorEventoResponse;
import com.example.eventhub.dto.evento.EventoResponse;
import com.example.eventhub.model.Categoria;
import com.example.eventhub.model.Evento;
import com.example.eventhub.model.Local;
import com.example.eventhub.repository.CategoriaRepository;
import com.example.eventhub.repository.EventoRepository;
import com.example.eventhub.repository.InscricaoRepository;
import com.example.eventhub.repository.ParticipanteRepository;
import com.example.eventhub.status.StatusInscricao;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DashboardService {

    private final EventoRepository eventoRepository;
    private final ParticipanteRepository participanteRepository;
    private final InscricaoRepository inscricaoRepository;
    private final CategoriaRepository categoriaRepository;

    public DashboardService(
            EventoRepository Erepository,
            ParticipanteRepository Prepository,
            InscricaoRepository Irepository,
            CategoriaRepository Crepository) {
        this.eventoRepository = Erepository;
        this.inscricaoRepository = Irepository;
        this.participanteRepository = Prepository;
        this.categoriaRepository = Crepository;
    }

    public Long totalEventos() {
        return eventoRepository.count();
    }

    public Long totalParticipantes() {
        return participanteRepository.count();
    }

    public Long totalInscricoes() {
        return inscricaoRepository.count();
    }

    public List<EventoResponse> eventosHoje() {
        return eventoRepository.findByData(LocalDate.now())
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    public List<EventoResponse> eventosFuturos() {

        return eventoRepository.findByDataAfter(LocalDate.now())
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    public EventoResponse eventoMaisPopular() {
        Evento evento = eventoRepository.findAll()
                .stream()
                .max(Comparator.comparingLong(e ->
                        e.getInscricoes()
                                .stream()
                                .filter(i -> i.getStatus() == StatusInscricao.ATIVA)
                                .count()))
                .orElseThrow(() -> new RuntimeException("Nenhum evento encontrado"));
        return toResponseDTO(evento);
    }

    public Categoria categoriaMaisUtilizada() {

        return categoriaRepository.findAll()
                .stream()
                .max(Comparator.comparingInt(c -> c.getEventos().size()))
                .orElseThrow(() -> new RuntimeException("Nenhuma categoria encontrada"));
    }

    public List<ParticipantesPorEventoResponse> participantesPorEvento() {
        return eventoRepository.findAll()
                .stream()
                .map(evento -> new ParticipantesPorEventoResponse(
                        evento.getIdEvento(),
                        evento.getNome(),
                        evento.getInscricoes().stream()
                                .filter(i -> i.getStatus() == StatusInscricao.ATIVA)
                                .count()
                ))
                .toList();
    }

    public List<InscricoesPorMesResponse> inscricoesPorMes() {

        Map<Integer, Long> mapa = inscricaoRepository.findAll()
                .stream()
                .collect(Collectors.groupingBy(
                        i -> i.getDataInscricao().getMonthValue(),
                        Collectors.counting()
                ));

        return mapa.entrySet()
                .stream()
                .map(e -> new InscricoesPorMesResponse(
                        e.getKey(),
                        e.getValue()
                ))
                .toList();
    }

    public DashboardResponse dashboard() {
        return new DashboardResponse(
                totalEventos(),
                eventosHoje(),
                eventosFuturos(),
                totalParticipantes(),
                totalInscricoes(),
                eventoMaisPopular(),
                categoriaMaisUtilizada(),
                participantesPorEvento(),
                inscricoesPorMes()
        );
    }

    private EventoResponse toResponseDTO(Evento evento) {

        int vagasRestantes =
           evento.getLimiteParticipantes() - evento.getInscricoes().size();

        return new EventoResponse(
                evento.getIdEvento(),
                evento.getNome(),
                evento.getDescricao(),
                evento.getData(),
                evento.getHorario(),
                evento.getStatus(),
                evento.getCategoria(),
                evento.getLocal(),
                evento.getLimiteParticipantes(),
                vagasRestantes
        );
    }
}
