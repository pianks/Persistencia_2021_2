package com.example.atividade06.model;

import lombok.*;
import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@ToString

@NamedQuery(name="Funcionario.findAll", query="select f from Funcionario as f")
@NamedQuery(name="Funcionario.findByIniciadosPorId", query="select f from Funcionario as f where f.id like :id")

@Entity
@Table(name = "funcionarios")
public class Funcionario {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NonNull @Getter @Setter private int id;
    @NonNull @Getter @Setter private String cpf;
    @Getter @Setter private String matricula;
    @Getter @Setter private String nome;
    @Getter @Setter private String email;
    @Getter @Setter private String telefone;
}
