package com.project.demo.service;

import com.project.demo.exceptions.PessoaCannotBeDeletedException;
import com.project.demo.exceptions.PessoaNotFoundException;
import com.project.demo.model.Pessoa;
import com.project.demo.repository.PessoaRepository;
import com.project.demo.repository.ProjetoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PessoaServiceImpl implements PessoaService{

    PessoaRepository pessoaRepository;
    ProjetoRepository projetoRepository;
    @Autowired
    public PessoaServiceImpl(PessoaRepository pessoaRepository, ProjetoRepository projetoRepository) {
        this.pessoaRepository = pessoaRepository;
        this.projetoRepository = projetoRepository;
    }

    @Override
    public void delete(Long id) {
        Pessoa pessoa = findById(id);

        if (!pessoa.getProjetos().isEmpty()) {
            throw new PessoaCannotBeDeletedException();
        }

        pessoaRepository.delete(pessoa);
    }


    @Override
    public List<Pessoa> findAll() {
        return pessoaRepository.findAll();
    }

    @Override
    public Pessoa create(Pessoa pessoa) {
        return pessoaRepository.save(pessoa);
    }

    @Override
    public Pessoa findById(Long id) {
        Optional<Pessoa> result = pessoaRepository.findById(id);
        Pessoa pessoa = null;
        if(result.isPresent()){
            pessoa = result.get();
        } else {
            throw new PessoaNotFoundException();
        }

        return pessoa;
    }


}
