package com.guilherme.mercantilapi.model;
import jakarta.validation.constraints.NotBlank;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import com.guilherme.mercantilapi.exception.EstoqueInsuficienteException;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;

@Entity
@Table(name = "produtos")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotBlank
    private String nome;
    @Positive
    private BigDecimal preco;
    @Positive
    private BigDecimal precoConsumidor;
    @PositiveOrZero
    private double estoque;

    public Produto() {
    }

    public Produto(Integer id, String nome,  BigDecimal preco, BigDecimal precoConsumidor, double estoque) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.precoConsumidor = precoConsumidor;
        this.estoque = estoque;
    }

    public String getNome() {
        return nome;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public double getEstoque() {
        return estoque;
    }

    public BigDecimal getPrecoConsumidor() {
        return precoConsumidor;
    }

    public Integer getId() {
        return id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public void setPrecoConsumidor(BigDecimal precoConsumidor) {
        this.precoConsumidor = precoConsumidor;
    }

    public void setEstoque(double estoque) {
        this.estoque = estoque;
    }

    public void darBaixa(double quantidade) {

        if (quantidade <= 0) {
            throw new IllegalArgumentException
                    ("A quantidade deve ser maior que zero");
        }
        if (quantidade > estoque) {
            throw new EstoqueInsuficienteException
                    ("Estoque insuficiente");
        }
        estoque -= quantidade;
    }
}