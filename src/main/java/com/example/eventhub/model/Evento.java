package com.example.eventhub.model;

import com.example.eventhub.status.StatusEvento;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "evento")
public class Evento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEvento;

    private String nome;

    private String descricao;

    private LocalDate data;

    private LocalTime horario;

    private Integer limiteParticipantes;

    @Enumerated(EnumType.STRING)
    private StatusEvento status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categoria_id", nullable = false)
    private Categoria categoria;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "local_id", nullable = false)
    private Local local;

    @OneToMany(mappedBy = "evento")
    private List<Inscricao> inscricoes;
}
