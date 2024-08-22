package com.project.demo.service;

import com.project.demo.model.Pessoa;
import com.project.demo.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PessoaServiceImpl implements PessoaService{

    PessoaRepository pessoaRepository;

    @Autowired
    public PessoaServiceImpl(PessoaRepository pessoaRepository) {
        this.pessoaRepository = pessoaRepository;
    }

    @Override
    public List<Pessoa> findAll() {
        return pessoaRepository.findAll();
    }

    @Override
    public Pessoa createPessoa(Pessoa pessoa) {
        return pessoaRepository.save(pessoa);
    }

    @Override
    public Pessoa findById(Long id) {
        Optional<Pessoa> result = pessoaRepository.findById(id);
        Pessoa pessoa = null;
        if(result.isPresent()){
            pessoa = result.get();
        }

        return pessoa;
    }
}
