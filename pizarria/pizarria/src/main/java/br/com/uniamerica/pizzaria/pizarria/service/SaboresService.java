package br.com.uniamerica.pizzaria.pizarria.service;

import br.com.uniamerica.pizzaria.pizarria.dto.FuncionarioDTO;
import br.com.uniamerica.pizzaria.pizarria.dto.SaboresDTO;
import br.com.uniamerica.pizzaria.pizarria.entity.FuncionarioEntity;
import br.com.uniamerica.pizzaria.pizarria.entity.SaboresEntity;
import br.com.uniamerica.pizzaria.pizarria.repository.SaboresRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

@Service
public class SaboresService {

    @Autowired
    private SaboresRepository saboresRepository;

    @Transactional(rollbackFor = Exception.class)
    public void validaSabor (final SaboresDTO saboresDTO){
        var sabores = new SaboresEntity();
        BeanUtils.copyProperties(saboresDTO,sabores);


        Assert.isTrue(!sabores.getNomeSabor().equals(""), "Nome do sabor não pode ser nulo");
        Assert.isTrue(sabores.getNomeSabor().length() <= 100, "Nome excede o limite de caracteres");

        this.saboresRepository.save(sabores);
    }

    @Transactional(rollbackFor = Exception.class)
    public void editaSabor (final SaboresEntity sabores){

        Assert.isTrue(!sabores.getNomeSabor().equals(""), "Nome do sabor não pode ser nulo");
        Assert.isTrue(sabores.getNomeSabor().length() <= 100, "Nome excede o limite de caracteres");

        this.saboresRepository.save(sabores);
    }

    @Transactional(rollbackFor = Exception.class)
    public void deletaSabor (final Long id){
        final SaboresEntity sabores1 = this.saboresRepository.findById(id).orElse(null);

        if (sabores1 == null || !sabores1.getId().equals(id)){
            throw new RuntimeException("Não foi possivel encontrar o funcionário.");
        }

        this.saboresRepository.delete(sabores1);
    }
}
