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

    @Column (name = "nome_usuario")
    private String nomeUsuario;

    @Column (name = "telefone_usuario")
    private String telefone;

    @Column (name = "email_usuario")
    private String email;

    @Column (name = "senha_usuario")
    private String senha;


    @OneToMany(mappedBy = "usuario", fetch = FetchType.EAGER)
    private List<Endereco> enderecos;


    public UsuarioEntity (){}

    public UsuarioEntity(Long id, String nomeUsuario, String telefone, List<Endereco> enderecos) {
        this.id = id;
        this.nomeUsuario = nomeUsuario;
        this.telefone = telefone;
        this.enderecos = enderecos;
    }

    public UsuarioEntity(Long id, String nomeUsuario) {
        this.id = id;
        this.nomeUsuario = nomeUsuario;
    }


}


