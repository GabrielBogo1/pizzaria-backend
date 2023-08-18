package br.com.uniamerica.pizzaria.pizarria.dto;

import br.com.uniamerica.pizzaria.pizarria.entity.Endereco;
import br.com.uniamerica.pizzaria.pizarria.entity.Login;
import br.com.uniamerica.pizzaria.pizarria.entity.UsuarioEntity;
import jakarta.persistence.Column;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
public class UsuarioDTO {
    private Long id;

    private String nomeUsuario;

    private Login login;

    private String cpf;

    private Endereco endereco;
}
