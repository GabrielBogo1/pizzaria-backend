package br.com.uniamerica.pizzaria.pizarria.repository;

import br.com.uniamerica.pizzaria.pizarria.entity.EstoqueProduto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstoqueProdutoRepository extends JpaRepository <EstoqueProduto, Long> {
}
