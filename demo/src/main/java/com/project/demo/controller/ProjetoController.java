package com.project.demo.controller;

import com.project.demo.model.Projeto;
import com.project.demo.service.ProjetoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
@RequestMapping("/api")
public class ProjetoController {

    ProjetoService projetoService;

    @Autowired
    public ProjetoController(ProjetoService projetoService){
        this.projetoService = projetoService;
    }

    @GetMapping("/projetos")
    public List<Projeto> GetAllProjeto(Model model){
        List<Projeto> projetos = projetoService.findAll();
        model.addAttribute("projetos", projetos);
        return null;
    }

    @PostMapping("/projeto")
    public Projeto createProjeto(Projeto projeto){
        return projetoService.createProjeto(projeto);
    }

}
