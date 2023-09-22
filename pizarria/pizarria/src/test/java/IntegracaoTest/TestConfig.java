package IntegracaoTest;

import br.com.uniamerica.pizzaria.pizarria.repository.*;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestConfig {
    @Bean
    public SaboresRepository saboresRepository() {
        return Mockito.mock(SaboresRepository.class);
    }

    @Bean
    public EnderecoRepository enderecoRepository() {
        return Mockito.mock(EnderecoRepository.class);
    }

    @Bean
    public UsuarioRepository usuarioRepository() {
        return Mockito.mock(UsuarioRepository.class);
    }

    @Bean
    public EstoqueProdutoRepository estoqueProdutoRepository() {
        return Mockito.mock(EstoqueProdutoRepository.class);
    }

    @Bean
    public FuncionarioRepository funcionarioRepository() {
        return Mockito.mock(FuncionarioRepository.class);
    }

    @Bean
    public LoginRepository loginRepository() {
        return Mockito.mock(LoginRepository.class);
    }

    @Bean
    public PizzaRepository pizzaRepository() {
        return Mockito.mock(PizzaRepository.class);
    }

    @Bean
    public ProdutosRepository produtosRepository() {
        return Mockito.mock(ProdutosRepository.class);
    }

    @Bean
    public PedidoRepository pedidoRepository() {
        return Mockito.mock(PedidoRepository.class);
    }
}
