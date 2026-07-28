package com.example.eventhub.service;

import com.example.eventhub.dto.evento.EventoRequest;
import com.example.eventhub.dto.evento.EventoResponse;
import com.example.eventhub.dto.inscricao.InscricaoRequest;
import com.example.eventhub.dto.inscricao.InscricaoResponse;
import com.example.eventhub.model.*;
import com.example.eventhub.repository.EventoRepository;
import com.example.eventhub.repository.InscricaoRepository;
import com.example.eventhub.repository.ParticipanteRepository;
import com.example.eventhub.status.StatusEvento;
import com.example.eventhub.status.StatusInscricao;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class InscricaoService {

    private final InscricaoRepository repository;
    private final EventoRepository eventoRepository;
    private final ParticipanteRepository participanteRepository;

    public InscricaoService(
            InscricaoRepository repository,
            EventoRepository eventoRepository,
            ParticipanteRepository participanteRepository
    ){
        this.repository = repository;
        this.eventoRepository = eventoRepository;
        this.participanteRepository = participanteRepository;
    }

    public InscricaoResponse cadastrar(InscricaoRequest request) {
        Inscricao inscricao = new Inscricao();

        Participante participante = participanteRepository.findById(request.participanteId())
                .orElseThrow(() -> new RuntimeException("Participante não encontrado"));

        Evento evento = eventoRepository.findById(request.eventoId())
                .orElseThrow(() -> new RuntimeException("Evento não encontrado"));

        if(evento.getStatus() != StatusEvento.ABERTO){
            throw new RuntimeException("Evento encerrado ou cancelado");
        }

        if(evento.getInscricoes().size() >= evento.getLimiteParticipantes()){
            throw new RuntimeException("Evento sem vagas disponíveis");
        }

        if(repository.existsByParticipanteAndEvento(participante, evento)){
            throw new RuntimeException("Participante já inscrito neste evento");
        }

        inscricao.setParticipante(participante);
        inscricao.setEvento(evento);
        inscricao.setDataInscricao(LocalDate.now());
        inscricao.setStatus(StatusInscricao.ATIVA);

        Inscricao salvo = repository.save(inscricao);
        return toResponseDTO (salvo);

}

    private InscricaoResponse toResponseDTO(Inscricao inscricao) {
        return new InscricaoResponse(
                inscricao.getIdInscricao(),
                inscricao.getDataInscricao(),
                inscricao.getStatus(),
                inscricao.getParticipante(),
                inscricao.getEvento()
        );
    }
    public void cancelarInscricao(Long id){

        Inscricao inscricao = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Inscrição não encontrada"));

        inscricao.setStatus(StatusInscricao.CANCELADA);
        repository.save(inscricao);
    }

    public List<InscricaoResponse> listar(){
        return repository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    public InscricaoResponse buscarPorId(Long id){
        Inscricao inscricao = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Inscrição não encontrada"));
        return toResponseDTO(inscricao);
    }
}
