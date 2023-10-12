package br.com.uniamerica.pizzaria.pizarria.repository;

import br.com.uniamerica.pizzaria.pizarria.entity.FuncionarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FuncionarioRepository extends JpaRepository <FuncionarioEntity, Long>{

    Optional <FuncionarioEntity> findOneByEmailAndSenha(String email, String senha);

    FuncionarioEntity findByEmail (String email);
}
