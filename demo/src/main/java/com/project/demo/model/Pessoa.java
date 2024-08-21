package com.project.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDate;

public class Pessoa {

    Integer id;

    String name;

    LocalDate dataNascimento;

    String cpf;

    Boolean funcionario;

    Boolean gerente;

    public Pessoa() {

    }

    public Pessoa(String name, LocalDate dataNascimento, String cpf, Boolean funcionario, Boolean gerente) {
        this.name = name;
        this.dataNascimento = dataNascimento;
        this.cpf = cpf;
        this.funcionario = funcionario;
        this.gerente = gerente;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Boolean getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Boolean funcionario) {
        this.funcionario = funcionario;
    }

    public Boolean getGerente() {
        return gerente;
    }

    public void setGerente(Boolean gerente) {
        this.gerente = gerente;
    }
}
