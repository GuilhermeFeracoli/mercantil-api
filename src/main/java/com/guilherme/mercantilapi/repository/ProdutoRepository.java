package com.guilherme.mercantilapi.repository;

import com.guilherme.mercantilapi.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {
}