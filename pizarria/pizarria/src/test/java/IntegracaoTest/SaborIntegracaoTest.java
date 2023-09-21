package IntegracaoTest;

import br.com.uniamerica.pizzaria.pizarria.controller.SaboresController;
import br.com.uniamerica.pizzaria.pizarria.dto.SaboresDTO;
import br.com.uniamerica.pizzaria.pizarria.entity.SaboresEntity;
import br.com.uniamerica.pizzaria.pizarria.repository.SaboresRepository;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SaborIntegracaoTest {
    @MockBean
    SaboresRepository saborRepository;
    @Autowired
    SaboresController saborController;


    private List<SaboresEntity> saborList;



    @BeforeEach
    void injectData() {


        SaborEntity sabor = new SaborEntity(1L,"Queijo");
        SaboresEntity sabor2 = new SaboresEntity(2L,"Frango");
        saborList = new ArrayList<>();
        saborList.add(sabor);
        saborList.add(sabor2);


        Mockito.when(saborRepository.save(sabor)).thenReturn(sabor);
        Mockito.when(saborRepository.save(sabor2)).thenReturn(sabor2);
        Mockito.when(saborRepository.findById(1L)).thenReturn(Optional.of(sabor));
        Mockito.when(saborRepository.findById(2L)).thenReturn(Optional.of(sabor2));
        Mockito.when(saborRepository.findAll()).thenReturn(saborList);



    }

    @Test
    void testSaboresEntityCriar() {
        var sabor = saborController.cadastra(new SaboresEntityDTO("Bacon"));
        Assert.assertEquals("Registro cadastrado com sucesso", sabor.getBody());
    }
    @Test
    void testSaboresEntityCriarErrado() {
        var sabor = saborController.cadastra(new SaboresEntityDTO());
        Assert.assertEquals("Error: Nome do sabor não pode ser nulo", sabor.getBody());
    }



    @Test
    void testPutSaboresEntity(){
        SaboresEntity sabores = new SaboresEntity("Bacon");
        sabores.setId(1L);


        var sabor = saborController.editar(1L, sabores);

        Assert.assertEquals("Registro Cadastrado com Sucesso", sabor.getBody());
    }

    @Test
    void testPutSaboresEntityErrado(){
        SaboresEntity sabores = new SaboresEntity("Bacon");
        sabores.setId(1L);


        var sabor = saborController.editar(10L, sabores);

        Assert.assertEquals("Nao foi possivel indentificar o registro informado", sabor.getBody());
    }

    @Test
    void testSaboresEntityDelete(){
        var sabor = saborController.delete(2L);
        Assert.assertEquals("excluído", sabor.getBody());
    }

    @Test
    void testSaboresEntityDeleteErrado(){
        var sabor = saborController.delete(20L);
        Assert.assertEquals("ERRor: Não foi possivel identificar o registro informado", sabor.getBody());
    }


    @Test
    void testFindByIdSaboresEntity(){
        saborController.cadastrar(new SaboresDTO("4 queijos"));
        var sabor = saborController.findById(1L);
        Assert.assertEquals(sabor.getBody().getSaboresEntityr(), saborController.findById(1L).getBody().getSaboresEntityr());
    }

    @Test
    void testFindAllSaboresEntity(){
        ResponseEntity<List<SaboresEntity>> saborFuncaoController = saborController.listaCompleta();
        List<SaboresEntity> saborListController = saborFuncaoController.getBody();

        Assert.assertNotNull(saborListController);
        for(int i = 0; i < saborList.size();i ++){
            Assert.assertEquals(saborList.get(i), saborListController.get(i));
        }
    }
}
