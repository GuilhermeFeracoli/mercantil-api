package com.guilherme.mercantilapi.controller;
import com.guilherme.mercantilapi.dto.ProdutoResponse;
import jakarta.validation.Valid;
import com.guilherme.mercantilapi.model.Produto;
import com.guilherme.mercantilapi.service.ProdutoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import com.guilherme.mercantilapi.dto.ProdutoRequest;
import java.util.List;

@RestController
public class ProdutoController {

    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @GetMapping("/produtos")
    public List<ProdutoResponse> listarProdutos() {
        List<Produto> produtos = produtoService.listarProdutos();
        List<ProdutoResponse> respostas = produtos.stream()
                .map(produto -> new ProdutoResponse(
                        produto.getId(),
                        produto.getNome(),
                        produto.getPreco(),
                        produto.getPrecoConsumidor(),
                        produto.getEstoque()
                ))
                .toList();
        return respostas;
    }
    @PostMapping("/produtos")
    public ProdutoResponse cadastrarProduto(
            @Valid @RequestBody ProdutoRequest request) {
        Produto produto = new Produto(
                null,
                request.getNome(),
                request.getPreco(),
                request.getPrecoConsumidor(),
                request.getEstoque());
        Produto produtoSalvo = produtoService.cadastrarProduto(produto);
        ProdutoResponse resposta = new ProdutoResponse(
                produtoSalvo.getId(),
                produtoSalvo.getNome(),
                produtoSalvo.getPreco(),
                produtoSalvo.getPrecoConsumidor(),
                produtoSalvo.getEstoque());
        return resposta;
    }
    @GetMapping("/produtos/{id}")
    public ProdutoResponse buscarProduto(@PathVariable Integer id) {
        Produto produto = produtoService.buscarProduto(id);
        ProdutoResponse resposta = new ProdutoResponse(
                produto.getId(),
                produto.getNome(),
                produto.getPreco(),
                produto.getPrecoConsumidor(),
                produto.getEstoque());
        return resposta;
    }
    @PutMapping("/produtos/{id}")
    public ProdutoResponse alterarProduto(
            @PathVariable Integer id,
            @Valid @RequestBody ProdutoRequest request) {
        Produto produto = new Produto(
                null,
                request.getNome(),
                request.getPreco(),
                request.getPrecoConsumidor(),
                request.getEstoque());
        Produto produtoAlterado = produtoService.alterarProduto(id, produto);
        ProdutoResponse resposta = new ProdutoResponse(
                produtoAlterado.getId(),
                produtoAlterado.getNome(),
                produtoAlterado.getPreco(),
                produtoAlterado.getPrecoConsumidor(),
                produtoAlterado.getEstoque());
        return resposta;
    }
    @DeleteMapping("/produtos/{id}")
    public String excluirProduto(@PathVariable Integer id) {
        produtoService.excluirProduto(id);
        return "Produto excluído com sucesso";
    }
}