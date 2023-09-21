package IntegracaoTest;

import br.com.uniamerica.pizzaria.pizarria.repository.EnderecoRepository;
import br.com.uniamerica.pizzaria.pizarria.repository.SaboresRepository;
import br.com.uniamerica.pizzaria.pizarria.repository.UsuarioRepository;
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
}
