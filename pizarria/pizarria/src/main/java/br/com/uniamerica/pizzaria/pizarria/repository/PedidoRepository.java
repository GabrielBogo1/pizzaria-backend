package br.com.uniamerica.pizzaria.pizarria.repository;

import br.com.uniamerica.pizzaria.pizarria.entity.PedidoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;

public interface PedidoRepository extends JpaRepository <PedidoEntity, Long> {
    @Query("SELECT COUNT(p) FROM PedidoEntity p WHERE p.dataPedido = :data")
    Long PedidosPorData(@Param("data") LocalDate data);
}
