package br.com.uniamerica.pizzaria.pizarria.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table (name = "pizza", schema = "public")
public class PizzaEntity {
    @Id
    @Getter
    @GeneratedValue(strategy =  GenerationType.AUTO)
    @Column(name = "id" , nullable = false, unique = true)
    private Long id;

    @Getter @Setter
    @ManyToMany
//    @JoinTable(name = "pizza_sabor",
//            joinColumns = @JoinColumn(name = "pizza_id"),
//            inverseJoinColumns = @JoinColumn(name = "sabor_id"))
    private List<SaboresEntity> sabores;
    @Getter @Setter
    @Column (name = "preco_pizza")
    private float precoPizza;

    @Getter @Setter
    @Column (name = "quant_pizza")
    private int quantPizza;

    @Enumerated(EnumType.STRING)
    @Column (name = "tamanho")
    private Tamanho tamanho;

}
