package com.guilherme.mercantilapi.model;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import com.guilherme.mercantilapi.validation.CpfOuCnpj;
import com.guilherme.mercantilapi.validation.DocumentoValidavel;

@CpfOuCnpj
@Entity
@Table(name = "clientes")

public class Cliente implements DocumentoValidavel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotBlank
    private String nome;
    private String endereco;
    private String telefone;
    private String email;
    private String cpf;
    private String cnpj;
    private String inscricaoEstadual;
    public Cliente() {
    }

    public String getInscricaoEstadual() {
        return inscricaoEstadual;
    }

    public void setInscricaoEstadual(String inscricaoEstadual) {
        this.inscricaoEstadual = inscricaoEstadual;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}

