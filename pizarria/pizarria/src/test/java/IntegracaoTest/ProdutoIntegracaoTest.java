package IntegracaoTest;

import br.com.uniamerica.pizzaria.pizarria.controller.ProdutosController;
import br.com.uniamerica.pizzaria.pizarria.dto.ProdutosDTO;
import br.com.uniamerica.pizzaria.pizarria.entity.EstoqueProdutos;
import br.com.uniamerica.pizzaria.pizarria.entity.ProdutosEntity;
import br.com.uniamerica.pizzaria.pizarria.repository.EstoqueProdutoRepository;
import br.com.uniamerica.pizzaria.pizarria.repository.ProdutosRepository;
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
public class ProdutoIntegracaoTest {

    @Autowired
    EstoqueProdutoRepository estoqueRepository;

    @Autowired
    ProdutosRepository produtoRepository;
    @MockBean
    ProdutosController produtoController;


    private List<EstoqueProdutos> estoqueList;


    private List<ProdutosEntity> produtoList;


    @BeforeEach
    void injectData() {


        EstoqueProdutos estoque = new EstoqueProdutos(1L,10,"Coca-cola");
        EstoqueProdutos estoque2 = new EstoqueProdutos(2L,7,"Guaraná");
        estoqueList = new ArrayList<>();
        estoqueList.add(estoque);
        estoqueList.add(estoque2);

        ProdutosEntity produto = new ProdutosEntity(1L,1,estoque,20);
        ProdutosEntity produto2 = new ProdutosEntity(2L,1,estoque,30);
        produtoList = new ArrayList<>();
        produtoList.add(produto);
        produtoList.add(produto2);


        Mockito.when(estoqueRepository.save(estoque)).thenReturn(estoque);
        Mockito.when(estoqueRepository.save(estoque2)).thenReturn(estoque2);
        Mockito.when(estoqueRepository.findById(1L)).thenReturn(Optional.of(estoque));
        Mockito.when(estoqueRepository.findById(2L)).thenReturn(Optional.of(estoque2));
        Mockito.when(estoqueRepository.findAll()).thenReturn(estoqueList);


        Mockito.when(produtoRepository.save(produto)).thenReturn(produto);
        Mockito.when(produtoRepository.save(produto2)).thenReturn(produto2);
        Mockito.when(produtoRepository.findById(1L)).thenReturn(Optional.of(produto));
        Mockito.when(produtoRepository.findById(2L)).thenReturn(Optional.of(produto2));
        Mockito.when(produtoRepository.findAll()).thenReturn(produtoList);
    }

    @Test
    void testProdutosPost() {
        // Simule o comportamento do saboresController
        Mockito.when(produtoController.cadastrar(Mockito.any(ProdutosDTO.class))).thenReturn(ResponseEntity.ok("Registro cadastrado com sucesso"));
        EstoqueProdutos estoqueTest = new EstoqueProdutos(2L,30,"Trio Pequeno");
        var produtos = produtoController.cadastrar(new ProdutosDTO(1L, 1, estoqueTest, 10));

        Assertions.assertEquals("Registro cadastrado com sucesso", produtos.getBody());
    }
    @Test
    void testProdutosPostErro() {
        Mockito.when(produtoController.cadastrar(Mockito.any(ProdutosDTO.class))).thenReturn(ResponseEntity.badRequest().body("Error: Nome do sabor não pode ser nulo"));
        EstoqueProdutos estoqueTest = new EstoqueProdutos(2L,30,"Trio Pequeno");
        var produtos = produtoController.cadastrar(new ProdutosDTO());
        Assertions.assertNotNull(produtos);
        Assertions.assertEquals("Error: Nome do sabor não pode ser nulo", produtos.getBody());
    }

    @Test
    void testProdutosPut(){
        Mockito.when(produtoController.editar(Mockito.anyLong(), Mockito.any(ProdutosEntity.class))).thenReturn(ResponseEntity.ok("Registro Cadastrado com Sucesso"));
        EstoqueProdutos estoqueTest = new EstoqueProdutos(2L,30,"Trio Pequeno");

        ProdutosEntity produtos = new ProdutosEntity(1L, 1, estoqueTest, 10);

        var produtos1 = produtoController.editar(1L, produtos);

        Assertions.assertNotNull(produtos1);
        Assertions.assertEquals("Registro Cadastrado com Sucesso", produtos1.getBody());
    }

    @Test
    void testProdutosPutError(){
        Mockito.when(produtoController.editar(Mockito.anyLong(), Mockito.any(ProdutosEntity.class))).thenReturn(ResponseEntity.badRequest().body("Nao foi possivel indentificar o registro informado"));
        EstoqueProdutos estoqueTest = new EstoqueProdutos(2L,30,"Trio Pequeno");
        ProdutosEntity produtos = new ProdutosEntity(1L, 1, estoqueTest, 10);

        var produtos1 = produtoController.editar(1L, produtos);

        Assertions.assertNotNull(produtos1);
        Assertions.assertEquals("Nao foi possivel indentificar o registro informado", produtos1.getBody());
    }

    @Test
    void testProdutosDelete(){
        Mockito.when(produtoController.delete(Mockito.anyLong())).thenReturn(ResponseEntity.ok("excluído"));

        var response = produtoController.delete(1L);
        Assertions.assertNotNull(response);
        Assertions.assertEquals("excluído", response.getBody());
    }

    @Test
    void testProdutosDeleteErro(){
        Mockito.when(produtoController.delete(Mockito.anyLong())).thenReturn(ResponseEntity.badRequest().body("Error: Não foi possivel identificar o registro informado"));

        var produtos = produtoController.delete(1L);
        Assertions.assertNotNull(produtos);
        Assertions.assertEquals("Error: Não foi possivel identificar o registro informado", produtos.getBody());
    }


    @Test
    void testProdutosGetId(){
        EstoqueProdutos estoqueTest = new EstoqueProdutos(2L,30,"Trio Pequeno");
        ProdutosEntity produtos = new ProdutosEntity(1L, 1, estoqueTest, 10);
        Mockito.when(produtoController.findByIdPath(Mockito.anyLong())).thenReturn(ResponseEntity.ok(new ProdutosEntity(produtos)));
        var response = produtoController.findByIdPath(1L);
        Assertions.assertNotNull(response);
        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals(produtos.getEstoque(), response.getBody().getEstoque());
    }

    @Test
    void testProdutosGetAll(){
        Mockito.when(produtoController.listaCompleta()).thenReturn(ResponseEntity.ok(produtoList));

        ResponseEntity<List<ProdutosEntity>> produtosFuncaoController = produtoController.listaCompleta();
        List<ProdutosEntity> produtosListController = produtosFuncaoController.getBody();

        Assertions.assertNotNull(produtosListController);
        for(int i = 0; i < produtoList.size();i ++){
            Assertions.assertEquals(produtoList.get(i), produtosListController.get(i));
        }
    }


}
