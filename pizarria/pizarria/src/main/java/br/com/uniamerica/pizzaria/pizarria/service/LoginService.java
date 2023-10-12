package br.com.uniamerica.pizzaria.pizarria.service;

import br.com.uniamerica.pizzaria.pizarria.dto.LoginDTO;
import br.com.uniamerica.pizzaria.pizarria.entity.FuncionarioEntity;
import br.com.uniamerica.pizzaria.pizarria.entity.Login;
import br.com.uniamerica.pizzaria.pizarria.entity.UsuarioEntity;
import br.com.uniamerica.pizzaria.pizarria.payload.response.LoginMessage;
import br.com.uniamerica.pizzaria.pizarria.repository.FuncionarioRepository;
import br.com.uniamerica.pizzaria.pizarria.repository.UsuarioRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.Optional;

@Service
public class LoginService {

    @Autowired
    FuncionarioRepository funcionarioRepository;
    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    PasswordEncoder passwordEncoder;


    public LoginMessage validaLogin (LoginDTO loginDTO) {
        FuncionarioEntity funcionario1 = funcionarioRepository.findByEmail(loginDTO.getEmail());
        UsuarioEntity usuario1 = usuarioRepository.findByEmail(loginDTO.getEmail());
        if (funcionario1 != null) {
            String senhaLogin = loginDTO.getSenha();
            String senhaEncriptada = funcionario1.getSenha();

            boolean validaSenha = passwordEncoder.matches(senhaLogin, senhaEncriptada);
            if (validaSenha) {
                Optional<FuncionarioEntity> funcionario = funcionarioRepository.findOneByEmailAndSenha(loginDTO.getEmail(), senhaEncriptada);
                if (funcionario.isPresent()) {
                    return new LoginMessage("Login realizado com sucesso", true);
                } else {
                    return new LoginMessage("Erro ao efetuar login");
                }
            } else {
                return new LoginMessage("Senha inválida");
            }
        } else if (usuario1 != null) {
            String senhaLogin = loginDTO.getSenha();
            String usuarioEncriptada = usuario1.getSenha();
            boolean validaSenhaUsuario = passwordEncoder.matches(senhaLogin, usuarioEncriptada);
            if (validaSenhaUsuario) {
                return new LoginMessage("Login realizado com sucesso", false);
            } else {
                return new LoginMessage("Erro ao efetuar login");
            }
        } else {
            return new LoginMessage("Email inválido");
        }
    }
}
