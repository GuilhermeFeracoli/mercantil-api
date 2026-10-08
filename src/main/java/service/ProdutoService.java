package com.guilherme.mercantilapi.service;
import com.guilherme.mercantilapi.repository.ProdutoRepository;
import com.guilherme.mercantilapi.model.Produto;
import org.springframework.stereotype.Service;
import com.guilherme.mercantilapi.exception.ProdutoNaoEncontradoException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public List<Produto> listarProdutos() {
        return produtoRepository.findAll();
    }
    public Produto cadastrarProduto(Produto produto) {
        return produtoRepository.save(produto);
    }
    public Produto buscarProduto(Integer id) {
        return produtoRepository.findById(id)
                .orElseThrow(() ->
                        new ProdutoNaoEncontradoException("Produto não encontrado"));
    }
    public Produto alterarProduto(Integer id, Produto produto) {

        Produto produtoExistente = produtoRepository.findById(id)
                .orElseThrow(() ->
                        new ProdutoNaoEncontradoException("Produto não encontrado"));
        produtoExistente.setNome(produto.getNome());
        produtoExistente.setPreco(produto.getPreco());
        produtoExistente.setPrecoConsumidor(produto.getPrecoConsumidor());
        produtoExistente.setEstoque(produto.getEstoque());
        return produtoRepository.save(produtoExistente);
    }
    public Produto excluirProduto(Integer id) {
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() ->
                        new ProdutoNaoEncontradoException("Produto não encontrado"));
        produtoRepository.delete(produto);

        return produto;
    }
}