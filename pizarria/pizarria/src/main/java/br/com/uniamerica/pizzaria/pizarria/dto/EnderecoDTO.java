package br.com.uniamerica.pizzaria.pizarria.dto;

import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class EnderecoDTO {
    private Long id;

    private String rua;

    private String bairro;

    private int numCasa;

    private String cep;

}
