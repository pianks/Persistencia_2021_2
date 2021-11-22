package com.example.atividade05.model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@ToString

public class Funcionario {
    @NonNull @Getter @Setter private int id;
    @NonNull @Getter @Setter private String cpf;
    @Getter @Setter private String matricula;
    @Getter @Setter private String nome;
    @Getter @Setter private String email;
    @Getter @Setter private String telefone;
}
