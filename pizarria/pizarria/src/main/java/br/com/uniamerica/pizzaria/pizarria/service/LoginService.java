package br.com.uniamerica.pizzaria.pizarria.service;

import br.com.uniamerica.pizzaria.pizarria.dto.LoginDTO;
import br.com.uniamerica.pizzaria.pizarria.entity.FuncionarioEntity;
import br.com.uniamerica.pizzaria.pizarria.entity.Login;
import br.com.uniamerica.pizzaria.pizarria.repository.LoginRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

@Service
public class LoginService {

    @Autowired
    private LoginRepository loginRepository;


    @Transactional(rollbackFor = Exception.class)
    public void validaLogin (LoginDTO loginDTO){
        var login = new Login();
        BeanUtils.copyProperties(loginDTO,login);

        Assert.isTrue(!login.getLogin().equals(""), "Login não pode ser nulo");
        Assert.isTrue(!login.getSenha().equals(""), "Senha não pode ser nula");

        this.loginRepository.save(login);
    }

    @Transactional(rollbackFor = Exception.class)
    public void editaLogin (final Login login){

        Assert.isTrue(!login.getLogin().equals(""), "Login não pode ser nulo");
        Assert.isTrue(!login.getSenha().equals(""), "Senha não pode ser nula");

        this.loginRepository.save(login);
    }

    @Transactional(rollbackFor = Exception.class)
    public void deletaLogin (final Long id){
        final Login login1 = this.loginRepository.findById(id).orElse(null);

        if (login1 == null || !login1.getId().equals(id)){
            throw new RuntimeException("Não foi possivel encontrar o login.");
        }

        this.loginRepository.delete(login1);
    }


}
