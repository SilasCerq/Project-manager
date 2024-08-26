package com.project.demo.service;

import com.project.demo.model.Projeto;

import java.util.List;

public interface ProjetoService {

    List<Projeto> findAll();
    Projeto findById(Long id);
    Projeto create(Projeto projeto);
    Projeto update(Projeto projeto);
    void delete(Long id);

}
