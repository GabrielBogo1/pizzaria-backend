package IntegracaoTest;

import br.com.uniamerica.pizzaria.pizarria.controller.EstoqueProdutoController;
import br.com.uniamerica.pizzaria.pizarria.dto.EstoqueProdutoDTO;
import br.com.uniamerica.pizzaria.pizarria.entity.EstoqueProdutos;
import br.com.uniamerica.pizzaria.pizarria.repository.EstoqueProdutoRepository;
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
class EstoqueProdIntegracaoTest {
    @MockBean
    EstoqueProdutoController estoqueProdutoController;

    @Autowired
    EstoqueProdutoRepository estoqueProdutoRepository;

    private List<EstoqueProdutos> estoqueProdutosList;

    @BeforeEach
    void injectData() {


        EstoqueProdutos estoque = new EstoqueProdutos(1L,10,"TesteEstq1");
        EstoqueProdutos estoque2 = new EstoqueProdutos(2L,7,"TesteEstq2");
        estoqueProdutosList = new ArrayList<>();
        estoqueProdutosList.add(estoque);
        estoqueProdutosList.add(estoque2);



        Mockito.when(estoqueProdutoRepository.save(estoque)).thenReturn(estoque);
        Mockito.when(estoqueProdutoRepository.save(estoque2)).thenReturn(estoque2);
        Mockito.when(estoqueProdutoRepository.findById(1L)).thenReturn(Optional.of(estoque));
        Mockito.when(estoqueProdutoRepository.findById(2L)).thenReturn(Optional.of(estoque2));
        Mockito.when(estoqueProdutoRepository.findAll()).thenReturn(estoqueProdutosList);
    }

    @Test
    void testEstoquePost() {
        Mockito.when(estoqueProdutoController.cadastrar(Mockito.any(EstoqueProdutoDTO.class))).thenReturn(ResponseEntity.ok("Registro cadastrado com sucesso"));

        var estoque = estoqueProdutoController.cadastrar(new EstoqueProdutoDTO(1L, "Testando"));
        Assertions.assertEquals("Registro cadastrado com sucesso", estoque.getBody());
    }

    @Test
    void testEstoquePostErro() {
        Mockito.when(estoqueProdutoController.cadastrar(Mockito.any(EstoqueProdutoDTO.class))).thenReturn(ResponseEntity.badRequest().body("Error: Nome não pode ser nulo"));

        var estoque = estoqueProdutoController.cadastrar(new EstoqueProdutoDTO());
        Assertions.assertEquals("Error: Nome não pode ser nulo", estoque.getBody());
    }

    @Test
    void testPutEstoque(){
        EstoqueProdutos estoqueProdutos = new EstoqueProdutos(2L,"TesteProduto");

        Mockito.when(estoqueProdutoController.editar(Mockito.anyLong(), Mockito.any(EstoqueProdutos.class))).thenReturn(ResponseEntity.ok("Registro Cadastrado com Sucesso"));

        var estoque = estoqueProdutoController.editar(1L, estoqueProdutos);

        Assertions.assertEquals("Registro Cadastrado com Sucesso", estoque.getBody());
    }

    @Test
    void testEstoquePutError(){
        Mockito.when(estoqueProdutoController.editar(Mockito.anyLong(), Mockito.any(EstoqueProdutos.class))).thenReturn(ResponseEntity.badRequest().body("Nao foi possivel indentificar o registro informado"));

        EstoqueProdutos estoques = new EstoqueProdutos(1L, "TesteEstoque");

        var estoque = estoqueProdutoController.editar(1L, estoques);

        Assertions.assertNotNull(estoque);
        Assertions.assertEquals("Nao foi possivel indentificar o registro informado", estoque.getBody());
    }

    @Test
    void testEstoqueDelete(){
        Mockito.when(estoqueProdutoController.delete(Mockito.anyLong())).thenReturn(ResponseEntity.ok("excluído"));

        var response = estoqueProdutoController.delete(1L);
        Assertions.assertNotNull(response);
        Assertions.assertEquals("excluído", response.getBody());
    }

    @Test
    void testEstoqueDeleteErro(){
        Mockito.when(estoqueProdutoController.delete(Mockito.anyLong())).thenReturn(ResponseEntity.badRequest().body("Error: Não foi possivel identificar o registro informado"));

        var estoque = estoqueProdutoController.delete(1L);
        Assertions.assertNotNull(estoque);
        Assertions.assertEquals("Error: Não foi possivel identificar o registro informado", estoque.getBody());
    }

    @Test
    void testEstoquesGetId(){
        EstoqueProdutos estoqueProdutos = new EstoqueProdutos(1L, "testee");
        Mockito.when(estoqueProdutoController.findByIdPath(Mockito.anyLong())).thenReturn(ResponseEntity.ok(new EstoqueProdutos(estoqueProdutos)));
        var response = estoqueProdutoController.findByIdPath(1L);
        Assertions.assertNotNull(response);
        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals(estoqueProdutos.getNomeProd(), response.getBody().getNomeProd());
    }

    @Test
    void testEstoqueGetAll() {
        Mockito.when(estoqueProdutoController.listaCompleta()).thenReturn(ResponseEntity.ok(estoqueProdutosList));

        ResponseEntity<List<EstoqueProdutos>> estoqueFuncaoController = estoqueProdutoController.listaCompleta();
        List<EstoqueProdutos> estoqueListController = estoqueFuncaoController.getBody();

        Assertions.assertNotNull(estoqueListController);
        for (int i = 0; i < estoqueProdutosList.size(); i++) {
            Assertions.assertEquals(estoqueProdutosList.get(i), estoqueListController.get(i));
        }


    }
}
