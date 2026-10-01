package com.example.eventhub.service;

import com.example.eventhub.dto.categoria.CategoriaRequest;
import com.example.eventhub.dto.categoria.CategoriaResponse;
import com.example.eventhub.dto.local.LocalRequest;
import com.example.eventhub.dto.local.LocalResponse;
import com.example.eventhub.model.Categoria;
import com.example.eventhub.model.Local;
import com.example.eventhub.repository.LocalRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocalService {

    private final LocalRepository repository;

    public LocalService(LocalRepository Lrepository){
        this.repository = Lrepository;
    }

    public LocalResponse cadastrar (LocalRequest request){
        Local local = new Local();

        local.setNome(request.nome());
        local.setEndereco(request.endereco());
        local.setCidade(request.cidade());
        local.setCapacidadeMaxima(request.capacidadeMaxima());

        Local salvo = repository.save(local);
        return toResponseDTO(salvo);
    }

    public List<LocalResponse> listar(){
        return repository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    public void excluir(Long id){
        repository.deleteById(id);
    }

    public LocalResponse atualizar(Long id, LocalRequest request) {

        Local existente = buscarLocal(id);

        existente.setNome(request.nome());
        existente.setCapacidadeMaxima(request.capacidadeMaxima());
        existente.setEndereco(request.endereco());
        existente.setCidade(request.cidade());

        Local atualizado = repository.save(existente);
        return toResponseDTO(atualizado);
    }

    private LocalResponse toResponseDTO(Local local) {
        return new LocalResponse(
                local.getIdLocal(),
                local.getNome(),
                local.getCidade(),
                local.getEndereco(),
                local.getCapacidadeMaxima()
        );
    }

    private Local buscarLocal(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Evento não encontrado"));
    }

    public LocalResponse buscarPorId (Long id){
        Local local = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Evento não encontrado"));
        return toResponseDTO(local);
    }
}
