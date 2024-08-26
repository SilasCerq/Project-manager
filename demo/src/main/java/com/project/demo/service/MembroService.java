package com.project.demo.service;

import com.project.demo.model.Pessoa;
import com.project.demo.model.Projeto;

import java.util.List;

public interface MembroService {

        void createMembro(Pessoa pessoa, Projeto projeto);
        List<Pessoa> getMembros(Long projectId);
}
