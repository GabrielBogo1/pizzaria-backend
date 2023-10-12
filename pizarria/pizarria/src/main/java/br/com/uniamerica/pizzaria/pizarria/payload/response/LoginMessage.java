package br.com.uniamerica.pizzaria.pizarria.payload.response;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class LoginMessage {
    String mensagem;
    boolean admin;

    public LoginMessage( ) {
    }

    public LoginMessage (String mensagem){}

    public LoginMessage(String mensagem, boolean admin) {
        this.mensagem = mensagem;
        this.admin = admin;
    }
}
