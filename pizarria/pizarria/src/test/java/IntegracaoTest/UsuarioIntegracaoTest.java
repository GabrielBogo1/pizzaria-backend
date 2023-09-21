package IntegracaoTest;

import br.com.uniamerica.pizzaria.pizarria.controller.UsuarioController;
import br.com.uniamerica.pizzaria.pizarria.dto.UsuarioDTO;
import br.com.uniamerica.pizzaria.pizarria.entity.UsuarioEntity;
import br.com.uniamerica.pizzaria.pizarria.repository.UsuarioRepository;
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
class UsuarioIntegracaoTest {


    @Autowired
    UsuarioRepository usuarioRepository;
    @MockBean
    UsuarioController usuarioController;

    private List<UsuarioEntity> usuarioList;

    @BeforeEach
    void injectData() {
        UsuarioEntity usuario = new UsuarioEntity(1L, "usuario");
        UsuarioEntity usuario2 = new UsuarioEntity(2L, "usuario2");
        usuarioList = new ArrayList<>();
        usuarioList.add(usuario);
        usuarioList.add(usuario2);

        Mockito.when(usuarioRepository.save(usuario)).thenReturn(usuario);
        Mockito.when(usuarioRepository.save(usuario2)).thenReturn(usuario2);
        Mockito.when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuario));
        Mockito.when(usuarioRepository.findById(2L)).thenReturn(Optional.of(usuario2));
        Mockito.when(usuarioRepository.findAll()).thenReturn(usuarioList);
    }

    @Test
    void testUsuarioPost() {
        // Simule o comportamento do saboresController
        Mockito.when(usuarioController.cadastrarUsuario(Mockito.any(UsuarioDTO.class))).thenReturn(ResponseEntity.ok("Registro cadastrado com sucesso"));

        var usuario = usuarioController.cadastrarUsuario(new UsuarioDTO(1L, "TesteNome"));

        Assertions.assertEquals("Registro cadastrado com sucesso", usuario.getBody());
    }

    @Test
    void testUsuarioPostErro() {
        Mockito.when(usuarioController.cadastrarUsuario(Mockito.any(UsuarioDTO.class))).thenReturn(ResponseEntity.badRequest().body("Error: Nome não pode ser nulo"));

        var usuario = usuarioController.cadastrarUsuario(new UsuarioDTO());
        Assertions.assertNotNull(usuario);
        Assertions.assertEquals("Error: Nome não pode ser nulo", usuario.getBody());
    }

    @Test
    void testPutUsuario(){
        Mockito.when(usuarioController.editarUsuario(Mockito.anyLong(), Mockito.any(UsuarioEntity.class))).thenReturn(ResponseEntity.ok("Registro cadastrado com sucesso"));

        UsuarioEntity usuario = new UsuarioEntity(1L, "TesteNome");
        usuario.setId(1L);

        var usuario1 = usuarioController.editarUsuario(1L, usuario);

        Assertions.assertNotNull(usuario1);
        Assertions.assertEquals("Registro cadastrado com sucesso", usuario1.getBody());
    }

    @Test
    void testPutUsuarioErro(){
        Mockito.when(usuarioController.editarUsuario(Mockito.anyLong(), Mockito.any(UsuarioEntity.class))).thenReturn(ResponseEntity.badRequest().body("Nao foi possivel indentificar o registro informado"));

        UsuarioEntity usuario = new UsuarioEntity(1L, "TesteNome");

        var usuario1 = usuarioController.editarUsuario(1L, usuario);

        Assertions.assertNotNull(usuario1);
        Assertions.assertEquals("Nao foi possivel indentificar o registro informado", usuario1.getBody());
    }

    @Test
    void testDeleteUsuario(){
        Mockito.when(usuarioController.delete(Mockito.anyLong())).thenReturn(ResponseEntity.ok("excluído"));

        var usuario = usuarioController.delete(2L);

        Assertions.assertNotNull(usuario);
        Assertions.assertEquals("excluído", usuario.getBody());
    }

    @Test
    void testDeleteUsuarioErro(){
        Mockito.when(usuarioController.delete(Mockito.anyLong())).thenReturn(ResponseEntity.badRequest().body("Nao foi possivel indentificar o registro informado"));

        var usuario = usuarioController.delete(10L);

        Assertions.assertNotNull(usuario);
        Assertions.assertEquals("Nao foi possivel indentificar o registro informado", usuario.getBody());
    }


    @Test
    void testGetIdUsuario(){
        Mockito.when(usuarioController.findByIdPath(Mockito.anyLong())).thenReturn(ResponseEntity.ok(new UsuarioEntity(1L,"TesteNome")));

        usuarioController.cadastrarUsuario(new UsuarioDTO(1L,"TesteNome"));
        var usuario = usuarioController.findByIdPath(1L);

        Assertions.assertNotNull(usuario);
        Assertions.assertEquals("TesteNome", usuario.getBody().getNomeUsuario());
    }

    @Test
    void testGetAllUsuario(){
        Mockito.when(usuarioController.listaCompleta()).thenReturn(ResponseEntity.ok(usuarioList));

        ResponseEntity<List<UsuarioEntity>> usuarioFuncaoController = usuarioController.listaCompleta();
        List<UsuarioEntity> usuarioListController = usuarioFuncaoController.getBody();

        Assertions.assertNotNull(usuarioListController);
        for(int i = 0; i < usuarioList.size();i ++){
            Assertions.assertEquals(usuarioList.get(i), usuarioListController.get(i));
        }
    }
}
