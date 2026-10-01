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
    public void excluir(@PathVariable("id") Long idParticipante){
        participanteService.excluir(idParticipante);
    }

    @PutMapping("/{id}")
    public ParticipanteResponse atualizar(@PathVariable("id") Long idParticipante, @RequestBody ParticipanteRequest request){
        return participanteService.atualizar(idParticipante, request);
    }

    @GetMapping("/{id}")
    public ParticipanteResponse buscarPorId(@PathVariable("id") Long idParticipante){
        return participanteService.buscarPorId(idParticipante);
    }

    @GetMapping("/buscar/nome/{nome}")
    public List<ParticipanteResponse> buscarPorParticipante(
            @PathVariable("nome") String nome) {
        return participanteService.buscarPorNomeParticipante(nome);
    }

    @GetMapping("/buscar/cpf/{cpf}")
    public List<ParticipanteResponse> buscarPorCPF(
            @PathVariable("cpf") String cpf) {
        return participanteService.buscarPorCpf(cpf);
    }

    @GetMapping("/buscar/email/{email}")
    public List<ParticipanteResponse> buscarPorEmail(
            @PathVariable("email") String email) {
        return participanteService.buscarPorEmail(email);
    }

    @GetMapping("/ordenar/nome")
    public List<ParticipanteResponse> ordenarPorNome(
            @RequestParam(name = "direcao", defaultValue = "ASC")
            Sort.Direction direcao) {
        return participanteService.OrdenarNome(direcao);
    }

    @GetMapping("/ordenar/email")
    public List<ParticipanteResponse> ordenarPorEmail(
            @RequestParam(name = "direcao", defaultValue = "ASC")
            Sort.Direction direcao) {
        return participanteService.OrdenarEmail(direcao);
    }
}