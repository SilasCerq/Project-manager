package com.project.demo.service;

import com.project.demo.model.Projeto;

import java.util.List;

public interface ProjetoService {

    List<Projeto> findAll();
    Projeto findById(Long id);
    Projeto createProjeto(Projeto projeto);
    Projeto updateProjeto(Projeto projeto);
    void deleteProjeto(Long id);

}
