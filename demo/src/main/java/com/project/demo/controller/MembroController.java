package com.project.demo.controller;

import com.project.demo.exceptions.PessoaNotFoundException;
import com.project.demo.model.Pessoa;
import com.project.demo.model.Projeto;
import com.project.demo.service.MembroService;
import com.project.demo.service.PessoaService;
import com.project.demo.service.ProjetoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/projeto")
public class MembroController {


    MembroService membroService;
    PessoaService pessoaService;
    ProjetoService projetoService;

    @Autowired
    public MembroController(MembroService membroService, ProjetoService projetoService, PessoaService pessoaService) {
        this.membroService = membroService;
        this.projetoService = projetoService;
        this.pessoaService = pessoaService;
    }


    @GetMapping("/{id}/membros")
    public String getMembros(@PathVariable("id") Long id, Model model) {
        List<Pessoa> membros = membroService.getMembros(id);
        Projeto projeto = projetoService.findById(id);
        model.addAttribute("projeto", projeto);
        model.addAttribute("membros", membros);
        model.addAttribute("pageTitle", "Membros do Projeto (ID:"+id+")");
        return "membros";
    }

    @PostMapping("/{projetoId}/membro")
    public String createMembro(@PathVariable Long projetoId, @RequestParam Long pessoaId, RedirectAttributes ra){
        try {
            Projeto projeto = projetoService.findById(projetoId);
            Pessoa pessoa = pessoaService.findById(pessoaId);
            membroService.createMembro(pessoa, projeto);
            return "redirect:/projeto/" + projetoId + "/membros";
        } catch(PessoaNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
            ra.addFlashAttribute("alertType", "danger");
        }
        return "redirect:/projeto/" + projetoId + "/membros";
    }
}