package br.com.uniamerica.pizzaria.pizarria.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table (name = "funcionarios", schema = "public")
public class FuncionarioEntity {
    @Id
    @Getter
    @GeneratedValue(strategy =  GenerationType.AUTO)
    @Column(name = "id" , nullable = false, unique = true)
    private Long id;
    @Getter
    @Setter
    @Column (name = "nome_funcionario")
    private String nomeFuncionario;
}
