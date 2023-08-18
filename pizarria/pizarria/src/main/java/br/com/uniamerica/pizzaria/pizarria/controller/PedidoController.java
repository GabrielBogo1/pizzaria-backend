package br.com.uniamerica.pizzaria.pizarria.controller;

import br.com.uniamerica.pizzaria.pizarria.dto.PedidoDTO;
import br.com.uniamerica.pizzaria.pizarria.entity.PedidoEntity;
import br.com.uniamerica.pizzaria.pizarria.repository.PedidoRepository;
import br.com.uniamerica.pizzaria.pizarria.service.PedidosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/pedido")
public class PedidoController {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private PedidosService pedidoService;

    @GetMapping("/{id}")
    public ResponseEntity<?> findByIdPath(@PathVariable("id") final Long id) {
        final PedidoEntity pedido = this.pedidoRepository.findById(id).orElse(null);
        return pedido == null
                ? ResponseEntity.badRequest().body("Nenhum pedido encontrado para o ID = " + id + ".")
                : ResponseEntity.ok(pedido);
    }

    @GetMapping("/lista")
    public ResponseEntity<?> listaCompleta() {
        return ResponseEntity.ok(this.pedidoRepository.findAll());
    }

//    @GetMapping("/lista/ativo")
//    public ResponseEntity<?> listaCompletaAtivo(){
//        return ResponseEntity.ok(this.pedidoRepository.findByAtivoTrue());
//    }

    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody final PedidoDTO pedido) {
        try {
//            this.pedidoService.validaPedido(pedido);
            return ResponseEntity.ok("Pedido realizado com sucesso.");
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.internalServerError().body("Error: " + e.getCause().getCause().getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editar(@PathVariable("id") final Long id, @RequestBody final PedidoDTO pedido) {
        try {
//            this.pedidoService.editarPedido(id, pedido);
            return ResponseEntity.ok("Pedido atualizado com sucesso. ");
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.internalServerError().body("Error: " + e.getCause().getCause().getMessage());
        } catch (RuntimeException e) {
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(
            @PathVariable("id") final Long id
    ) {
        try {
//            this.pedidoService.deletarPedido(id);
            return ResponseEntity.ok("Pedido excluido com sucesso.");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
        }
    }
}
