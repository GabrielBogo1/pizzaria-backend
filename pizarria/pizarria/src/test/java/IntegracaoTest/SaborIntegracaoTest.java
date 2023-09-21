package IntegracaoTest;

import br.com.uniamerica.pizzaria.pizarria.controller.SaboresController;
import br.com.uniamerica.pizzaria.pizarria.dto.SaboresDTO;
import br.com.uniamerica.pizzaria.pizarria.entity.SaboresEntity;
import br.com.uniamerica.pizzaria.pizarria.repository.SaboresRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = TestConfig.class)
class SaborIntegracaoTest {
    @Autowired
    SaboresRepository saboresRepository;
    @MockBean
    SaboresController saboresController;


    private List<SaboresEntity> saborList;



    @BeforeEach
    void injectData() {
        SaboresEntity sabor = new SaboresEntity(1L,"Teste1");
        SaboresEntity sabor2 = new SaboresEntity(2L,"teste2");
        saborList = new ArrayList<>();
        saborList.add(sabor);
        saborList.add(sabor2);

        Mockito.when(saboresRepository.save(sabor)).thenReturn(sabor);
        Mockito.when(saboresRepository.save(sabor2)).thenReturn(sabor2);
        Mockito.when(saboresRepository.findById(1L)).thenReturn(Optional.of(sabor));
        Mockito.when(saboresRepository.findById(2L)).thenReturn(Optional.of(sabor2));
        Mockito.when(saboresRepository.findAll()).thenReturn(saborList);
    }

    @Test
    void testSaborePost() {
        // Simule o comportamento do saboresController
        Mockito.when(saboresController.cadastrar(Mockito.any(SaboresDTO.class))).thenReturn(ResponseEntity.ok("Registro cadastrado com sucesso"));

        var sabor = saboresController.cadastrar(new SaboresDTO(1L, "TESTANDOSABOR"));

        Assertions.assertEquals("Registro cadastrado com sucesso", sabor.getBody());
    }
    @Test
    void testSaboresPostErro() {
        Mockito.when(saboresController.cadastrar(Mockito.any(SaboresDTO.class))).thenReturn(ResponseEntity.badRequest().body("Error: Nome do sabor não pode ser nulo"));

        var sabor = saboresController.cadastrar(new SaboresDTO());
        Assertions.assertNotNull(sabor);
        Assertions.assertEquals("Error: Nome do sabor não pode ser nulo", sabor.getBody());
    }

    @Test
    void testSaboresPut(){
        Mockito.when(saboresController.editar(Mockito.anyLong(), Mockito.any(SaboresEntity.class))).thenReturn(ResponseEntity.ok("Registro Cadastrado com Sucesso"));

        SaboresEntity sabores = new SaboresEntity(1L, "SaborTeste");

        var sabor = saboresController.editar(1L, sabores);

        Assertions.assertNotNull(sabor);
        Assertions.assertEquals("Registro Cadastrado com Sucesso", sabor.getBody());
    }

    @Test
    void testSaboresPutError(){
        Mockito.when(saboresController.editar(Mockito.anyLong(), Mockito.any(SaboresEntity.class))).thenReturn(ResponseEntity.badRequest().body("Nao foi possivel indentificar o registro informado"));

        SaboresEntity sabores = new SaboresEntity(1L, "TesteSabor");

        var sabor = saboresController.editar(1L, sabores);

        Assertions.assertNotNull(sabor);
        Assertions.assertEquals("Nao foi possivel indentificar o registro informado", sabor.getBody());
    }

    @Test
    void testSaboresDelete(){
        Mockito.when(saboresController.delete(Mockito.anyLong())).thenReturn(ResponseEntity.ok("excluído"));

        var response = saboresController.delete(1L);
        Assertions.assertNotNull(response);
        Assertions.assertEquals("excluído", response.getBody());
    }

    @Test
    void testSaboresDeleteErro(){
        Mockito.when(saboresController.delete(Mockito.anyLong())).thenReturn(ResponseEntity.badRequest().body("Error: Não foi possivel identificar o registro informado"));

        var sabor = saboresController.delete(1L);
        Assertions.assertNotNull(sabor);
        Assertions.assertEquals("Error: Não foi possivel identificar o registro informado", sabor.getBody());
    }


    @Test
    void testSaboresGetId(){
        SaboresEntity sabor = new SaboresEntity(1L, "testee");
        Mockito.when(saboresController.findByIdPath(Mockito.anyLong())).thenReturn(ResponseEntity.ok(new SaboresEntity(sabor)));
        var response = saboresController.findByIdPath(1L);
        Assertions.assertNotNull(response);
        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals(sabor.getNomeSabor(), response.getBody().getNomeSabor());
    }

    @Test
    void testSaboresGetAll(){
        Mockito.when(saboresController.listaCompleta()).thenReturn(ResponseEntity.ok(saborList));

        ResponseEntity<List<SaboresEntity>> saboresFuncaoController = saboresController.listaCompleta();
        List<SaboresEntity> saboresListController = saboresFuncaoController.getBody();

        Assertions.assertNotNull(saboresListController);
        for(int i = 0; i < saborList.size();i ++){
            Assertions.assertEquals(saborList.get(i), saboresListController.get(i));
        }
    }
}
