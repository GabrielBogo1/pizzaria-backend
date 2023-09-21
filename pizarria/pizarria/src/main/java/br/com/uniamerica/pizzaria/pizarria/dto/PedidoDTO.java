package br.com.uniamerica.pizzaria.pizarria.dto;

import br.com.uniamerica.pizzaria.pizarria.entity.*;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalDateTime;
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

    private List<PizzaEntity> pizzas;

    private boolean pagamentoCartao;

    private boolean pagamentoDinheiro;

    private boolean cancelado;

    private boolean entrega;

    private List <ProdutosEntity> produtos;

    private LocalDate dataPedido;

    private LocalDateTime pedidoData;

    public PedidoDTO (){}

    /**
     * Construtor para criar uma instância de PedidoDTO com 14 parâmetros.
     *
     * @param id O ID do pedido.
     * @param funcionario O funcionário associado ao pedido.
     * @param usuario O usuário associado ao pedido.
     * @param observacao Uma flag indicando se o pedido deve ser sem cebola.
     * @param pedidoPreco O preço total do pedido.
     * @param status O status do pedido.
     * @param delivery Indica se o pedido é para entrega.
     * @param pizzas A lista de pizzas no pedido.
     * @param cancelado Indica se o pedido foi cancelado.
     * @param pagamentoCartao Indica se o pagamento foi feito com cartão.
     * @param pagamentoDinheiro Indica se o pagamento foi feito com dinheiro.
     * @param entrega Indica se o pedido foi entregue.
     * @param produtos A lista de produtos no pedido.
     * @param dataPedido A data manual do pedido.
     */

    @SuppressWarnings("java:S107")
    public PedidoDTO(Long id, FuncionarioEntity funcionario, UsuarioEntity usuario, String observacao, float pedidoPreco, Status status, boolean delivery, List<PizzaEntity> pizzas, boolean cancelado, boolean pagamentoCartao, boolean pagamentoDinheiro, boolean entrega, List<ProdutosEntity> produtos, LocalDate dataPedido) {
        this.id = id;
        this.funcionario = funcionario;
        this.usuario = usuario;
        this.observacao = observacao;
        this.pedidoPreco = pedidoPreco;
        this.status = status;
        this.delivery = delivery;
        this.pizzas = pizzas;
        this.cancelado = cancelado;
        this.pagamentoCartao = pagamentoCartao;
        this.pagamentoDinheiro = pagamentoDinheiro;
        this.entrega = entrega;
        this.produtos = produtos;
        this.dataPedido = dataPedido;
    }

    @SuppressWarnings("java:S107")
    public PedidoDTO(long id, FuncionarioEntity funcionario, UsuarioEntity usuario, String semCebola, int pedidoPreco, Status status, boolean delivery, List<PizzaEntity> pizzaList, boolean cancelado, boolean pagamentoCartao, boolean pagamentoDinheiro, boolean entrega, List<ProdutosEntity> produtoList, LocalDateTime dataManual) {
        this.id = id;
        this.funcionario = funcionario;
        this.usuario = usuario;
        this.pedidoPreco = pedidoPreco;
        this.status = status;
        this.delivery = delivery;
        this.pizzas = pizzaList;
        this.cancelado = cancelado;
        this.pagamentoCartao = pagamentoCartao;
        this.pagamentoDinheiro = pagamentoDinheiro;
        this.entrega = entrega;
        this.produtos = produtoList;
        this.pedidoData = dataManual;
    }
}
