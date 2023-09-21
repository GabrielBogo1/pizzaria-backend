package br.com.uniamerica.pizzaria.pizarria.dto;

import br.com.uniamerica.pizzaria.pizarria.entity.Endereco;
import br.com.uniamerica.pizzaria.pizarria.entity.Login;
import lombok.Data;

import java.util.List;

@Data
public class UsuarioDTO {
    private Long id;

    private String nomeUsuario;

    private Login loginUsuario;

    private String telefone;

    private List<Endereco> enderecos;

    public UsuarioDTO (){}

    public UsuarioDTO(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public UsuarioDTO(Long id , String nomeUsuario) {
        this.id = id;
        this.nomeUsuario = nomeUsuario;
    }

}
