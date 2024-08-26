package com.project.demo.controller;

import com.project.demo.exceptions.PessoaNotFoundException;
import com.project.demo.exceptions.ProjetoNotFoundException;
import com.project.demo.model.Pessoa;
import com.project.demo.model.Projeto;
import com.project.demo.model.Status;
import com.project.demo.service.PessoaService;
import com.project.demo.service.ProjetoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class ProjetoController {

    ProjetoService projetoService;
    PessoaService pessoaService;

    @Autowired
    public ProjetoController(ProjetoService projetoService, PessoaService pessoaService){
        this.projetoService = projetoService;
        this.pessoaService = pessoaService;
    }

    @GetMapping("/projetos")
    public String GetAllProjeto(Model model){
        List<Projeto> projetos = projetoService.findAll();
        model.addAttribute("projetos", projetos);

        return "projetos";
    }

    @PostMapping("/projeto")
    public String createProjeto(Projeto projeto, RedirectAttributes ra){
        if(projeto == null || projeto.getGerente() == null || projeto.getGerente().getId() == null) {
            ra.addFlashAttribute("message", "Campos não foram preenchidos corretamente");
            ra.addFlashAttribute("alertType", "danger");
        } else {
            try {
                Pessoa gerente = pessoaService.findById(projeto.getGerente().getId());
                projeto.setGerente(gerente);
                projetoService.create(projeto);
                ra.addFlashAttribute("message", "Projeto salvo com sucesso");
                ra.addFlashAttribute("alertType", "success");
            } catch (PessoaNotFoundException e) {
                ra.addFlashAttribute("message", "Gerente não encontrado");
                ra.addFlashAttribute("alertType", "danger");
            }
        }
        return "redirect:projetos";
    }

    @PostMapping("/projeto/update")
    public String updateProjeto(Projeto projeto, RedirectAttributes ra){
        if(projeto == null){
            ra.addFlashAttribute("message", "Campos não foram preenchidos corretamente");
        } else {
            projetoService.update(projeto);
            ra.addFlashAttribute("message", "Projeto salvo");
            ra.addFlashAttribute("alertType", "success");
        }
        return "redirect:/projetos";
    }

    @GetMapping("/projeto/new")
    public String newProjetoForm(Model model) {
        model.addAttribute("projeto", new Projeto());
        model.addAttribute("pageTitle", "Adicionar novo projeto");
        model.addAttribute("formAction", "/projeto");
        model.addAttribute("statusOptions", Status.values());
        return "projeto_form";
    }

    @GetMapping("/projeto/edit/{id}")
    public String editProjetoForm(@PathVariable Long id, Model model, RedirectAttributes ra) {
        try {
            Projeto projeto = projetoService.findById(id);
            model.addAttribute("projeto", projeto);
            model.addAttribute("pageTitle", "Editar projeto (ID:" + id + ")");
            model.addAttribute("formAction", "/projeto/update");
            model.addAttribute("statusOptions", Status.values());
            return "projeto_form";
        } catch (ProjetoNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
            ra.addFlashAttribute("alertType", "danger");
            return "redirect:/projetos";
        }
    }

    @GetMapping("/projeto/delete/{id}")
    public String deleteProjeto(@PathVariable Long id, RedirectAttributes ra) {
        try {
            projetoService.delete(id);
        } catch (ProjetoNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
            ra.addFlashAttribute("alertType", "danger");
        }
        return "redirect:/projetos";
    }


}
