package DtoTest;

import br.com.uniamerica.pizzaria.pizarria.dto.EnderecoDTO;
import br.com.uniamerica.pizzaria.pizarria.entity.Endereco;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

//@ContextConfiguration(classes = TestConfig.class)
class EnderecoDtoTests {
    EnderecoDTO endereco2 = new EnderecoDTO(1L,"Rua Das Pedras","Porto Meira",1,"85854000","Perto da Technos");
    EnderecoDTO endereco = new EnderecoDTO();
    @Test
    void testNomeRua(){
        endereco.setRua("Rua das Dálias");
        Assertions.assertEquals("Rua das Dálias", endereco.getRua());
    }


    @Test
    void testConstrutorComArgumentos() {
        Long id = 1L;
        String rua = "Rua Exemplo";
        String bairro = "Bairro Exemplo";
        int numCasa = 123;
        String cep = "12345-678";
        String observ = "Observação Exemplo";

        Endereco endereco = new Endereco(id, rua, bairro, numCasa, cep, observ);

        assertEquals(id, endereco.getId());
        assertEquals(rua, endereco.getRua());
        assertEquals(bairro, endereco.getBairro());
        assertEquals(numCasa, endereco.getNumCasa());
        assertEquals(cep, endereco.getCep());
        assertEquals(observ, endereco.getObserv());
    }

    @Test
    void testConstrutorPadrao() {
        Endereco endereco = new Endereco();

        // Verifique se os campos do objeto criado com o construtor padrão estão nulos ou com valores padrão
        assertNull(endereco.getId());
        assertNull(endereco.getRua());
        assertNull(endereco.getBairro());
        assertEquals(0, endereco.getNumCasa());
        assertNull(endereco.getCep());
        assertNull(endereco.getObserv());
    }

    @Test
    void testNomeBairro(){
        endereco.setBairro("Vila Portes");
        Assertions.assertEquals("Vila Portes", endereco.getBairro());
    }

    @Test
    void testNumCasa(){
        endereco.setNumCasa(123);
        Assertions.assertEquals(123, endereco.getNumCasa());
    }

    @Test
    void testEnderecoId(){
        endereco.setId(1L);
        Assertions.assertEquals(1L, endereco.getId());
    }

    @Test
    void testCep(){
        endereco.setCep("908090");
        Assertions.assertEquals("908090", endereco.getCep());
    }

    @Test
    void testComparacaoDosEnderecos(){
        EnderecoDTO endereco3 = new EnderecoDTO(1L,"Rua Das Pedras","Porto Meira",1,"85854000","Perto da Technos");
        Assertions.assertEquals(endereco2, endereco3);
    }

    @Test
    void testConstrutorVazio(){
        EnderecoDTO endereco3 = new EnderecoDTO();
        Assertions.assertEquals(endereco, endereco3);
    }

    @Test
    void testObservacao(){
        endereco.setObserv("Perto do Poste");
        Assertions.assertEquals("Perto do Poste", endereco.getObserv());
    }


}
