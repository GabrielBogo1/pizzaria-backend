package br.com.uniamerica.pizzaria.pizarria.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table (name = "funcionarios", schema = "public")
@Getter @Setter
public class FuncionarioEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @Column(name = "nome_funcionario")
    private String nomeFuncionario;

    @Column (name = "email")
    private String email;

    @Column (name = "senha")
    private String senha;
    public FuncionarioEntity (){}

    public FuncionarioEntity (FuncionarioEntity funcionario){
        this.id = funcionario.getId();
        this.nomeFuncionario = getNomeFuncionario();
    }

    public FuncionarioEntity(Long id, String nomeFuncionario) {
        this.id = id;
        this.nomeFuncionario = nomeFuncionario;
    }
}
