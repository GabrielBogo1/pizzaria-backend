package br.com.uniamerica.pizzaria.pizarria.dto;

import br.com.uniamerica.pizzaria.pizarria.entity.Endereco;
import jakarta.persistence.Column;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class UsuarioDTO {
    private Long id;

    private String nomeUsuario;

    private String cpf;

    private Endereco endereco;
}
