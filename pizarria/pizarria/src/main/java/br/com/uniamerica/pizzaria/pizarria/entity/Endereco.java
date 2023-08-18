package br.com.uniamerica.pizzaria.pizarria.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table (name = "endereco", schema = "public")
public class Endereco {
    @Id
    @Getter
    @GeneratedValue(strategy =  GenerationType.AUTO)
    @Column(name = "id" , nullable = false, unique = true)
    private Long id;

    @Getter @Setter
    @Column (name = "rua")
    private String rua;

    @Getter @Setter
    @Column (name = "bairro")
    private String bairro;

    @Getter @Setter
    @Column (name = "n_casa")
    private int numCasa;

    @Getter @Setter
    @Column (name = "cep")
    private String cep;

    @Getter @Setter
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinTable( name = "endereco_usuario",
            uniqueConstraints = @UniqueConstraint(
                    columnNames = {
                            "endereco_id",
                            "usuario_id"
                    }
            ),
            joinColumns = @JoinColumn(
                    name = "endereco_id"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "usuario_id"
            )
    )
    private UsuarioEntity usuario;
}
