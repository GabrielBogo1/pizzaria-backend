package br.com.uniamerica.pizzaria.pizarria.dto;

import jakarta.persistence.Column;
import lombok.Data;
@Data
public class FuncionarioDTO {
    private Long id;
    private String nomeFuncionario;
    private String email;

    private String senha;

    public FuncionarioDTO (){}

    public FuncionarioDTO(Long id, String nomeFuncionario) {
        this.id = id;
        this.nomeFuncionario = nomeFuncionario;
    }
}
