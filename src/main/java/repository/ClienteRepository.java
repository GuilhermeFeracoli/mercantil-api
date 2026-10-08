package com.guilherme.mercantilapi.repository;
import com.guilherme.mercantilapi.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
}