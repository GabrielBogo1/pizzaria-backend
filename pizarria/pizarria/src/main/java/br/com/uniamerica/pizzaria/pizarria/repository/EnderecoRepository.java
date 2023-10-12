package br.com.uniamerica.pizzaria.pizarria.repository;

import br.com.uniamerica.pizzaria.pizarria.entity.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

public interface EnderecoRepository extends JpaRepository <Endereco, Long> {
}
