package IntegracaoTest;

import br.com.uniamerica.pizzaria.pizarria.controller.LoginController;
import br.com.uniamerica.pizzaria.pizarria.dto.LoginDTO;
import br.com.uniamerica.pizzaria.pizarria.entity.Login;
import br.com.uniamerica.pizzaria.pizarria.repository.LoginRepository;
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
public class LoginIntegracaoTest {

    @Autowired
    LoginRepository loginRepository;

    @MockBean
    LoginController loginController;

    private List <Login> loginList;


    @BeforeEach
    void injectData() {


        Login login = new Login(1L,"testeLogin","1234");
        Login login2 = new Login(2L,"testeLogin2","12345");
        loginList = new ArrayList<>();
        loginList.add(login);
        loginList.add(login2);


        Mockito.when(loginRepository.save(login)).thenReturn(login);
        Mockito.when(loginRepository.save(login2)).thenReturn(login2);
        Mockito.when(loginRepository.findById(1L)).thenReturn(Optional.of(login));
        Mockito.when(loginRepository.findById(2L)).thenReturn(Optional.of(login2));
        Mockito.when(loginRepository.findAll()).thenReturn(loginList);
    }

    @Test
    void testLoginPost() {
        Mockito.when(loginController.cadastrarLogin(Mockito.any(LoginDTO.class))).thenReturn(ResponseEntity.ok("Registro cadastrado com sucesso"));

        var login = loginController.cadastrarLogin(new LoginDTO(1L, "testeLogin", "senhaTeste"));

        Assertions.assertEquals("Registro cadastrado com sucesso", login.getBody());
    }

    @Test
    void testLoginPostErro() {
        Mockito.when(loginController.cadastrarLogin(Mockito.any(LoginDTO.class))).thenReturn(ResponseEntity.badRequest().body("Error: Nome do funcionario não pode ser nulo"));

        var login = loginController.cadastrarLogin(new LoginDTO());
        Assertions.assertNotNull(login);
        Assertions.assertEquals("Error: Nome do funcionario não pode ser nulo", login.getBody());
    }

    @Test
    void testLoginPut(){
        Mockito.when(loginController.editarLogin(Mockito.anyLong(), Mockito.any(Login.class))).thenReturn(ResponseEntity.ok("Registro Cadastrado com Sucesso"));

        Login login = new Login(1L, "testeLogin", "SenhaTeste");

        var login1 = loginController.editarLogin(1L, login);

        Assertions.assertNotNull(login1);
        Assertions.assertEquals("Registro Cadastrado com Sucesso", login1.getBody());
    }

    @Test
    void testLoginPutError(){
        Mockito.when(loginController.editarLogin(Mockito.anyLong(), Mockito.any(Login.class))).thenReturn(ResponseEntity.badRequest().body("Nao foi possivel indentificar o registro informado"));

        Login login = new Login(1L, "testeLogin", "SenhaTeste");

        var login1 = loginController.editarLogin(1L, login);

        Assertions.assertNotNull(login1);
        Assertions.assertEquals("Nao foi possivel indentificar o registro informado", login1.getBody());
    }

    @Test
    void testLoginDelete(){
        Mockito.when(loginController.deletarLogin(Mockito.anyLong())).thenReturn(ResponseEntity.ok("excluído"));

        var response = loginController.deletarLogin(1L);
        Assertions.assertNotNull(response);
        Assertions.assertEquals("excluído", response.getBody());
    }

    @Test
    void testLoginDeleteErro(){
        Mockito.when(loginController.deletarLogin(Mockito.anyLong())).thenReturn(ResponseEntity.badRequest().body("Error: Não foi possivel identificar o registro informado"));

        var login = loginController.deletarLogin(1L);
        Assertions.assertNotNull(login);
        Assertions.assertEquals("Error: Não foi possivel identificar o registro informado", login.getBody());
    }


    @Test
    void testLoginGetId(){
        Login login = new Login(1L, "testeLogin", "TesteSenha");
        Mockito.when(loginController.findByIdPath(Mockito.anyLong())).thenReturn(ResponseEntity.ok(login));

        var response = loginController.findByIdPath(1L);
        Assertions.assertNotNull(response);
        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals(login.getLoginUsuario(), response.getBody().getLoginUsuario());
    }

    @Test
    void testLoginGetAll(){
        Mockito.when(loginController.listaCompleta()).thenReturn(ResponseEntity.ok(loginList));

        ResponseEntity<List<Login>> loginFuncaoController = loginController.listaCompleta();
        List<Login> loginListController = loginFuncaoController.getBody();

        Assertions.assertNotNull(loginListController);
        for(int i = 0; i < loginList.size();i ++){
            Assertions.assertEquals(loginList.get(i), loginListController.get(i));
        }
    }

}
