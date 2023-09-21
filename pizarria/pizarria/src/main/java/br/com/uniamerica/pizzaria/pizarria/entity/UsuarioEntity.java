package br.com.uniamerica.pizzaria.pizarria.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Entity
@Table (name = "tb_usuario", schema = "public")
@Getter @Setter
public class UsuarioEntity {
    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    @Column(name = "id" , nullable = false, unique = true)
    private Long id;

    public UsuarioEntity (){}

    public UsuarioEntity(Long id, String nomeUsuario, Login loginUsuario, String telefone, List<Endereco> enderecos) {
        this.id = id;
        this.nomeUsuario = nomeUsuario;
        this.loginUsuario = loginUsuario;
        this.telefone = telefone;
        this.enderecos = enderecos;
    }

    @Column (name = "nome_usuario")
    private String nomeUsuario;

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
    private Login loginUsuario;

    @Column (name = "telefone_usuario")
    private String telefone;


    @OneToMany(mappedBy = "usuario", fetch = FetchType.EAGER)
    private List<Endereco> enderecos;


}


