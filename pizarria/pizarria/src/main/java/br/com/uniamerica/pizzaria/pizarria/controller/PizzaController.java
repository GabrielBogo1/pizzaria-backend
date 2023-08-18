package br.com.uniamerica.pizzaria.pizarria.controller;

import br.com.uniamerica.pizzaria.pizarria.dto.PizzaDTO;
import br.com.uniamerica.pizzaria.pizarria.entity.PizzaEntity;
import br.com.uniamerica.pizzaria.pizarria.repository.PizzaRepository;
import br.com.uniamerica.pizzaria.pizarria.service.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/pizza")
public class PizzaController {

    @Autowired
    private PizzaRepository pizzaRepository;

    @Autowired
    private PizzaService pizzaService;

    @GetMapping("/{id}")
    public ResponseEntity<?> findByIdPath(@PathVariable("id") final Long id) {
        final PizzaEntity pizza = this.pizzaRepository.findById(id).orElse(null);
        return pizza == null
                ? ResponseEntity.badRequest().body("Nenhuma pizza encontrada para o ID = " + id + ".")
                : ResponseEntity.ok(pizza);
    }

    @GetMapping("/lista")
    public ResponseEntity<?> listaCompleta() {
        return ResponseEntity.ok(this.pizzaRepository.findAll());
    }

//    @GetMapping("/lista/ativo")
//    public ResponseEntity<?> listaCompletaAtivo(){
//        return ResponseEntity.ok(this.pizzaRepository.findByAtivoTrue());
//    }

    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody final PizzaDTO pizza) {
        try {
//            this.pizzaService.validaPizza(pizza);
            return ResponseEntity.ok("Pizza cadastrada com sucesso.");
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.internalServerError().body("Error: " + e.getCause().getCause().getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editar(@PathVariable("id") final Long id, @RequestBody final PizzaDTO pizza) {
        try {
//            this.pizzaService.editarPizza(id, pizza);
            return ResponseEntity.ok("Pizza atualizada com sucesso. ");
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
//            this.pizzaService.deletarPizza(id);
            return ResponseEntity.ok("Pizza excluida com sucesso.");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
        }
    }
}
