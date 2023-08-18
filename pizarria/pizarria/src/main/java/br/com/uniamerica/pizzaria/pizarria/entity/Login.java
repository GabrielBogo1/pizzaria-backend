package br.com.uniamerica.pizzaria.pizarria.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table (name = "login", schema = "public")
public class Login {
    @Id
    @Getter
    @GeneratedValue(strategy =  GenerationType.AUTO)
    @Column(name = "id" , nullable = false, unique = true)
    private Long id;

    @Getter @Setter
    @Column (name = "login")
    private String login;

    @Getter @Setter
    @Column (name = "senha")
    private String senha;
}
