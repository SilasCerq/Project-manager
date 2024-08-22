package com.project.demo.service;

import com.project.demo.model.Pessoa;

import java.util.List;

public interface PessoaService {

    List<Pessoa> findAll();
    Pessoa createPessoa(Pessoa pessoa);
    Pessoa findById(Long id);
}
