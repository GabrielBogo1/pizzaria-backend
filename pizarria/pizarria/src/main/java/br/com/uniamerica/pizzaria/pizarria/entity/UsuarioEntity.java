package br.com.uniamerica.pizzaria.pizarria.entity;

import jakarta.persistence.*;
import jdk.jfr.Enabled;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

import java.util.List;

@Entity
@Table (name = "usuario", schema = "public")
public class UsuarioEntity {
    @Id
    @Getter
    @GeneratedValue(strategy =  GenerationType.AUTO)
    @Column(name = "id" , nullable = false, unique = true)
    private Long id;

    @Getter @Setter
    @Column (name = "nome_usuario")
    private String nomeUsuario;

    @Getter @Setter
    @OneToOne (fetch = FetchType.EAGER)
    @JoinTable(name = "usuario_login",
            uniqueConstraints =@UniqueConstraint(
                    columnNames = {
                            "usuario_id",
                            "login_id"
                    }
            ),
            joinColumns = @JoinColumn(
                    name = "usuario_id"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "login_id"
            )
    )
    private Login login;

    @Getter @Setter
    @Column (name = "cpf_usuario")
    @CPF (message = "CPF Inválido")
    private String cpf;

    @Getter @Setter
    @OneToOne(fetch = FetchType.EAGER)
    @JoinTable(name = "usuario_endereco",
            uniqueConstraints =@UniqueConstraint(
                    columnNames = {
                            "endereco_id",
                            "usuario_id"
                    }
            ),
            joinColumns = @JoinColumn(
                    name = "usuario_id"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "endereco_id"
            )
    )
    private Endereco endereco;
}


