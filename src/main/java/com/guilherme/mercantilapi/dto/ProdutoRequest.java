package com.guilherme.mercantilapi.dto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;

public class ProdutoRequest {

    @NotBlank
    private String nome;

    @Positive
    private BigDecimal preco;

    @Positive
    private BigDecimal precoConsumidor;

    @PositiveOrZero
    private double estoque;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public BigDecimal getPrecoConsumidor() {
        return precoConsumidor;
    }

    public void setPrecoConsumidor(BigDecimal precoConsumidor) {
        this.precoConsumidor = precoConsumidor;
    }

    public double getEstoque() {
        return estoque;
    }

    public void setEstoque(double estoque) {
        this.estoque = estoque;
    }
}