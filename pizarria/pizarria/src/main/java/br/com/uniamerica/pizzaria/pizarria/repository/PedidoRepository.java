package br.com.uniamerica.pizzaria.pizarria.repository;

import br.com.uniamerica.pizzaria.pizarria.entity.PedidoEntity;
import br.com.uniamerica.pizzaria.pizarria.entity.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface PedidoRepository extends JpaRepository <PedidoEntity, Long> {
    @Query("SELECT COUNT(p) FROM PedidoEntity p WHERE p.dataPedido = :data")
    Long pedidosPorData(@Param("data") LocalDate data);

    @Query ("SELECT COUNT(p) FROM PedidoEntity p WHERE p.pagamentoCartao = true AND p.dataPedido = :data")
    Long totalPedidosCartao(@Param("data") LocalDate data);

    @Query ("SELECT COUNT(p) FROM PedidoEntity p WHERE p.pagamentoDinheiro = true AND p.dataPedido = :data")
    Long totalPedidosDinheiro(@Param("data") LocalDate data);

    @Query ("SELECT COUNT(p) FROM PedidoEntity p WHERE p.delivery = true AND p.dataPedido = :data")
    Long pedidosDelivery(@Param("data") LocalDate data);

    @Query ("SELECT COUNT(p) FROM PedidoEntity p WHERE p.delivery = false AND p.dataPedido = :data")
    Long totalPedidosBalcao(@Param("data") LocalDate data);

    @Query ("SELECT COUNT(p) FROM PedidoEntity p WHERE p.status = 'CANCELADO' AND p.dataPedido = :data")
    Long totalCancelados(@Param("data") LocalDate data);

    @Query ("SELECT COUNT(p) FROM PedidoEntity p WHERE p.status = 'ENTREGUE' AND p.dataPedido = :data")
    Long totalPagos(@Param("data") LocalDate data);

    List<PedidoEntity> findByDelivery(boolean delivery);

    List<PedidoEntity> findByStatus(Status status);

    List<PedidoEntity> findByCanceladoAndCadastroBetween(boolean cancelado, LocalDateTime startOfDay, LocalDateTime endOfDay);
    List<PedidoEntity> findByStatusAndCadastroBetween(Status status, LocalDateTime startOfDay, LocalDateTime endOfDay);
    List<PedidoEntity> findByCadastroBetween(LocalDateTime inicioDoDia, LocalDateTime fimDoDia);


}
