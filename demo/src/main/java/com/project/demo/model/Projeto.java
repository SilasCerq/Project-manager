package com.project.demo.model;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "projeto", schema ="gerenciador")
public class Projeto {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    Long id;

    @ManyToOne
    @JoinColumn(name = "idgerente")
    Pessoa gerente;

    @Column(name="nome", nullable=false, unique=false)
    String nome;

    @CreationTimestamp
    @Column(name="data_inicio", nullable=false, unique=false)
    LocalDate dataInicio;

    @CreationTimestamp
    @Column(name="data_fim", nullable=false, unique=false)
    LocalDate dataFim;

    @Column(name="descricao", nullable=false, unique=false)
    String descricao;

    @Column(name="status", nullable=false, unique=false)
    String status;

    @Column(name="orcamento", nullable=false, unique=false)
    double orcamento;

    @Column(name="risco", nullable=false, unique=false)
    ClassificacaoRisco risco;

    @ManyToMany
    @JoinTable(
            name = "membros",
            joinColumns = @JoinColumn(name = "id_projeto"),
            inverseJoinColumns = @JoinColumn(name = "id_pessoa"))
    List<Pessoa> membros;

    public Projeto() {
    }

    public Projeto(Pessoa gerente, String nome, LocalDate dataInicio, LocalDate dataFim, String descricao, String status, double orcamento, ClassificacaoRisco risco) {
        this.gerente = gerente;
        this.nome = nome;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.descricao = descricao;
        this.status = status;
        this.orcamento = orcamento;
        this.risco = risco;
    }

    public Pessoa getGerente() {
        return gerente;
    }

    public void setGerente(Pessoa gerente) {
        this.gerente = gerente;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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

    public List<Pessoa> getMembros() {
        return membros;
    }

    public void setMembros(List<Pessoa> membros) {
        this.membros = membros;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void addMember(Pessoa pessoa){
        if(membros == null){
            membros = new ArrayList<>();
        }
        membros.add(pessoa);
    }
}
