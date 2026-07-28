package com.example.eventhub.controller;

import com.example.eventhub.dto.evento.*;
import com.example.eventhub.model.Categoria;
import com.example.eventhub.service.EventoService;
import com.example.eventhub.status.StatusEvento;
import jakarta.validation.Valid;
import com.example.eventhub.model.Local;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;


@RestController
@RequestMapping("/eventos")

public class EventoController {

    private final EventoService eventoService;

    public EventoController(EventoService service){
        this.eventoService = service;
    }

    @PostMapping
    public EventoResponse cadastrar(@RequestBody @Valid EventoRequest dto){
        return eventoService.cadastrar(dto);
    }

    @GetMapping
    public List<EventoResponse> listar(){
        return eventoService.listar();
    }

    @DeleteMapping("/{id}")
    public void excluir(@RequestParam Long idEvento){
        eventoService.excluir(idEvento);
    }

    @PutMapping("/{id}")
    public EventoResponse atualizar(@PathVariable Long id, @RequestBody EventoRequest request){
        return eventoService.atualizar(id, request);
    }

    @GetMapping("/nome/{nome}")
    public List<EventoResponse> buscarPorNome(String nome){
        return eventoService.buscarPorNome(nome);
    }

    @GetMapping("/{idEvento}")
    public EventoResponse buscarPorId(Long idEvento){
        return eventoService.buscarPorId(idEvento);
    }

    @GetMapping("/categoria")
    public List<EventoResponse> buscarPorCategoria(Categoria categoria){
        return eventoService.buscarPorCategoria(categoria);
    }

    @GetMapping("/local")
    public List<EventoResponse> buscarPorLocal(Local local){
        return eventoService.buscarPorLocal(local);
    }

    @GetMapping("/status")
    public List<EventoResponse> buscarPorStatus(StatusEvento statusEvento){
        return eventoService.buscarPorStatus(statusEvento);
    }

    @GetMapping("/data")
    public List<EventoResponse> buscarPorData(LocalDate data){
        return eventoService.buscarPorData(data);
    }

    @GetMapping("/ordenar/nome")
    public List<EventoResponse> ordenarNome(@RequestParam Sort.Direction direcao){
        return eventoService.ordenarPorNome(direcao);
    }

    @GetMapping("/ordenar/data")
    public List<EventoResponse> ordenarData(@RequestParam Sort.Direction direcao){
        return eventoService.ordenarPorData(direcao);
    }

    @GetMapping("/ordenar/status")
    public List<EventoResponse> ordenarStatus(@RequestParam Sort.Direction direcao){
        return eventoService.ordenarPorStatus(direcao);
    }










}
