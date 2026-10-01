package com.example.eventhub.controller;

import com.example.eventhub.dto.inscricao.InscricaoRequest;
import com.example.eventhub.dto.inscricao.InscricaoResponse;
import com.example.eventhub.service.InscricaoService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inscricao")
public class InscricaoController {

    private final InscricaoService inscricaoService;

    public InscricaoController(InscricaoService inscricaoService) {
        this.inscricaoService = inscricaoService;
    }

    @PostMapping
    public InscricaoResponse cadastrar(
            @RequestBody @Valid InscricaoRequest request
    ) {
        return inscricaoService.cadastrar(request);
    }

    @GetMapping
    public List<InscricaoResponse> listar() {
        return inscricaoService.listar();
    }

    @GetMapping("/{id}")
    public InscricaoResponse buscarPorId(
            @PathVariable("id") Long id
    ) {
        return inscricaoService.buscarPorId(id);
    }

    @PutMapping("/{id}/cancelar")
    public void cancelar(
            @PathVariable("id") Long id
    ) {
        inscricaoService.cancelarInscricao(id);
    }
}