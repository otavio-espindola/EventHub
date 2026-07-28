package com.example.eventhub.model;

import com.example.eventhub.status.StatusInscricao;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "inscricao")
public class Inscricao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idInscricao;

    private LocalDate dataInscricao;

    @Enumerated(EnumType.STRING)
    private StatusInscricao status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "participante_id", nullable = false)
    private Participante participante;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "evento_id")
    private Evento evento;
}
