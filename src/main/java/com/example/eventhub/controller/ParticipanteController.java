package com.example.eventhub.controller;

import com.example.eventhub.dto.evento.EventoRequest;
import com.example.eventhub.dto.participante.ParticipanteRequest;
import com.example.eventhub.dto.participante.ParticipanteResponse;
import com.example.eventhub.model.Participante;
import com.example.eventhub.service.ParticipanteService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/participante")
public class ParticipanteController {
    private final ParticipanteService participanteService;

    public ParticipanteController(ParticipanteService service) {
        this.participanteService = service;
    }

    @PostMapping
    public ParticipanteResponse cadastrar(@RequestBody @Valid ParticipanteRequest request) {
        return participanteService.cadastrar(request);
    }

    @GetMapping
    public List<ParticipanteResponse> listar(){
        return participanteService.listar();
    }

    @DeleteMapping("/{id}")
    public void excluir(Long idParticipante){
        participanteService.excluir(idParticipante);
    }

    @PutMapping("/{id}")
    public ParticipanteResponse atualizar(@PathVariable Long idParticipante, @RequestBody ParticipanteRequest request){
        return participanteService.atualizar(idParticipante, request);
    }

    @GetMapping("/{id}")
    public ParticipanteResponse buscarPorId(Long idParticipante){
        return participanteService.buscarPorId(idParticipante);
    }

    @GetMapping("/nome/{nome}")
    public List<ParticipanteResponse> buscarPorParticipante(String nome){
        return participanteService.buscarPorNomeParticipante(nome);
    }

    @GetMapping("/cpf/{cpf}")
    public List<ParticipanteResponse> buscarPorCPF(String cpf){
        return participanteService.buscarPorCpf(cpf);
    }

    @GetMapping("/email/{email}")
    public List<ParticipanteResponse> buscarPorEmail(String email){
        return participanteService.buscarPorEmail(email);
    }

    @GetMapping("/ordenar/{nome}")
    public List<ParticipanteResponse> ordenarPorNome(@RequestParam Sort.Direction direcao){
        return participanteService.OrdenarNome(direcao);
    }

    @GetMapping("/ordenar/{email}")
    public List<ParticipanteResponse> ordenarPorEmail(@RequestParam Sort.Direction direcao){
        return participanteService.OrdenarEmail(direcao);
    }
}