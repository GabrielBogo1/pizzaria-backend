package br.com.uniamerica.pizzaria.pizarria.service;

import br.com.uniamerica.pizzaria.pizarria.dto.EstoqueProdutoDTO;
import br.com.uniamerica.pizzaria.pizarria.entity.EstoqueProduto;
import br.com.uniamerica.pizzaria.pizarria.repository.EstoqueProdutoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

@Service
public class EstoqueProdutoService {
    @Autowired
    private EstoqueProdutoRepository estoqueProdutoRepository;

    @Transactional(rollbackFor = Exception.class)
    public void validaEstoque (final EstoqueProdutoDTO estoqueProdutoDTO){

        var estoqueProduto = new EstoqueProduto();
        BeanUtils.copyProperties(estoqueProdutoDTO,estoqueProduto);

        Assert.isTrue(!estoqueProduto.getNomeProd().equals(""),"Nome do produto não pode ser nulo");
        Assert.isTrue(estoqueProduto.getNomeProd().length() <= 100, "O nome do produto ultrapassa o limite de caracteres");

        this.estoqueProdutoRepository.save(estoqueProduto);

    }

    public void editaEstoque (EstoqueProduto estoqueProduto){

        Assert.isTrue(!estoqueProduto.getNomeProd().equals(""),"Nome do produto não pode ser nulo");
        Assert.isTrue(estoqueProduto.getNomeProd().length() <= 100, "O nome do produto ultrapassa o limite de caracteres");
        this.estoqueProdutoRepository.save(estoqueProduto);

    }

    @Transactional(rollbackFor = Exception.class)
    public void deletarProduto (final Long id){

        final EstoqueProduto estoque1 = this.estoqueProdutoRepository.findById(id).orElse(null);

        if (estoque1 == null || !estoque1.getId().equals(id)){
            throw new RuntimeException("Não foi possivel encontrar o ID do estoque informado");
        }
        this.estoqueProdutoRepository.delete(estoque1);

    }
}
