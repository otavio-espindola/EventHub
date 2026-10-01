package com.example.eventhub.controller;

import com.example.eventhub.dto.categoria.CategoriaRequest;
import com.example.eventhub.dto.categoria.CategoriaResponse;
import com.example.eventhub.service.CategoriaService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {

    private final CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService){
        this.categoriaService = categoriaService;
    }

    @PostMapping
    public CategoriaResponse cadastrar(@RequestBody @Valid CategoriaRequest request){
        return categoriaService.cadastrar(request);
    }
    @GetMapping
    public List<CategoriaResponse> listar(){
        return categoriaService.listar();
    }
    @GetMapping("/{id}")
    public CategoriaResponse buscarPorId(@PathVariable("id") Long id){
        return categoriaService.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public CategoriaResponse atualizar(@PathVariable("id") Long id, @RequestBody @Valid CategoriaRequest request){
        return categoriaService.atualizar(id, request);
    }

    @DeleteMapping("/{id}")
    public void excluir(@PathVariable("id") Long id){
            categoriaService.excluir(id);
    }
}
