package IntegracaoTest;

import br.com.uniamerica.pizzaria.pizarria.controller.FuncionarioController;
import br.com.uniamerica.pizzaria.pizarria.dto.FuncionarioDTO;
import br.com.uniamerica.pizzaria.pizarria.entity.FuncionarioEntity;
import br.com.uniamerica.pizzaria.pizarria.repository.FuncionarioRepository;
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
class FuncionarioIntegracaoTest {

    @MockBean
    FuncionarioController funcionarioController;

    @Autowired
    FuncionarioRepository funcionarioRepository;

    private List<FuncionarioEntity> funcionarioList;


    @BeforeEach
    void injectData() {


        FuncionarioEntity funcionario = new FuncionarioEntity(1L, "funcionario");
        FuncionarioEntity funcionario2 = new FuncionarioEntity(2L, "funcionario2");
        funcionarioList = new ArrayList<>();
        funcionarioList.add(funcionario);
        funcionarioList.add(funcionario2);


        Mockito.when(funcionarioRepository.save(funcionario)).thenReturn(funcionario);
        Mockito.when(funcionarioRepository.save(funcionario2)).thenReturn(funcionario2);
        Mockito.when(funcionarioRepository.findById(1L)).thenReturn(Optional.of(funcionario));
        Mockito.when(funcionarioRepository.findById(2L)).thenReturn(Optional.of(funcionario2));
        Mockito.when(funcionarioRepository.findAll()).thenReturn(funcionarioList);
    }

    @Test
    void testFuncionarioPost() {
        Mockito.when(funcionarioController.cadastrarFuncionario(Mockito.any(FuncionarioDTO.class))).thenReturn(ResponseEntity.ok("Registro cadastrado com sucesso"));

        var funcionario = funcionarioController.cadastrarFuncionario(new FuncionarioDTO(1L, "TesteFuncionario"));

        Assertions.assertEquals("Registro cadastrado com sucesso", funcionario.getBody());
    }

    @Test
    void testFuncionarioPostErro() {
        Mockito.when(funcionarioController.cadastrarFuncionario(Mockito.any(FuncionarioDTO.class))).thenReturn(ResponseEntity.badRequest().body("Error: Nome do funcionario não pode ser nulo"));

        var funcionario = funcionarioController.cadastrarFuncionario(new FuncionarioDTO());
        Assertions.assertNotNull(funcionario);
        Assertions.assertEquals("Error: Nome do funcionario não pode ser nulo", funcionario.getBody());
    }

    @Test
    void testFuncionarioPut(){
        Mockito.when(funcionarioController.editarFuncionario(Mockito.anyLong(), Mockito.any(FuncionarioEntity.class))).thenReturn(ResponseEntity.ok("Registro Cadastrado com Sucesso"));

        FuncionarioEntity funcionario = new FuncionarioEntity(1L, "TesteFuncionario");

        var funcionario1 = funcionarioController.editarFuncionario(1L, funcionario);

        Assertions.assertNotNull(funcionario1);
        Assertions.assertEquals("Registro Cadastrado com Sucesso", funcionario1.getBody());
    }

    @Test
    void testFuncionarioPutError(){
        Mockito.when(funcionarioController.editarFuncionario(Mockito.anyLong(), Mockito.any(FuncionarioEntity.class))).thenReturn(ResponseEntity.badRequest().body("Nao foi possivel indentificar o registro informado"));

        FuncionarioEntity funcionario = new FuncionarioEntity(1L, "TesteFuncionario");

        var funcionario1 = funcionarioController.editarFuncionario(1L, funcionario);

        Assertions.assertNotNull(funcionario1);
        Assertions.assertEquals("Nao foi possivel indentificar o registro informado", funcionario1.getBody());
    }

    @Test
    void testFuncionarioDelete(){
        Mockito.when(funcionarioController.deletarFuncionario(Mockito.anyLong())).thenReturn(ResponseEntity.ok("excluído"));

        var response = funcionarioController.deletarFuncionario(1L);
        Assertions.assertNotNull(response);
        Assertions.assertEquals("excluído", response.getBody());
    }

    @Test
    void testFuncionarioDeleteErro(){
        Mockito.when(funcionarioController.deletarFuncionario(Mockito.anyLong())).thenReturn(ResponseEntity.badRequest().body("Error: Não foi possivel identificar o registro informado"));

        var funcionario = funcionarioController.deletarFuncionario(1L);
        Assertions.assertNotNull(funcionario);
        Assertions.assertEquals("Error: Não foi possivel identificar o registro informado", funcionario.getBody());
    }


    @Test
    void testFuncionarioGetId(){
        FuncionarioEntity funcionario = new FuncionarioEntity(1L, "testee");
        Mockito.when(funcionarioController.findByIdPath(Mockito.anyLong())).thenReturn(ResponseEntity.ok(funcionario));

        var response = funcionarioController.findByIdPath(1L);
        Assertions.assertNotNull(response);
        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals(funcionario.getNomeFuncionario(), response.getBody().getNomeFuncionario());
    }

    @Test
    void testFuncionarioGetAll(){
        Mockito.when(funcionarioController.listaCompleta()).thenReturn(ResponseEntity.ok(funcionarioList));

        ResponseEntity<List<FuncionarioEntity>> funcionarioFuncaoController = funcionarioController.listaCompleta();
        List<FuncionarioEntity> funcionarioListController = funcionarioFuncaoController.getBody();

        Assertions.assertNotNull(funcionarioListController);
        for(int i = 0; i < funcionarioList.size();i ++){
            Assertions.assertEquals(funcionarioList.get(i), funcionarioListController.get(i));
        }
    }


}
