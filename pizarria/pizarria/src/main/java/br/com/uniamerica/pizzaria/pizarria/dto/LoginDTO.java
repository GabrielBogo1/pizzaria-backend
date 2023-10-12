package br.com.uniamerica.pizzaria.pizarria.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class LoginDTO {
    private String email;
    private String senha;

    public LoginDTO (){}

}
