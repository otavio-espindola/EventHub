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
    public void excluir(@PathVariable Long idEvento){
        eventoService.excluir(idEvento);
    }

    @PutMapping("/{id}")
    public EventoResponse atualizar(@PathVariable Long id, @RequestBody EventoRequest request){
        return eventoService.atualizar(id, request);
    }

    @GetMapping("/nome/{nome}")
    public List<EventoResponse> buscarPorNome(@PathVariable String nome){
        return eventoService.buscarPorNome(nome);
    }

    @GetMapping("/{idEvento}")
    public EventoResponse buscarPorId(@PathVariable Long idEvento){
        return eventoService.buscarPorId(idEvento);
    }

    @GetMapping("/categoria/{categoriaId}")
    public List<EventoResponse> buscarPorCategoria(
            @PathVariable Long categoriaId) {
        return eventoService.buscarPorCategoria(categoriaId);
    }

    @GetMapping("/local/{localId}")
    public List<EventoResponse> buscarPorLocal(
            @PathVariable Long localId) {
        return eventoService.buscarPorLocal(localId);
    }

    @GetMapping("/status/{status}")
    public List<EventoResponse> buscarPorStatus(@PathVariable("status") StatusEvento statusEvento){
        return eventoService.buscarPorStatus(statusEvento);
    }

    @GetMapping("/data/{data}")
    public List<EventoResponse> buscarPorData(@PathVariable("data") LocalDate data){
        return eventoService.buscarPorData(data);
    }

    @GetMapping("/ordenar/nome")
    public List<EventoResponse> ordenarNome(@RequestParam(defaultValue = "ASC") Sort.Direction direcao){
        return eventoService.ordenarPorNome(direcao);
    }

    @GetMapping("/ordenar/data")
    public List<EventoResponse> ordenarData(@RequestParam (defaultValue = "ASC") Sort.Direction direcao){
        return eventoService.ordenarPorData(direcao);
    }

    @GetMapping("/ordenar/status")
    public List<EventoResponse> ordenarStatus(@RequestParam(defaultValue = "ASC") Sort.Direction direcao){
        return eventoService.ordenarPorStatus(direcao);
    }
}
