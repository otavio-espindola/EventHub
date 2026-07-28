package com.example.eventhub.service;

import com.example.eventhub.dto.categoria.*;
import com.example.eventhub.model.Categoria;
import com.example.eventhub.repository.CategoriaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService {

    private final CategoriaRepository repository;

    public CategoriaService(CategoriaRepository Crepository){
        this.repository = Crepository;
    }

    public CategoriaResponse cadastrar (CategoriaRequest request){
        Categoria categoria = new Categoria();

        categoria.setNome(request.nome());
        categoria.setDescricao(request.descricao());

        Categoria salvo = repository.save(categoria);
        return toResponseDTO(salvo);
    }

    public List<CategoriaResponse> listar(){
        return repository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    public CategoriaResponse buscarPorId (Long id){
        Categoria categoria = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Evento não encontrado"));
        return toResponseDTO(categoria);
    }

    public void excluir(Long id){
        repository.deleteById(id);
    }

    public CategoriaResponse atualizar(Long id, CategoriaRequest request) {

        Categoria existente = buscarCategoria(id);

        existente.setNome(request.nome());
        existente.setDescricao(request.descricao());

        Categoria atualizado = repository.save(existente);

        return toResponseDTO(atualizado);
    }
    private CategoriaResponse toResponseDTO(Categoria categoria) {
        return new CategoriaResponse(
                categoria.getIdCategoria(),
                categoria.getNome(),
                categoria.getDescricao()
        );
    }
    private Categoria buscarCategoria(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Evento não encontrado"));
    }
}
