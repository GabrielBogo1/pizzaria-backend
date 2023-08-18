package br.com.uniamerica.pizzaria.pizarria.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table (name = "pedidos", schema = "public")
public class PedidoEntity {

    @Id
    @Getter
    @GeneratedValue(strategy =  GenerationType.AUTO)
    @Column(name = "id" , nullable = false, unique = true)
    private Long id;

    @Getter @Setter
//    @ManyToOne (fetch = FetchType.EAGER)
//    @JoinColumn (name = "funcionario_id")
    @OneToOne
    private FuncionarioEntity funcionario;

    @Getter @Setter
    @ManyToOne
    @JoinColumn(name = "usuario",nullable = false)
    private UsuarioEntity usuario;

    @Getter @Setter
    @Column (name = "observacao")
    private String observacao;


    @Getter @Setter
    @Column (name = "pedido_preco")
    private float pedidoPreco;

    @Enumerated(EnumType.STRING)
    @Column (name = "status")
    private Status status;

    @Getter @Setter
    private boolean delivery;

    @Getter @Setter
    @ManyToOne
    private PizzaEntity pizza;


    @Getter @Setter
    @Column (name = "pagameto_cartao")
    private boolean pagamentoCartao;

    @Getter @Setter
    @ManyToOne
    @JoinColumn(name = "produtos_id")
    private ProdutosEntity produtos;

    @Getter @Setter
    @Column (name = "dataPedido")
    private LocalDateTime dataPedido;

}
