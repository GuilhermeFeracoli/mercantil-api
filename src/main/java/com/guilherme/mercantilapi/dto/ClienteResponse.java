package com.guilherme.mercantilapi.dto;


public class ClienteResponse {
        private Integer id;
        private String nome;
        private String endereco;
        private String telefone;
        private String email;
        private String cpf;
        private String cnpj;
        private String inscricaoEstadual;

    public ClienteResponse(Integer id, String nome, String endereco, String telefone, String email, String cpf, String cnpj, String inscricaoEstadual) {
        this.id = id;
        this.nome = nome;
        this.endereco = endereco;
        this.telefone = telefone;
        this.email = email;
        this.cpf = cpf;
        this.cnpj = cnpj;
        this.inscricaoEstadual = inscricaoEstadual;
    }
    public Integer getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public String getTelefone() {
        return telefone;
    }
    public String getEmail() {
        return email;
    }

    public String getCpf() {
        return cpf;
    }

    public String getCnpj() {
        return cnpj;
    }

    public String getInscricaoEstadual() {
        return inscricaoEstadual;
    }
}
