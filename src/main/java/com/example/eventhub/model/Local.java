package com.example.eventhub.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "local")
public class Local {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idLocal;

    private String nome;

    private String cidade;

    private String endereco;

    private Integer capacidadeMaxima;

    @OneToMany(mappedBy = "local", fetch = FetchType.LAZY)
    private List<Evento> eventos = new ArrayList<>();
}
