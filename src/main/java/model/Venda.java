package com.guilherme.mercantilapi.model;
import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;
import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "vendas")
public class Venda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;
    private LocalDateTime dataHora;
    @OneToMany(
            mappedBy = "venda",
            cascade = CascadeType.ALL
    )
    private List<ItemVenda> itens = new ArrayList<>();
    public BigDecimal getTotal() {
        BigDecimal total = BigDecimal.ZERO;
        for (ItemVenda item : itens) {
            total = total.add(item.getSubtotal());
        }
        return total;
    }
    public void adicionarItem(ItemVenda item) {
        itens.add(item);
        item.setVenda(this);
    }
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    public Integer getId() {
        return id;
    }
    public Cliente getCliente() {
        return cliente;
    }
    public LocalDateTime getDataHora() {
        return dataHora;
    }
    public List<ItemVenda> getItens() {
        return itens;
    }
    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }
    public Venda() {
    }

    public void adicionarProduto(Produto produto, double quantidade) {
        produto.darBaixa(quantidade);
        BigDecimal preco;

        if (cliente == null) {
            preco = produto.getPrecoConsumidor();
        } else {
            preco = produto.getPreco();
        }

        ItemVenda item = new ItemVenda(produto, quantidade, preco);
        adicionarItem(item);
    }
}