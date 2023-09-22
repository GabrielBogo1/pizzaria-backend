package br.com.uniamerica.pizzaria.pizarria.controller;

import br.com.uniamerica.pizzaria.pizarria.dto.PedidoDTO;
import br.com.uniamerica.pizzaria.pizarria.entity.PedidoEntity;
import br.com.uniamerica.pizzaria.pizarria.entity.Status;
import br.com.uniamerica.pizzaria.pizarria.repository.PedidoRepository;
import br.com.uniamerica.pizzaria.pizarria.service.PedidosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/pedido")
public class PedidoController {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private PedidosService pedidoService;

    public PedidoController(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }


    @GetMapping("/{id}")
    public ResponseEntity<PedidoEntity> findByIdPath(@PathVariable("id") final Long id) {
        final PedidoEntity pedido = this.pedidoRepository.findById(id).orElse(null);
        return ResponseEntity.ok(pedido);
    }

    @GetMapping("/lista")
    public ResponseEntity<List<PedidoEntity>> listaCompleta() {
        return ResponseEntity.ok(this.pedidoRepository.findAll());
    }

    @GetMapping("/solicitados")
    public ResponseEntity<List<PedidoEntity>> solicitados() {
        List<PedidoEntity> pedidosAndamento = this.pedidoRepository.findByStatus(Status.ANDAMENTO);
        return ResponseEntity.ok(pedidosAndamento);
    }


    @GetMapping("/pedidosDoDia")
    public List<PedidoEntity> getPedidosDoDia() {
        LocalDate dataAtual = LocalDate.now();

        LocalDateTime inicioDoDia = dataAtual.atStartOfDay();
        LocalDateTime fimDoDia = dataAtual.atTime(23, 59, 59);

        return pedidoRepository.findByCadastroBetween(inicioDoDia, fimDoDia);
    }

    @GetMapping("/pedidosEncerradosDoDia")
    public List<PedidoEntity> getPedidosEncerradosDoDia() {
        LocalDate dataAtual = LocalDate.now();

        LocalDateTime inicioDoDia = dataAtual.atStartOfDay();
        LocalDateTime fimDoDia = dataAtual.atTime(23, 59, 59);

        return pedidoRepository.findByStatusAndCadastroBetween(
                Status.ENTREGUE, inicioDoDia, fimDoDia
        );
    }

    @GetMapping("/pedidosCanceladosDoDia")
    public List<PedidoEntity> getPedidosCanceladosDoDia() {
        LocalDate dataAtual = LocalDate.now();

        return pedidoRepository.findByCanceladoAndCadastroBetween(true, dataAtual.atStartOfDay(), dataAtual.atTime(23, 59, 59));
    }


    @GetMapping("/delivery/{ativo}")
    public ResponseEntity<Map<String, Long>> delivery(@PathVariable("ativo") boolean delivery) {
        List<PedidoEntity> pedidos;
        if (!delivery) {
            pedidos = pedidoRepository.findByDelivery(false);
        } else {
            pedidos = pedidoRepository.findByDelivery(true);
        }

        long entregasPorDelivery = pedidos.stream()
                .filter(pedido -> pedido.isDelivery())
                .count();

        long entregasPorBalcao = pedidos.size() - entregasPorDelivery;

        Map<String, Long> resultado = new HashMap<>();
        resultado.put("entregasPorDelivery", entregasPorDelivery);
        resultado.put("entregasPorBalcao", entregasPorBalcao);

        return ResponseEntity.ok(resultado);

    }


    @GetMapping ("/comandaentregue/{id}")
    public ResponseEntity <String> comandaEntrega (@PathVariable ("id") Long id){
        try {
            PedidoEntity pedido = pedidoService.findPedidoById(id);
            pedidoService.gerarComandaFinalizado(pedido);
            return ResponseEntity.ok("comanda gerada com sucesso");
        }catch (Exception e){
            String errorMessage = getErrorMessage(e);
            return ResponseEntity.internalServerError().body(errorMessage);
        }
    }

    @PostMapping
    public ResponseEntity<String> cadastrarPedido (@RequestBody final PedidoDTO pedido) {
        try {
            this.pedidoService.validaPedido(pedido);
            return ResponseEntity.ok("Pedido realizado com sucesso.");
        } catch (DataIntegrityViolationException e) {
            String errorMessage = getErrorMessage(e);
            return ResponseEntity.internalServerError().body(errorMessage);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> editarPedido (@PathVariable("id") final Long id, @RequestBody final PedidoEntity pedido) {
        try {
            this.pedidoService.editaPedido(pedido);
            return ResponseEntity.ok("Pedido atualizado com sucesso. ");
        } catch (DataIntegrityViolationException e) {
            String errorMessage = getErrorMessage(e);
            return ResponseEntity.internalServerError().body(errorMessage);
        }
    }

    @PutMapping("/finalizapedido/{id}")
    public ResponseEntity<String> finalizaPedido(@PathVariable ("id") final Long id, @RequestBody final PedidoEntity pedido){
        try {
            final PedidoEntity pedido1 = this.pedidoRepository.findById(id).orElse(null);

            if (pedido1 == null || !pedido1.getId().equals(pedido.getId())){
                return ResponseEntity.internalServerError().body("Nao foi posivel identificar o pedido informado");
            }
            this.pedidoService.finalizaPedido(pedido);
            return ResponseEntity.ok("Pedido finalizado");
        }
        catch (DataIntegrityViolationException e){
            String errorMessage = getErrorMessage(e);
            return ResponseEntity.internalServerError().body(errorMessage);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletarPedido (@PathVariable("id") final Long id) {
        try {
            this.pedidoService.deletarPedido(id);
            return ResponseEntity.ok("Pedido excluido com sucesso.");
        } catch (Exception e) {
            String errorMessage = getErrorMessage(e);
            return ResponseEntity.internalServerError().body(errorMessage);
        }
    }

    private String getErrorMessage(Exception e) {
        return "Error: " + e.getMessage();
    }
}
