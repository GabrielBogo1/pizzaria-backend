package DtoTest;

import br.com.uniamerica.pizzaria.pizarria.dto.PedidoDTO;
import br.com.uniamerica.pizzaria.pizarria.entity.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

class PedidoDtoTest {
    private UsuarioEntity usuario;

    private FuncionarioEntity funcionario;

    private List<PizzaEntity> pizzaList = new ArrayList<>();
    private List<ProdutosEntity> produtoList = new ArrayList<>();

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSSSSS");
    String dataString = "2023-09-21T02:03:38.724796100";
    LocalDateTime dataManual = LocalDateTime.parse(dataString, formatter);
    PedidoDTO pedido = new PedidoDTO("nada",usuario,20,Status.ANDAMENTO,pizzaList,produtoList,true,
            false,false,false,LocalDateTime.now(),funcionario);
    PedidoDTO pedidoVazio = new PedidoDTO();


    @Test
    void testPreco(){
        pedido.setPedidoPreco(50);
        Assertions.assertEquals(50,pedido.getPedidoPreco());
    }

    /*@Test
    void testData(){
        String dataString2 = "2024-09-21T02:03:38.724796100";
        LocalDateTime dataManual2= LocalDateTime.parse(dataString2, formatter);
        pedido.setDataPedido(LocalDate.from(dataManual2));
        Assertions.assertEquals(dataManual2,pedido.getUsuario());
    }*/

    @Test
    void testCancelamento(){
        pedido.setCancelado(true);
        Assertions.assertTrue(pedido.isCancelado());
    }

    @Test
    void testEntrega(){
        pedido.setEntrega(true);
        Assertions.assertTrue(pedido.isEntrega());
    }

    @Test
    void testDelivery(){
        pedido.setDelivery(true);
        Assertions.assertTrue(pedido.isDelivery());
    }

    @Test
    void testObservacao(){
        pedido.setObservacao("Sem picles");
        Assertions.assertEquals("Sem picles", pedido.getObservacao());
    }

    @Test
    void testStatus(){
        pedido.setStatus(Status.ANDAMENTO);
        Assertions.assertEquals(Status.ANDAMENTO,pedido.getStatus());
    }

    @Test
    void testConstrutorVazio(){
        PedidoDTO pedidoVazio2 = new PedidoDTO();
        Assertions.assertEquals(pedidoVazio, pedidoVazio2);
    }

    @Test
    void testId(){
        pedido.setId(1L);
        Assertions.assertEquals(1L, pedido.getId());
    }

    @Test
    void testComparacao(){
        UsuarioEntity usuario = new UsuarioEntity(1L, "teste");
        FuncionarioEntity funcionario = new FuncionarioEntity(1L, "TesteFun");
        PedidoDTO pedido2 = new PedidoDTO("nada",usuario,20,Status.ANDAMENTO,pizzaList,produtoList,true,
                false,false,false,LocalDateTime.now(),funcionario);
        Assertions.assertEquals(pedido, pedido2);
    }
}
