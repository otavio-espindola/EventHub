package com.example.eventhub.controller;

import com.example.eventhub.dto.local.*;
import com.example.eventhub.service.LocalService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/local")
public class LocalController {

    private final LocalService localService;

    public LocalController(LocalService localService) {
        this.localService = localService;
    }

    @PostMapping
    public LocalResponse cadastrar(@RequestBody @Valid LocalRequest request) {
        return localService.cadastrar(request);
    }

    @GetMapping
    public List<LocalResponse> listar() {
        return localService.listar();
    }

    @GetMapping("/{id}")
    public LocalResponse buscarPorId(@PathVariable("id") Long id) {
        return localService.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public LocalResponse atualizar(
            @PathVariable("id") Long id,
            @RequestBody @Valid LocalRequest request
    ) {
        return localService.atualizar(id, request);
    }

    @DeleteMapping("/{id}")
    public void excluir(@PathVariable("id") Long id) {
        localService.excluir(id);
    }
}
