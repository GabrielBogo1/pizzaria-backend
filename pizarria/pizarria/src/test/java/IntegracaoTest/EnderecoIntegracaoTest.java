package IntegracaoTest;

import br.com.uniamerica.pizzaria.pizarria.controller.EnderecoController;
import br.com.uniamerica.pizzaria.pizarria.dto.EnderecoDTO;
import br.com.uniamerica.pizzaria.pizarria.dto.SaboresDTO;
import br.com.uniamerica.pizzaria.pizarria.entity.Endereco;
import br.com.uniamerica.pizzaria.pizarria.entity.SaboresEntity;
import br.com.uniamerica.pizzaria.pizarria.entity.UsuarioEntity;
import br.com.uniamerica.pizzaria.pizarria.repository.EnderecoRepository;
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
public class EnderecoIntegracaoTest {

    @Autowired
    EnderecoRepository enderecoRepository;

    @MockBean
    EnderecoController enderecoController;

    private List <Endereco> enderecosList;

    private List <UsuarioEntity> usuarioList;

    @BeforeEach
    void injectData() {

        UsuarioEntity usuario = new UsuarioEntity(1L, "usuario");
        UsuarioEntity usuario2 = new UsuarioEntity(2L, "usuario2");
        usuarioList = new ArrayList<>();
        usuarioList.add(usuario);
        usuarioList.add(usuario2);


        Endereco endereco = new Endereco(1L, "ruaTeste", "bairroTeste",10, "85867264", "casa");
        Endereco endereco2 = new Endereco(2L,"ruaTeste2", "bairroTeste2",11, "85867254", "predio");
        enderecosList = new ArrayList<>();
        enderecosList.add(endereco);
        enderecosList.add(endereco2);

        Mockito.when(enderecoRepository.save(endereco)).thenReturn(endereco);
        Mockito.when(enderecoRepository.save(endereco2)).thenReturn(endereco2);
        Mockito.when(enderecoRepository.findById(1L)).thenReturn(Optional.of(endereco));
        Mockito.when(enderecoRepository.findById(2L)).thenReturn(Optional.of(endereco2));
        Mockito.when(enderecoRepository.findAll()).thenReturn(enderecosList);
    }


    @Test
    void testEnderecoPost() {
        Mockito.when(enderecoController.cadastraEndereco(Mockito.any(EnderecoDTO.class))).thenReturn(ResponseEntity.ok("Registro cadastrado com sucesso"));

        var endereco = enderecoController.cadastraEndereco(new EnderecoDTO(1L,"TesteRua", "TesteBairro",10, "85867264", "casa"));

        Assertions.assertEquals("Registro cadastrado com sucesso", endereco.getBody());
    }

    @Test
    void testEnderecoPostErro() {
        Mockito.when(enderecoController.cadastraEndereco(Mockito.any(EnderecoDTO.class))).thenReturn(ResponseEntity.badRequest().body("Error: Endereco não pode ser nulo"));

        var endereco = enderecoController.cadastraEndereco(new EnderecoDTO());
        Assertions.assertNotNull(endereco);
        Assertions.assertEquals("Error: Endereco não pode ser nulo", endereco.getBody());
    }

    @Test
    void testEnderecoPut(){
        Mockito.when(enderecoController.editarEndereco(Mockito.anyLong(), Mockito.any(Endereco.class))).thenReturn(ResponseEntity.ok("Registro Cadastrado com Sucesso"));

        Endereco endereco = new Endereco(1L,"TesteRua", "TesteBairro",10, "85867264", "casa");

        var endereco1 = enderecoController.editarEndereco(1L, endereco);

        Assertions.assertNotNull(endereco1);
        Assertions.assertEquals("Registro Cadastrado com Sucesso", endereco1.getBody());
    }

    @Test
    void testEnderecoPutError(){
        Mockito.when(enderecoController.editarEndereco(Mockito.anyLong(), Mockito.any(Endereco.class))).thenReturn(ResponseEntity.badRequest().body("Nao foi possivel indentificar o registro informado"));

        Endereco enderecos = new Endereco(1L,"TesteRua", "TesteBairro",10, "85867264", "casa");

        var endereco = enderecoController.editarEndereco(1L, enderecos);

        Assertions.assertNotNull(endereco);
        Assertions.assertEquals("Nao foi possivel indentificar o registro informado", endereco.getBody());
    }

    @Test
    void testEnderecoDelete(){
        Mockito.when(enderecoController.deletaEndereco(Mockito.anyLong())).thenReturn(ResponseEntity.ok("excluído"));

        var response = enderecoController.deletaEndereco(1L);
        Assertions.assertNotNull(response);
        Assertions.assertEquals("excluído", response.getBody());
    }

    @Test
    void testEnderecoDeleteErro(){
        Mockito.when(enderecoController.deletaEndereco(Mockito.anyLong())).thenReturn(ResponseEntity.badRequest().body("ERRor: Não foi possivel identificar o registro informado"));

        var endereco = enderecoController.deletaEndereco(1L);
        Assertions.assertNotNull(endereco);
        Assertions.assertEquals("ERRor: Não foi possivel identificar o registro informado", endereco.getBody());
    }

    @Test
    void testEnderecoGetId(){
        Endereco endereco = new Endereco(1L,"TesteRua", "TesteBairro",10, "85867264", "condominio");
        Mockito.when(enderecoController.findByIdPath(Mockito.anyLong())).thenReturn(ResponseEntity.ok(new Endereco(endereco)));
        var response = enderecoController.findByIdPath(1L);
        Assertions.assertNotNull(response);
        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals(endereco.getRua(), response.getBody().getRua());
    }

    @Test
    void testEnderecoGetAll(){
        Mockito.when(enderecoController.listaCompleta()).thenReturn(ResponseEntity.ok(enderecosList));

        ResponseEntity<List<Endereco>> enderecoFuncaoController = enderecoController.listaCompleta();
        List<Endereco> enderecoListController = enderecoFuncaoController.getBody();

        Assertions.assertNotNull(enderecoListController);
        for(int i = 0; i < enderecosList.size();i ++){
            Assertions.assertEquals(enderecosList.get(i), enderecoListController.get(i));
        }
    }





}
