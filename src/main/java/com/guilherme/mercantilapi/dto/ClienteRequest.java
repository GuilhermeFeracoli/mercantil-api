package com.guilherme.mercantilapi.dto;
import jakarta.validation.constraints.NotBlank;
import com.guilherme.mercantilapi.validation.DocumentoValidavel;
import com.guilherme.mercantilapi.validation.CpfOuCnpj;

@CpfOuCnpj
public class ClienteRequest implements DocumentoValidavel {
    @NotBlank
    private String nome;
    private String endereco;
    private String telefone;
    private String email;
    private String cpf;
    private String cnpj;
    private String inscricaoEstadual;

    public ClienteRequest() {
    }
    public String getInscricaoEstadual() {
        return inscricaoEstadual;
    }

    public void setInscricaoEstadual(String inscricaoEstadual) {
        this.inscricaoEstadual = inscricaoEstadual;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }



}
