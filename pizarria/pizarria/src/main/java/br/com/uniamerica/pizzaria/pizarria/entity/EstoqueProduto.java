package br.com.uniamerica.pizzaria.pizarria.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table (name = "estoqueProdutos", schema = "public")
public class EstoqueProduto {
    @Id
    @Getter
    @GeneratedValue(strategy =  GenerationType.AUTO)
    @Column(name = "id" , nullable = false, unique = true)
    private Long id;

    @Getter @Setter
    @Column (name = "preco_produto")
    private float precoProd;

    @Getter @Setter
    @Column (name = "nome_produto")
    private String nomeProd;
}
