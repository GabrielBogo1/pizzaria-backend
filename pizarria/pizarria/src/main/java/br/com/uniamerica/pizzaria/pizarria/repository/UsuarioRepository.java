package br.com.uniamerica.pizzaria.pizarria.repository;

import br.com.uniamerica.pizzaria.pizarria.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository <UsuarioEntity, Long> {
    UsuarioEntity findByTelefone(String telefone);

    Optional<UsuarioEntity> findOneByEmailAndSenha(String email, String senha);

    UsuarioEntity findByEmail (String email);
}
