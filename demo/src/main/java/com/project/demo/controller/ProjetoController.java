package com.project.demo.controller;

import com.project.demo.model.Projeto;
import com.project.demo.service.ProjetoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
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
        return  projetoService.findAll();
    }

    @PostMapping("/projeto")
    public Projeto createProjeto(@RequestBody Projeto projeto){
        return projetoService.createProjeto(projeto);
    }

}
