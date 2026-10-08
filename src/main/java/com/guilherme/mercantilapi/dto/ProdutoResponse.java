package com.guilherme.mercantilapi.dto;
import java.math.BigDecimal;

public class ProdutoResponse {
    private Integer id;
    private String nome;
    private BigDecimal preco;
    private BigDecimal precoConsumidor;
    private double estoque;
    public ProdutoResponse (Integer id, String nome, BigDecimal preco, BigDecimal precoConsumidor, double estoque) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.precoConsumidor = precoConsumidor;
        this.estoque = estoque;
    }

    public Integer getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public BigDecimal getPrecoConsumidor() {
        return precoConsumidor;
    }

    public double getEstoque() {
        return estoque;
    }
}
