package com.project.demo.service;

import com.project.demo.exceptions.CannotDeleteProjectException;
import com.project.demo.exceptions.ProjetoNotFoundException;
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
        } else {throw new ProjetoNotFoundException();}
        return projeto;
    }

    @Override
    public Projeto create(Projeto projeto) {
        return projetoRepository.save(projeto);
    }

    @Override
    public Projeto update(Projeto projeto) {
        Projeto existingProjeto = findById(projeto.getId());
        existingProjeto.setNome(projeto.getNome());
        existingProjeto.setDataInicio(projeto.getDataInicio());
        existingProjeto.setDataPrevisaoFim(projeto.getDataPrevisaoFim());
        existingProjeto.setDataFim(projeto.getDataFim());
        existingProjeto.setDescricao(projeto.getDescricao());
        existingProjeto.setStatus(projeto.getStatus());
        existingProjeto.setOrcamento(projeto.getOrcamento());
        existingProjeto.setRisco(projeto.getRisco());
        existingProjeto.setGerente(projeto.getGerente());
        return projetoRepository.save(existingProjeto);
    }

    @Override
    public void delete(Long id) {
        Projeto projeto = findById(id);
        Status status = Status.valueOf(projeto.getStatus());
        if (status == Status.INICIADO || status == Status.ANDAMENTO || status == Status.ENCERRADO){
        throw new CannotDeleteProjectException();
        } else {
            projetoRepository.delete(projeto);
    }
    }
}
