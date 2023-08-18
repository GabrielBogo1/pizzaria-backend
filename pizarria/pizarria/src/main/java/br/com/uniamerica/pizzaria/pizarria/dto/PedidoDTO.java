package br.com.uniamerica.pizzaria.pizarria.dto;

import br.com.uniamerica.pizzaria.pizarria.entity.*;
import lombok.Data;

import java.util.List;

@Data
public class PedidoDTO {
    private Long id;

    private FuncionarioEntity funcionario;

    private UsuarioEntity usuario;

    private String observacao;

    private float pedidoPreco;

    private Status status;

    private boolean delivery;

    private List<PizzaEntity> pizza;

    private boolean pagamentoCartao;

    private List <ProdutosEntity> produtos;
}
