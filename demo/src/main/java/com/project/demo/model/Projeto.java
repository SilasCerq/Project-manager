package com.project.demo.model;

import java.time.LocalDate;

public class Projeto {

    Integer id;

    Integer idGerente;

    String name;

    LocalDate dataInicio;

    LocalDate dataFim;

    String descricao;

    String status;

    double orcamento;

    ClassificacaoRisco risco;


    public Projeto() {
    }

    public Projeto(Integer idGerente, String name, LocalDate dataInicio, LocalDate dataFim, String descricao, String status, double orcamento, ClassificacaoRisco risco) {
        this.idGerente = idGerente;
        this.name = name;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.descricao = descricao;
        this.status = status;
        this.orcamento = orcamento;
        this.risco = risco;
    }

    public Integer getIdGerente() {
        return idGerente;
    }

    public void setIdGerente(Integer idGerente) {
        this.idGerente = idGerente;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDate getDataFim() {
        return dataFim;
    }

    public void setDataFim(LocalDate dataFim) {
        this.dataFim = dataFim;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getOrcamento() {
        return orcamento;
    }

    public void setOrcamento(double orcamento) {
        this.orcamento = orcamento;
    }

    public ClassificacaoRisco getRisco() {
        return risco;
    }

    public void setRisco(ClassificacaoRisco risco) {
        this.risco = risco;
    }
}
