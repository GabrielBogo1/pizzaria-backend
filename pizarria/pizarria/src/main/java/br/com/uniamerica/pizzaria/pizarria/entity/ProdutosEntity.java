package br.com.uniamerica.pizzaria.pizarria.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table (name = "produtos", schema = "public")
public class ProdutosEntity {
    @Id
    @Getter
    @GeneratedValue(strategy =  GenerationType.AUTO)
    @Column(name = "id" , nullable = false, unique = true)
    private Long id;

    @Getter @Setter
    @Column (name = "quant_prod")
    private int quantProd;

    @ManyToMany
    private List <EstoqueProduto> estoqueProduto;
}
