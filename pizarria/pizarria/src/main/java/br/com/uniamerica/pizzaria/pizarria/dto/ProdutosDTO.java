package br.com.uniamerica.pizzaria.pizarria.dto;

import br.com.uniamerica.pizzaria.pizarria.entity.EstoqueProduto;
import jakarta.persistence.Column;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class ProdutosDTO {
    private Long id;

    private int quantProd;

    private EstoqueProduto estoqueProduto;
}
