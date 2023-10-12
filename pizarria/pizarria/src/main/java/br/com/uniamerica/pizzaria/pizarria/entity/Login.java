package br.com.uniamerica.pizzaria.pizarria.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Getter @Setter
public class Login {

    @Column (name = "login")
    private String email;

    @Column (name = "senha")
    private String senha;
    public Login (){}




}


