package com.guilherme.mercantilapi.service;
import com.guilherme.mercantilapi.dto.ItemVendaRequest;
import com.guilherme.mercantilapi.exception.VendaNaoEncontradaException;
import com.guilherme.mercantilapi.repository.VendaRepository;
import com.guilherme.mercantilapi.repository.ProdutoRepository;
import com.guilherme.mercantilapi.model.Venda;
import org.springframework.stereotype.Service;
import com.guilherme.mercantilapi.repository.ClienteRepository;
import com.guilherme.mercantilapi.dto.VendaRequest;
import com.guilherme.mercantilapi.model.Cliente;
import com.guilherme.mercantilapi.exception.ClienteNaoEncontradoException;
import java.util.List;
import com.guilherme.mercantilapi.model.Produto;
import com.guilherme.mercantilapi.model.ItemVenda;
import com.guilherme.mercantilapi.exception.ProdutoNaoEncontradoException;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.math.BigDecimal;

@Service
public class VendaService {

    private final VendaRepository vendaRepository;
    private final ProdutoRepository produtoRepository;
    private final ClienteRepository clienteRepository;

    public VendaService(VendaRepository vendaRepository, ProdutoRepository produtoRepository, ClienteRepository clienteRepository) {
        this.vendaRepository = vendaRepository;
        this.produtoRepository = produtoRepository;
        this.clienteRepository = clienteRepository;
    }
    public List<Venda> listarVendas() {
        return vendaRepository.findAll();
    }
    @Transactional
    public Venda cadastrarVenda(VendaRequest vendaRequest) {

        Venda venda = new Venda();
        venda.setDataHora(LocalDateTime.now());
        Cliente cliente = null;

        if (vendaRequest.getClienteId() != null) {

            cliente = clienteRepository.findById(vendaRequest.getClienteId())
                    .orElseThrow(() ->
                            new ClienteNaoEncontradoException("Cliente não encontrado"));
        }

        venda.setCliente(cliente);

        for (ItemVendaRequest item : vendaRequest.getItens()) {

            if (item.getQuantidade() <= 0) {
                throw new IllegalArgumentException(
                        "Quantidade deve ser maior que zero"
                );
            }

            Produto produto = produtoRepository.findById(item.getProdutoId())
                    .orElseThrow(() ->
                            new ProdutoNaoEncontradoException("Produto não encontrado"));
            produto.darBaixa(item.getQuantidade());
            BigDecimal precoUnitario;

            if (cliente == null) {
                precoUnitario = produto.getPrecoConsumidor();
            } else {
                precoUnitario = produto.getPreco();
            }

            ItemVenda itemVenda = new ItemVenda(
                    produto,
                    item.getQuantidade(),
                    precoUnitario
            );

            venda.adicionarItem(itemVenda);
        }

        return vendaRepository.save(venda);
    }
    public Venda buscarVenda(Integer id) {
        return vendaRepository.findById(id)
                .orElseThrow(() ->
                        new VendaNaoEncontradaException("Venda não encontrada"));
    }
}



