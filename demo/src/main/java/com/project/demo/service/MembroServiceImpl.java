package com.project.demo.service;

import com.project.demo.model.Pessoa;
import com.project.demo.model.Projeto;
import com.project.demo.repository.PessoaRepository;
import com.project.demo.repository.ProjetoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MembroServiceImpl implements MembroService{

    PessoaRepository pessoaRepository;
    ProjetoRepository projetoRepository;

    @Autowired
    public MembroServiceImpl(PessoaRepository pessoaRepository, ProjetoRepository projetoRepository) {
        this.pessoaRepository = pessoaRepository;
        this.projetoRepository = projetoRepository;
    }

    @Override
    public void createMembro(Pessoa pessoa, Projeto projeto) {
        if(pessoa.getFuncionario()) {
            projeto.addMember(pessoa);
            pessoa.addProjetos(projeto);
            this.pessoaRepository.save(pessoa);
            this.projetoRepository.save(projeto);
        }
    }
}
