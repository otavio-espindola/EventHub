package com.example.eventhub.repository;

import com.example.eventhub.model.Categoria;
import com.example.eventhub.model.Evento;
import com.example.eventhub.model.Local;
import com.example.eventhub.status.StatusEvento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface EventoRepository extends JpaRepository <Evento, Long>{

    List<Evento> findByNomeContainingIgnoreCase(String nome);
    List<Evento> findByCategoriaContainingIgnoreCase(Categoria categoria);
    List<Evento> findByLocalContainingIgnoreCase(Local local);
    List<Evento> findByStatus(StatusEvento statusEvento);
    List<Evento> findByData(LocalDate data);
    List<Evento> findByDataAfter(LocalDate data);

    boolean existsByLocalAndDataAndHorario(Local local, LocalDate data, LocalTime horario);
    boolean existsByLocalAndDataAndHorarioAndIdEventoNot(
            Local local,
            LocalDate data,
            LocalTime horario,
            Long idEvento
    );
}
