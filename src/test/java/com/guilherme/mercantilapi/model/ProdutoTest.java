package com.guilherme.mercantilapi.model;

import com.guilherme.mercantilapi.exception.EstoqueInsuficienteException;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


class ProdutoTest {

    @Test
    void deveDarBaixaQuandoQuantidadeForValida() {
        Produto produto = new Produto(
                1,
                "Arroz",
                new BigDecimal("20.00"),
                new BigDecimal("25.00"),
                10.0);
        produto.darBaixa(3.0);

        assertEquals(7.0, produto.getEstoque());
    }
    @Test
    void naoDeveDarBaixaQuandoQuantidadeForZero() {
        Produto produto = new Produto(
                1,
                "Arroz",
                new BigDecimal("20.00"),
                new BigDecimal("25.00"),
                10.0);
        assertThrows(
                IllegalArgumentException.class,
                () -> produto.darBaixa(0));
    }
    @Test
    void naoDeveDarBaixaQuandoEstoqueForInsuficiente() {
        Produto produto = new Produto(
                1,
                "Arroz",
                new BigDecimal("20.00"),
                new BigDecimal("25.00"),
                10.0);
        assertThrows(
                EstoqueInsuficienteException.class,
                () -> produto.darBaixa(11.0));
        assertEquals(10.0, produto.getEstoque());
    }
    @Test
    void naoDeveDarBaixaQuandoQuantidadeForNegativa() {
        Produto produto = new Produto(
                1,
                "Arroz",
                new BigDecimal("20.00"),
                new BigDecimal("25.00"),
                10.0);
        assertThrows(
                IllegalArgumentException.class,
                () -> produto.darBaixa(-1.0));
        assertEquals(10.0, produto.getEstoque());
    }
    @Test
    void devePermitirBaixaQuandoQuantidadeForIgualAoEstoque() {
        Produto produto = new Produto(
                1,
                "Arroz",
                new BigDecimal("20.00"),
                new BigDecimal("25.00"),
                10.0);
        produto.darBaixa(10.0);
        assertEquals(0.0, produto.getEstoque());
    }

@Test
void devePermitirBaixaDeEstoqueFracionado() {
    Produto produto = new Produto(
            1,
            "Queijo",
            new BigDecimal("30.00"),
            new BigDecimal("40.00"),
            10.0);

    produto.darBaixa(2.5);

    assertEquals(7.5, produto.getEstoque(), 0.0001);
}
}