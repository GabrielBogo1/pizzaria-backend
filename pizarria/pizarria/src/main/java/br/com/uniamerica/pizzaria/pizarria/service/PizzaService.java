package br.com.uniamerica.pizzaria.pizarria.service;

import br.com.uniamerica.pizzaria.pizarria.dto.PizzaDTO;
import br.com.uniamerica.pizzaria.pizarria.entity.PizzaEntity;
import br.com.uniamerica.pizzaria.pizarria.entity.Tamanho;
import br.com.uniamerica.pizzaria.pizarria.repository.PizzaRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

@Service
public class PizzaService {
    @Autowired
    private PizzaRepository pizzaRepository;

    @Transactional(rollbackFor = Exception.class)
    public void validaPizza (final PizzaDTO pizzaDTO){
        var pizza = new PizzaEntity();
        BeanUtils.copyProperties(pizzaDTO,pizza);

        Assert.isTrue(!pizza.getSabores().equals(""), "Sabor da pizza não pode ser nulo");

        if(pizza.getTamanho() == Tamanho.P){
            pizza.setPrecoPizza(15);
        }else if (pizza.getTamanho() == Tamanho.M) {
            pizza.setPrecoPizza(25);
        }else if (pizza.getTamanho() == Tamanho.G)
        {
            pizza.setPrecoPizza(30);
        }else {
            pizza.setPrecoPizza(45);
        }

        this.pizzaRepository.save(pizza);
    }


    @Transactional(rollbackFor = Exception.class)
    public void editaPizza (PizzaEntity pizza) {

        Assert.isTrue(!pizza.getSabores().equals(""), "Sabor da pizza não pode ser nulo");

        if(pizza.getTamanho() == Tamanho.P){
            pizza.setPrecoPizza(15);
        }else if (pizza.getTamanho() == Tamanho.M) {
            pizza.setPrecoPizza(25);
        }else if (pizza.getTamanho() == Tamanho.G)
        {
            pizza.setPrecoPizza(30);
        }else {
            pizza.setPrecoPizza(45);
        }

        this.pizzaRepository.save(pizza);
    }

    @Transactional(rollbackFor = Exception.class)
    public void deletaPizza (final Long id){

        final PizzaEntity pizza1 = this.pizzaRepository.findById(id).orElse(null);

        if (pizza1 == null || pizza1.getId()!=(id)){
            throw new RuntimeException("Não foi possivel encontrar a pizza informada");
        }
        this.pizzaRepository.delete(pizza1);
    }
}
