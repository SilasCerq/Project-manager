package com.project.demo.service;

import com.project.demo.exceptions.AcessDeniedException;
import com.project.demo.exceptions.PessoaNotFoundException;
import com.project.demo.exceptions.ProjetoNotFoundException;
import com.project.demo.model.Pessoa;
import com.project.demo.model.Projeto;
import com.project.demo.repository.PessoaRepository;
import com.project.demo.repository.ProjetoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    public List<Pessoa> getMembros(Long projectId){
        Optional<Projeto> result = projetoRepository.findById(projectId);
        Projeto projeto = null;
        if(result.isPresent()){
            projeto = result.get();
        } else { throw new ProjetoNotFoundException();}
        return projeto.getMembros();
    }

    @Override
    public void createMembro(Pessoa pessoa, Projeto projeto) {
        if(pessoa.getFuncionario()) {
            projeto.addMember(pessoa);
            pessoa.addProjetos(projeto);
            this.pessoaRepository.save(pessoa);
            this.projetoRepository.save(projeto);
        } else{ throw new AcessDeniedException();}
    }
}
