package com.project.demo.service;

import com.project.demo.model.Pessoa;

import java.util.List;

public interface PessoaService {

    void delete(Long id);
    List<Pessoa> findAll();
    Pessoa create(Pessoa pessoa);
    Pessoa findById(Long id);
}
