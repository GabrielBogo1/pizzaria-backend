package IntegracaoTest;

import br.com.uniamerica.pizzaria.pizarria.controller.PedidoController;
import br.com.uniamerica.pizzaria.pizarria.dto.LoginDTO;
import br.com.uniamerica.pizzaria.pizarria.dto.PedidoDTO;
import br.com.uniamerica.pizzaria.pizarria.entity.*;
import br.com.uniamerica.pizzaria.pizarria.repository.*;
import br.com.uniamerica.pizzaria.pizarria.service.PedidosService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@SpringBootTest
@ContextConfiguration(classes = TestConfig.class)
public class PedidosIntegracaoTest {
    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    FuncionarioRepository funcionarioRepository;
    @Autowired
    EnderecoRepository enderecoRepository;
    @Autowired
    EstoqueProdutoRepository estoqueRepository;

    @Autowired
    LoginRepository loginRepository;

    @Autowired
    SaboresRepository saborRepository;

    @Autowired
    PizzaRepository pizzaRepository;

    @Autowired
    ProdutosRepository produtoRepository;

    @Autowired
    PedidoRepository pedidoRepository;
    @MockBean
    PedidoController pedidoController;


    private List<UsuarioEntity> clienteList;

    private List<FuncionarioEntity> funcionarioList;

    private List<Endereco> enderecoList;

    private List<EstoqueProdutos> estoqueList;

    private List<Login> loginList;

    private List<SaboresEntity> saborList;

    private List<PizzaEntity> pizzaList;

    private List<ProdutosEntity> produtoList;

    private List<PedidoEntity> pedidoList;




    @BeforeEach
    void injectData() {
        UsuarioEntity cliente = new UsuarioEntity(1L, "cliente");
        UsuarioEntity cliente2 = new UsuarioEntity(2L, "cliente2");
        clienteList = new ArrayList<>();
        clienteList.add(cliente);
        clienteList.add(cliente2);

        FuncionarioEntity funcionario = new FuncionarioEntity(1L, "funcionario");
        FuncionarioEntity funcionario2 = new FuncionarioEntity(2L, "funcionario2");
        funcionarioList = new ArrayList<>();
        funcionarioList.add(funcionario);
        funcionarioList.add(funcionario2);

        Endereco endereco = new Endereco(1L,"Rua alou","Vila C",303,"Casa","92452-2342");
        Endereco endereco2 = new Endereco(2L,"Rua Salve","Vila B",231,"Casa","73254-6243");
        enderecoList = new ArrayList<>();
        enderecoList.add(endereco);
        enderecoList.add(endereco2);

        EstoqueProdutos estoque = new EstoqueProdutos(1L,10,"Coca-cola");
        EstoqueProdutos estoque2 = new EstoqueProdutos(2L,7,"Guaraná");
        estoqueList = new ArrayList<>();
        estoqueList.add(estoque);
        estoqueList.add(estoque2);

        Login login = new Login(1L,"admin","admin");
        Login login2 = new Login(2L,"nimda","nimda");
        loginList = new ArrayList<>();
        loginList.add(login);
        loginList.add(login2);

        SaboresEntity sabor = new SaboresEntity(1L,"Queijo");
        SaboresEntity sabor2 = new SaboresEntity(2L,"Frango");
        saborList = new ArrayList<>();
        saborList.add(sabor);
        saborList.add(sabor2);

        PizzaEntity pizza = new PizzaEntity(1L,saborList, 30, 1, Tamanho.P);
        PizzaEntity pizza2 = new PizzaEntity(2L,saborList,60,2,Tamanho.M);
        pizzaList = new ArrayList<>();
        pizzaList.add(pizza);
        pizzaList.add(pizza2);

        ProdutosEntity produto = new ProdutosEntity(1L,1,estoque,20);
        ProdutosEntity produto2 = new ProdutosEntity(2L,1,estoque2,30);
        produtoList = new ArrayList<>();
        produtoList.add(produto);
        produtoList.add(produto2);

        PedidoEntity pedido = new PedidoEntity(1L,"Observation",cliente,20,
                Status.ANDAMENTO,pizzaList,produtoList,false,true,false,false, LocalDateTime.now(),funcionario);
        PedidoEntity pedido2 = new PedidoEntity(2L,"Observation2",cliente2,20,
                Status.ANDAMENTO,pizzaList,produtoList,false,true,false,false, LocalDateTime.now(),funcionario2);
        pedidoList = new ArrayList<>();
        pedidoList.add(pedido);
        pedidoList.add(pedido2);

        Mockito.when(usuarioRepository.save(cliente)).thenReturn(cliente);
        Mockito.when(usuarioRepository.save(cliente2)).thenReturn(cliente2);
        Mockito.when(usuarioRepository.findById(1L)).thenReturn(Optional.of(cliente));
        Mockito.when(usuarioRepository.findById(2L)).thenReturn(Optional.of(cliente2));
        Mockito.when(usuarioRepository.findAll()).thenReturn(clienteList);

        Mockito.when(funcionarioRepository.save(funcionario)).thenReturn(funcionario);
        Mockito.when(funcionarioRepository.save(funcionario2)).thenReturn(funcionario2);
        Mockito.when(funcionarioRepository.findById(1L)).thenReturn(Optional.of(funcionario));
        Mockito.when(funcionarioRepository.findById(2L)).thenReturn(Optional.of(funcionario2));
        Mockito.when(funcionarioRepository.findAll()).thenReturn(funcionarioList);

        Mockito.when(enderecoRepository.save(endereco)).thenReturn(endereco);
        Mockito.when(enderecoRepository.save(endereco2)).thenReturn(endereco2);
        Mockito.when(enderecoRepository.findById(1L)).thenReturn(Optional.of(endereco));
        Mockito.when(enderecoRepository.findById(2L)).thenReturn(Optional.of(endereco2));
        Mockito.when(enderecoRepository.findAll()).thenReturn(enderecoList);

        Mockito.when(estoqueRepository.save(estoque)).thenReturn(estoque);
        Mockito.when(estoqueRepository.save(estoque2)).thenReturn(estoque2);
        Mockito.when(estoqueRepository.findById(1L)).thenReturn(Optional.of(estoque));
        Mockito.when(estoqueRepository.findById(2L)).thenReturn(Optional.of(estoque2));
        Mockito.when(estoqueRepository.findAll()).thenReturn(estoqueList);

        Mockito.when(loginRepository.save(login)).thenReturn(login);
        Mockito.when(loginRepository.save(login2)).thenReturn(login2);
        Mockito.when(loginRepository.findById(1L)).thenReturn(Optional.of(login));
        Mockito.when(loginRepository.findById(2L)).thenReturn(Optional.of(login2));
        Mockito.when(loginRepository.findAll()).thenReturn(loginList);

        Mockito.when(saborRepository.save(sabor)).thenReturn(sabor);
        Mockito.when(saborRepository.save(sabor2)).thenReturn(sabor2);
        Mockito.when(saborRepository.findById(1L)).thenReturn(Optional.of(sabor));
        Mockito.when(saborRepository.findById(2L)).thenReturn(Optional.of(sabor2));
        Mockito.when(saborRepository.findAll()).thenReturn(saborList);

        Mockito.when(pizzaRepository.save(pizza)).thenReturn(pizza2);
        Mockito.when(pizzaRepository.save(pizza2)).thenReturn(pizza2);
        Mockito.when(pizzaRepository.findById(1L)).thenReturn(Optional.of(pizza));
        Mockito.when(pizzaRepository.findById(2L)).thenReturn(Optional.of(pizza2));
        Mockito.when(pizzaRepository.findAll()).thenReturn(pizzaList);

        Mockito.when(produtoRepository.save(produto)).thenReturn(produto);
        Mockito.when(produtoRepository.save(produto2)).thenReturn(produto2);
        Mockito.when(produtoRepository.findById(1L)).thenReturn(Optional.of(produto));
        Mockito.when(produtoRepository.findById(2L)).thenReturn(Optional.of(produto2));
        Mockito.when(produtoRepository.findAll()).thenReturn(produtoList);

        Mockito.when(pedidoRepository.save(pedido)).thenReturn(pedido);
        Mockito.when(pedidoRepository.save(pedido2)).thenReturn(pedido2);
        Mockito.when(pedidoRepository.findById(1L)).thenReturn(Optional.of(pedido));
        Mockito.when(pedidoRepository.findById(2L)).thenReturn(Optional.of(pedido2));
        Mockito.when(pedidoRepository.findAll()).thenReturn(pedidoList);

        Mockito.when(pedidoRepository.findByStatus(Status.ANDAMENTO)).thenReturn(pedidoList);

        Mockito.when(pedidoRepository.findByDelivery(true)).thenReturn(pedidoList);
    }


    @Test
    void testPedidoPost() {
        Mockito.when(pedidoController.cadastrarPedido(Mockito.any(PedidoDTO.class))).thenReturn(ResponseEntity.ok("Registro cadastrado com sucesso"));
        UsuarioEntity usuario = new UsuarioEntity(1L, "testenome");
        FuncionarioEntity funcionario = new FuncionarioEntity(1L, "funcionarioteste");
        var pedido = pedidoController.cadastrarPedido(new PedidoDTO("nada",usuario,20,Status.ANDAMENTO,pizzaList,produtoList,true,
                false,false,false,LocalDateTime.now(),funcionario));
        Assertions.assertEquals("Registro cadastrado com sucesso", pedido.getBody());
    }

    @Test
    void testPedidoPostErro() {
        Mockito.when(pedidoController.cadastrarPedido(Mockito.any(PedidoDTO.class))).thenReturn(ResponseEntity.badRequest().body("Error: Nome do funcionario não pode ser nulo"));

        var pedido = pedidoController.cadastrarPedido(new PedidoDTO());
        Assertions.assertNotNull(pedido);
        Assertions.assertEquals("Error: Nome do funcionario não pode ser nulo", pedido.getBody());
    }

    @Test
    void testPedidoPut(){
        Mockito.when(pedidoController.editarPedido(Mockito.anyLong(), Mockito.any(PedidoEntity.class))).thenReturn(ResponseEntity.ok("Registro Cadastrado com Sucesso"));

        PedidoEntity pedido = new PedidoEntity();

        var pedido1 = pedidoController.editarPedido(1L, pedido);

        Assertions.assertNotNull(pedido1);
        Assertions.assertEquals("Registro Cadastrado com Sucesso", pedido1.getBody());
    }

    @Test
    void testPutPedido(){

        UsuarioEntity usuario = new UsuarioEntity(1L,"pedro");
        FuncionarioEntity funcionarioT = new FuncionarioEntity(1L,"salve");
        PedidoEntity pedido = new PedidoEntity("nada",usuario,20,Status.ANDAMENTO,pizzaList,produtoList,true,
                false,false,false,LocalDateTime.now(),funcionarioT);
        pedido.setId(1L);

        var pedido1 = pedidoController.editarPedido(1L,pedido);

        Assertions.assertEquals("Registro Cadastrado com Sucesso", pedido1.getBody());
    }
    @Test
    void testLoginPutError(){
        Mockito.when(pedidoController.editarPedido(Mockito.anyLong(), Mockito.any(PedidoEntity.class))).thenReturn(ResponseEntity.badRequest().body("Nao foi possivel indentificar o registro informado"));
        UsuarioEntity usuario = new UsuarioEntity(1L,"pedro");
        FuncionarioEntity funcionarioT = new FuncionarioEntity(1L,"salve");
        PedidoEntity pedido = new PedidoEntity("nada",usuario,20,Status.ANDAMENTO,pizzaList,produtoList,true,
                false,false,false,LocalDateTime.now(),funcionarioT);

        var pedido1 = pedidoController.editarPedido(1L, pedido);

        Assertions.assertNotNull(pedido1);
        Assertions.assertEquals("Nao foi possivel indentificar o registro informado", pedido1.getBody());
    }

    @Test
    void testLoginDelete(){
        Mockito.when(pedidoController.deletarPedido(Mockito.anyLong())).thenReturn(ResponseEntity.ok("excluído"));

        var response = pedidoController.deletarPedido(1L);
        Assertions.assertNotNull(response);
        Assertions.assertEquals("excluído", response.getBody());
    }

    @Test
    void testPedidoDeleteErrado(){
        var pedido = pedidoController.deletarPedido(20L);
        Assertions.assertEquals("ERRor: Não foi possivel identificar o registro informado", pedido.getBody());
    }

    @Test
    void testPedidoGetId(){
        UsuarioEntity usuario = new UsuarioEntity(1L,"pedro");
        FuncionarioEntity funcionarioT = new FuncionarioEntity(1L,"salve");
        PedidoEntity pedido = new PedidoEntity("nada",usuario,20,Status.ANDAMENTO,pizzaList,produtoList,true,
                false,false,false,LocalDateTime.now(),funcionarioT);
        Mockito.when(pedidoController.findByIdPath(Mockito.anyLong())).thenReturn(ResponseEntity.ok(pedido));

        var response = pedidoController.findByIdPath(1L);
        Assertions.assertNotNull(response);
        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals(pedido.getUsuario(), response.getBody().getUsuario());
    }

    @Test
    void testFindAllPedidos(){
        ResponseEntity<List<PedidoEntity>> pedidoFuncaoController = pedidoController.listaCompleta();
        List<PedidoEntity> pedidoListController = pedidoFuncaoController.getBody();
        System.out.println(pedidoFuncaoController);
        Assertions.assertNotNull(pedidoListController);
        for(int i = 0; i < pedidoList.size();i ++){
            Assertions.assertEquals(pedidoList.get(i), pedidoListController.get(i));
        }
    }

    @Test
    void testFindAndamentoPedidos(){
        PedidoRepository pedidoRepository = Mockito.mock(PedidoRepository.class);
        Mockito.when(pedidoRepository.findByStatus(Mockito.any(Status.class))).thenReturn(pedidoList);
        PedidoController pedidoController = new PedidoController(pedidoRepository);

        ResponseEntity<List<PedidoEntity>> pedidoFuncaoController = pedidoController.solicitados();
        List<PedidoEntity> pedidoListController = pedidoFuncaoController.getBody();
        System.out.println(pedidoFuncaoController);
        Assertions.assertNotNull(pedidoListController);
        for(int i = 0; i < pedidoList.size();i ++){
            Assertions.assertEquals(pedidoList.get(i), pedidoListController.get(i));
        }

    }

    @Test
    void testFindDeliveryPedidos() {
        PedidoRepository pedidoRepository = Mockito.mock(PedidoRepository.class);
        Mockito.when(pedidoRepository.findByDelivery(Mockito.anyBoolean())).thenReturn(List.of(new PedidoEntity()));
        PedidoController pedidoController = new PedidoController(pedidoRepository);

        ResponseEntity<?> responseEntity = pedidoController.delivery(true);

        Assertions.assertNotNull(responseEntity);
        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Assertions.assertTrue(responseEntity.getBody() instanceof Map);

    }



}
