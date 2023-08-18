package br.com.uniamerica.pizzaria.pizarria.service;

import br.com.uniamerica.pizzaria.pizarria.dto.ProdutosDTO;
import br.com.uniamerica.pizzaria.pizarria.entity.ProdutosEntity;
import br.com.uniamerica.pizzaria.pizarria.repository.ProdutosRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

@Service
public class ProdutosService {
    @Autowired
    ProdutosRepository produtosRepository;

    @Transactional(rollbackFor = Exception.class)
    public void validarProduto (final ProdutosDTO produtosDTO){

        var produtos = new ProdutosEntity();
        BeanUtils.copyProperties(produtosDTO,produtos);

        Assert.isTrue(produtos.getQuantProd() != 0, "Quantidade do produto não pode ser nula");


        this.produtosRepository.save(produtos);
    }

    @Transactional(rollbackFor = Exception.class)
    public void editarProduto(ProdutosEntity produtos) {

        Assert.isTrue(produtos.getQuantProd() != 0, "Quantidade do produto não pode ser nula");
        this.produtosRepository.save(produtos);
    }
    @Transactional(rollbackFor = Exception.class)
    public void deletarProduto(final Long id){

        final ProdutosEntity produto1 = this.produtosRepository.findById(id).orElse(null);

        if (produto1 == null || produto1.getId()!=(id)){
            throw new RuntimeException("Não foi possivel identificar o produto informado.");
        }
        this.produtosRepository.delete(produto1);
    }
}
