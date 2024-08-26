package com.project.demo.repository;

import com.project.demo.model.Pessoa;
import com.project.demo.model.Projeto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
}
