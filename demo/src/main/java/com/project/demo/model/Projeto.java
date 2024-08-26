package com.project.demo.model;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "projeto", schema ="gerenciador")
public class Projeto {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "projeto_seq_gen")
    @SequenceGenerator(name = "projeto_seq_gen", sequenceName = "gerenciador.projeto_id_seq", allocationSize = 1)
    Long id;

    @ManyToOne
    @JoinColumn(name = "idgerente", nullable = false)
    Pessoa gerente;

    @Column(name="nome", nullable = false)
    String nome;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name="data_inicio")
    LocalDate dataInicio;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name="data_previsao_fim")
    LocalDate dataPrevisaoFim;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name="data_fim")
    LocalDate dataFim;

    @Column(name="descricao")
    String descricao;

    @Column(name="status")
    String status;

    @Column(name="orcamento")
    double orcamento;

    @Enumerated(EnumType.STRING)
    @Column(name = "risco")
    ClassificacaoRisco risco;

    @ManyToMany
    @JoinTable(
            name = "membros",
            schema = "gerenciador",
            joinColumns = @JoinColumn(name = "id_projeto"),
            inverseJoinColumns = @JoinColumn(name = "id_pessoa"))
    List<Pessoa> membros;

    public Projeto() {
    }

    public Projeto(Pessoa gerente, String nome, LocalDate dataInicio, LocalDate dataPrevisaoFim, LocalDate dataFim, String descricao, String status, double orcamento, ClassificacaoRisco risco, List<Pessoa> membros) {
        this.gerente = gerente;
        this.nome = nome;
        this.dataInicio = dataInicio;
        this.dataPrevisaoFim = dataPrevisaoFim;
        this.dataFim = dataFim;
        this.descricao = descricao;
        this.status = status;
        this.orcamento = orcamento;
        this.risco = risco;
        this.membros = membros;
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

    public LocalDate getDataPrevisaoFim() {
        return dataPrevisaoFim;
    }

    public void setDataPrevisaoFim(LocalDate dataPrevisaoFim) {
        this.dataPrevisaoFim = dataPrevisaoFim;
    }

    public void addMember(Pessoa pessoa){
        if(membros == null){
            membros = new ArrayList<>();
        }
        membros.add(pessoa);
    }
}
