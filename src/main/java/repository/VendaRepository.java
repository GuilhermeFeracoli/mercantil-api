package com.guilherme.mercantilapi.repository;

import com.guilherme.mercantilapi.model.Venda;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendaRepository extends JpaRepository<Venda, Integer> {
}