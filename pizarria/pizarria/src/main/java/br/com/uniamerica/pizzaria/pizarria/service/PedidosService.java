package br.com.uniamerica.pizzaria.pizarria.service;

import br.com.uniamerica.pizzaria.pizarria.dto.PedidoDTO;
import br.com.uniamerica.pizzaria.pizarria.entity.PedidoEntity;
import br.com.uniamerica.pizzaria.pizarria.repository.PedidoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

@Service
public class PedidosService {
    @Autowired
    private PedidoRepository pedidoRepository;

    @Transactional(rollbackFor = Exception.class)
    public void validaPedido (final PedidoDTO pedidoDTO) {

        var pedido = new PedidoEntity();
        BeanUtils.copyProperties(pedidoDTO, pedido);

        Assert.isTrue(!pedido.getFuncionario().equals(""), "Funcionário não pode ser nulo");
        Assert.isTrue(!pedido.getUsuario().equals(""), "Usuário não pode ser nulo");

        this.pedidoRepository.save(pedido);
    }

    @Transactional(rollbackFor = Exception.class)
    public void editaPedido (final PedidoEntity pedido){

        Assert.isTrue(!pedido.getFuncionario().equals(""), "Funcionário não pode ser nulo");
        Assert.isTrue(!pedido.getUsuario().equals(""), "Usuário não pode ser nulo");
        this.pedidoRepository.save(pedido);

    }

    @Transactional(rollbackFor = Exception.class)
    public void deletarPedido (final Long id){

        final PedidoEntity pedido1 = this.pedidoRepository.findById(id).orElse(null);

        if (pedido1 == null || pedido1.getId()!=(id)){
            throw new RuntimeException("Não foi possivel identificar o pedido informado.");
        }
        this.pedidoRepository.delete(pedido1);
    }
}
