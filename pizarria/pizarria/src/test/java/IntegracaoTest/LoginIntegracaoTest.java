package IntegracaoTest;

import br.com.uniamerica.pizzaria.pizarria.controller.LoginController;
import br.com.uniamerica.pizzaria.pizarria.entity.Login;
import br.com.uniamerica.pizzaria.pizarria.repository.LoginRepository;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
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

}
