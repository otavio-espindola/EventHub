package com.example.eventhub.service;

import com.example.eventhub.dto.evento.*;
import com.example.eventhub.model.*;
import com.example.eventhub.repository.*;
import com.example.eventhub.status.StatusEvento;
import com.example.eventhub.status.StatusInscricao;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class EventoService {

    private final EventoRepository repository;
    private final CategoriaRepository categoriaRepository;
    private final LocalRepository localRepository;

    public EventoService(EventoRepository repository, CategoriaRepository Crepository, LocalRepository Lrepository) {
        this.repository = repository;
        this.localRepository = Lrepository;
        this.categoriaRepository = Crepository;
    }

    public EventoResponse cadastrar(EventoRequest request) {
        Evento evento = new Evento();

        Categoria categoria = categoriaRepository.findById(request.categoriaId())
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));

        Local local = localRepository.findById(request.localId())
                .orElseThrow(() -> new RuntimeException("Local não encontrado"));

        evento.setCategoria(categoria);
        evento.setData(request.data());
        evento.setDescricao(request.descricao());
        evento.setHorario(request.horario());
        evento.setLocal(local);
        evento.setStatus(StatusEvento.ABERTO);
        evento.setLimiteParticipantes(request.limiteParticipantes());
        evento.setNome(request.nome());

        if (repository.existsByLocalAndDataAndHorario(local, request.data(), request.horario())) {
            throw new RuntimeException("Já existe um evento nesse local, data e horário.");
        }

        Evento salvo = repository.save(evento);
        return toResponseDTO(salvo);
    }

    private EventoResponse toResponseDTO(Evento evento) {

        long inscricoesAtivas =
                evento.getInscricoes()
                        .stream()
                        .filter(i -> i.getStatus() == StatusInscricao.ATIVA)
                        .count();
        int vagasRestantes =
                evento.getLimiteParticipantes() - Math.toIntExact(inscricoesAtivas);

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

    public List<EventoResponse> listar(){
        return repository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    public EventoResponse buscarPorId (Long id){
        Evento evento = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Evento não encontrado"));
        return toResponseDTO(evento);
    }

    public void excluir(Long id){
         repository.deleteById(id);
    }

    public EventoResponse atualizar(Long id, EventoRequest request) {

        Evento existente = buscarEvento(id);

        Categoria categoria = categoriaRepository.findById(request.categoriaId())
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));

        Local local = localRepository.findById(request.localId())
                .orElseThrow(() -> new RuntimeException("Local não encontrado"));

        if (repository.existsByLocalAndDataAndHorarioAndIdEventoNot(
                local,
                request.data(),
                request.horario(),
                id
        )) {
            throw new RuntimeException("Já existe um evento nesse local, data e horário.");
        }
        existente.setNome(request.nome());
        existente.setDescricao(request.descricao());
        existente.setData(request.data());
        existente.setHorario(request.horario());
        existente.setLimiteParticipantes(request.limiteParticipantes());
        existente.setCategoria(categoria);
        existente.setLocal(local);

        Evento atualizado = repository.save(existente);

        return toResponseDTO(atualizado);
    }

    private Evento buscarEvento(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Evento não encontrado"));
    }

    public List<EventoResponse> buscarPorNome (String nome){

        return repository.findByNomeContainingIgnoreCase(nome)
                .stream()
                .map(this::toResponseDTO)
                .toList();
    };

    public List<EventoResponse> buscarPorCategoria (Categoria categoria){

        return repository.findByCategoriaContainingIgnoreCase(categoria)
                .stream()
                .map(this::toResponseDTO)
                .toList();
    };

    public List<EventoResponse> buscarPorLocal (Local local){
        return repository.findByLocalContainingIgnoreCase(local)
                .stream()
                .map(this::toResponseDTO)
                .toList();
    };

    public List<EventoResponse> buscarPorStatus (StatusEvento statusEvento){
        return repository.findByStatus(statusEvento)
                .stream()
                .map(this::toResponseDTO)
                .toList();
    };

    public List<EventoResponse> buscarPorData (LocalDate data){
        return repository.findByData(data)
                .stream()
                .map(this::toResponseDTO)
                .toList();
    };

    public List<EventoResponse> ordenarPorNome(Sort.Direction direction) {
        return repository.findAll(Sort.by(direction, "nome"))
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    public List<EventoResponse> ordenarPorData(Sort.Direction direction) {
        return repository.findAll(Sort.by(direction, "data"))
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    public List<EventoResponse> ordenarPorStatus(Sort.Direction direction) {
        return repository.findAll(Sort.by(direction, "status"))
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }
}
