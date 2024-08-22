package com.project.demo.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="pessoa", schema = "gerenciador")
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @Column(name = "nome")
    String nome;

    @Column(name = "data_nascimento")
    LocalDate dataNascimento;

    @Column(name = "cpf")
    String cpf;

    @Column(name = "funcionario")
    Boolean funcionario;

    @Column(name = "gerente")
    Boolean gerente;


    @ManyToMany(mappedBy = "membros")
    List<Projeto> projetos;

    public Pessoa() {

    }

    public Pessoa(String nome, LocalDate dataNascimento, String cpf, Boolean funcionario, Boolean gerente) {
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.cpf = cpf;
        this.funcionario = funcionario;
        this.gerente = gerente;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return nome;
    }

    public void setName(String name) {
        this.nome = name;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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

    public List<Projeto> getProjetos() {
        return projetos;
    }

    public void setProjetos(List<Projeto> projetos) {
        this.projetos = projetos;
    }

    public void addProjetos(Projeto projeto){
        if(projetos == null){
            projetos = new ArrayList<>();
        }
        projetos.add(projeto);
    }
}
