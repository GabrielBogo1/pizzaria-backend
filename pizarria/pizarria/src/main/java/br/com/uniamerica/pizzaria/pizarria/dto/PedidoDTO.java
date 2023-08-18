package br.com.uniamerica.pizzaria.pizarria.dto;

import br.com.uniamerica.pizzaria.pizarria.entity.*;
import lombok.Data;

@Data
public class PedidoDTO {
    private Long id;

    private FuncionarioEntity funcionario;

    private UsuarioEntity usuario;

    private String observacao;

    private float pedidoPreco;

    private Status status;


    private boolean delivery;

    private PizzaEntity pizza;

    private boolean pagamentoCartao;

    private ProdutosDTO produtos;
}
