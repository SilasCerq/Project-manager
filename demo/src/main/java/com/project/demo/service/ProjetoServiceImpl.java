package com.project.demo.service;

import com.project.demo.model.Status;
import com.project.demo.model.Projeto;
import com.project.demo.repository.ProjetoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjetoServiceImpl implements ProjetoService{

    ProjetoRepository projetoRepository;

    @Autowired
    public ProjetoServiceImpl(ProjetoRepository projetoRepository){
        this.projetoRepository = projetoRepository;
    }


    @Override
    public List<Projeto> findAll() {
        return projetoRepository.findAll();
    }

    @Override
    public Projeto findById(Long id) {
        Optional<Projeto> result = projetoRepository.findById(id);
        Projeto projeto = null;
        if(result.isPresent()){
            projeto = result.get();
        } else {throw new RuntimeException("Nao existe nao amigo");}
        return projeto;
    }

    @Override
    public Projeto createProjeto(Projeto projeto) {
        return projetoRepository.save(projeto);
    }

    @Override
    public Projeto updateProjeto(Projeto projeto) {
        return projetoRepository.save(projeto);
    }

    @Override
    public void deleteProjeto(Long id) {
        Projeto projeto = findById(id);
        Status status = Status.valueOf(projeto.getStatus());
        if (status == Status.INICIADO || status == Status.ANDAMENTO || status == Status.ENCERRADO){
        throw new RuntimeException("");
        } else {
            projetoRepository.delete(projeto);
    }
    }
}
