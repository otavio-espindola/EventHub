package com.example.eventhub.service;

import com.example.eventhub.dto.participante.*;
import com.example.eventhub.model.*;
import com.example.eventhub.repository.InscricaoRepository;
import com.example.eventhub.repository.ParticipanteRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParticipanteService {

    private final ParticipanteRepository repository;
    private final InscricaoRepository Irepository;

    public ParticipanteService(ParticipanteRepository repository, InscricaoRepository Irepository){
        this.repository = repository;
        this.Irepository = Irepository;

    }
    //CRUD
    public ParticipanteResponse cadastrar(ParticipanteRequest request){
            Participante participante = new Participante();

            participante.setCpf(request.cpf());
            participante.setNome(request.nome());
            participante.setEmail(request.email());
            participante.setTelefone(request.telefone());

            Participante salvo = repository.save(participante);
            return toResponseDTO(salvo);
        }

    private ParticipanteResponse toResponseDTO(Participante participante) {

        return new ParticipanteResponse(
                participante.getIdParticipante(),
                participante.getNome(),
                participante.getCpf(),
                participante.getEmail(),
                participante.getTelefone()
        );
    }

    public List<ParticipanteResponse> listar(){
        return repository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    public ParticipanteResponse buscarPorId (Long id){
        Participante participante = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Evento não encontrado"));
        return toResponseDTO(participante);
    }

    public void excluir(Long id){
        repository.deleteById(id);
    }

    public ParticipanteResponse atualizar(Long id, ParticipanteRequest request) {

        Participante existente = buscarParticipante(id);

        existente.setNome(request.nome());
        existente.setTelefone(request.telefone());
        existente.setCpf(request.cpf());
        existente.setEmail(request.email());

        Participante atualizado = repository.save(existente);

        return toResponseDTO(atualizado);
    }

    //BUSCAS

    private Participante buscarParticipante(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Evento não encontrado"));
    }

    public List<ParticipanteResponse> buscarPorNomeParticipante(String nome){
        return repository.findByNomeContainingIgnoreCase(nome)
            .stream()
                .map(this::toResponseDTO)
                .toList();
}
    public  List<ParticipanteResponse> buscarPorCpf(String cpf){
        return repository.findByCpfContainingIgnoreCase(cpf)
            .stream()
            .map(this::toResponseDTO)
            .toList();
    }

    public  List<ParticipanteResponse> buscarPorEmail(String email){
        return repository.findByEmailContainingIgnoreCase(email)
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    public List<ParticipanteResponse> OrdenarNome(Sort.Direction direction){
        return repository.findAll(Sort.by(direction, "nome"))
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }
    public List<ParticipanteResponse> OrdenarEmail(Sort.Direction direction){
        return repository.findAll(Sort.by(direction, "email"))
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }
}

