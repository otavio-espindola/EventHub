package com.example.eventhub.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "participante")
public class Participante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idParticipante;

    private String nome;

    @Column(unique = true)
    private String cpf;

    @Column(unique = true)
    private String email;

    private String telefone;

    @OneToMany(mappedBy = "participante")
    private List<Inscricao> inscricoes;
}
