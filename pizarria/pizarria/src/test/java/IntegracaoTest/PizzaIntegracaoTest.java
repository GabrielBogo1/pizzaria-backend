package IntegracaoTest;

import br.com.uniamerica.pizzaria.pizarria.controller.PizzaController;
import br.com.uniamerica.pizzaria.pizarria.dto.PizzaDTO;
import br.com.uniamerica.pizzaria.pizarria.entity.PizzaEntity;
import br.com.uniamerica.pizzaria.pizarria.entity.SaboresEntity;
import br.com.uniamerica.pizzaria.pizarria.entity.Tamanho;
import br.com.uniamerica.pizzaria.pizarria.repository.PizzaRepository;
import br.com.uniamerica.pizzaria.pizarria.repository.SaboresRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@ContextConfiguration(classes = TestConfig.class)
public class PizzaIntegracaoTest {

    @MockBean
    PizzaController pizzaController;

    @Autowired
    PizzaRepository pizzaRepository;

    @Autowired
    SaboresRepository saboresRepository;

    private List<PizzaEntity> pizzaList;

    private List <SaboresEntity> saborList;

    @BeforeEach
    void injectData() {


        SaboresEntity sabor = new SaboresEntity(1L,"Queijo");
        SaboresEntity sabor2 = new SaboresEntity(2L,"Frango");
        saborList = new ArrayList<>();
        saborList.add(sabor);
        saborList.add(sabor2);

        PizzaEntity pizza = new PizzaEntity(1L,saborList, 30, 1, Tamanho.P);
        PizzaEntity pizza2 = new PizzaEntity(2L,saborList,60,2, Tamanho.M);
        pizzaList = new ArrayList<>();
        pizzaList.add(pizza);
        pizzaList.add(pizza2);




        Mockito.when(saboresRepository.save(sabor)).thenReturn(sabor);
        Mockito.when(saboresRepository.save(sabor2)).thenReturn(sabor2);
        Mockito.when(saboresRepository.findById(1L)).thenReturn(Optional.of(sabor));
        Mockito.when(saboresRepository.findById(2L)).thenReturn(Optional.of(sabor2));
        Mockito.when(saboresRepository.findAll()).thenReturn(saborList);

        Mockito.when(pizzaRepository.save(pizza)).thenReturn(pizza2);
        Mockito.when(pizzaRepository.save(pizza2)).thenReturn(pizza2);
        Mockito.when(pizzaRepository.findById(1L)).thenReturn(Optional.of(pizza));
        Mockito.when(pizzaRepository.findById(2L)).thenReturn(Optional.of(pizza2));
        Mockito.when(pizzaRepository.findAll()).thenReturn(pizzaList);
    }

    @Test
    void testPizzaPost() {
        Mockito.when(pizzaController.cadastrarPizza(Mockito.any(PizzaDTO.class))).thenReturn(ResponseEntity.ok("Registro cadastrado com sucesso"));

        var pizza = pizzaController.cadastrarPizza(new PizzaDTO(1L, saborList, 10, 2, Tamanho.P));

        Assertions.assertEquals("Registro cadastrado com sucesso", pizza.getBody());
    }

    @Test
    void testPizzaPostErro() {
        Mockito.when(pizzaController.cadastrarPizza(Mockito.any(PizzaDTO.class))).thenReturn(ResponseEntity.badRequest().body("Error: Algo deu errado no cadastro da pizza"));

        var pizza = pizzaController.cadastrarPizza(new PizzaDTO());
        Assertions.assertNotNull(pizza);
        Assertions.assertEquals("Error: Algo deu errado no cadastro da pizza", pizza.getBody());
    }

    @Test
    void testPizzaPut(){
        Mockito.when(pizzaController.editarPizza(Mockito.anyLong(), Mockito.any(PizzaEntity.class))).thenReturn(ResponseEntity.ok("Registro Cadastrado com Sucesso"));

        PizzaEntity pizza = new PizzaEntity(1L, saborList, 10, 2, Tamanho.P);

        var pizza1 = pizzaController.editarPizza(1L, pizza);

        Assertions.assertNotNull(pizza1);
        Assertions.assertEquals("Registro Cadastrado com Sucesso", pizza1.getBody());
    }

    @Test
    void testPizzaPutError(){
        Mockito.when(pizzaController.editarPizza(Mockito.anyLong(), Mockito.any(PizzaEntity.class))).thenReturn(ResponseEntity.badRequest().body("Nao foi possivel indentificar o registro informado"));

        PizzaEntity pizza = new PizzaEntity(1L, saborList, 10, 2, Tamanho.P);

        var pizza1 = pizzaController.editarPizza(1L, pizza);

        Assertions.assertNotNull(pizza1);
        Assertions.assertEquals("Nao foi possivel indentificar o registro informado", pizza1.getBody());
    }

    @Test
    void testPizzaDelete(){
        Mockito.when(pizzaController.deletarPizza(Mockito.anyLong())).thenReturn(ResponseEntity.ok("excluído"));

        var response = pizzaController.deletarPizza(1L);
        Assertions.assertNotNull(response);
        Assertions.assertEquals("excluído", response.getBody());
    }

    @Test
    void testPizzaDeleteErro(){
        Mockito.when(pizzaController.deletarPizza(Mockito.anyLong())).thenReturn(ResponseEntity.badRequest().body("Error: Não foi possivel identificar o registro informado"));

        var pizza = pizzaController.deletarPizza(1L);
        Assertions.assertNotNull(pizza);
        Assertions.assertEquals("Error: Não foi possivel identificar o registro informado", pizza.getBody());
    }


    @Test
    void testPizzaGetId(){
        PizzaEntity pizza = new PizzaEntity(1L, saborList, 10, 2, Tamanho.P);
        Mockito.when(pizzaController.findByIdPath(Mockito.anyLong())).thenReturn(ResponseEntity.ok(pizza));

        var response = pizzaController.findByIdPath(1L);
        Assertions.assertNotNull(response);
        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals(pizza.getSabores(), response.getBody().getSabores());
    }

    @Test
    void testPizzaGetAll(){
        Mockito.when(pizzaController.listaCompleta()).thenReturn(ResponseEntity.ok(pizzaList));

        ResponseEntity<List<PizzaEntity>> pizzaFuncaoController = pizzaController.listaCompleta();
        List<PizzaEntity> pizzaListController = pizzaFuncaoController.getBody();

        Assertions.assertNotNull(pizzaListController);
        for(int i = 0; i < pizzaList.size();i ++){
            Assertions.assertEquals(pizzaList.get(i), pizzaListController.get(i));
        }
    }

}
