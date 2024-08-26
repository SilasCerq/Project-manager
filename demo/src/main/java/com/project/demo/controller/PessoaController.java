    package com.project.demo.controller;

    import com.project.demo.exceptions.PessoaCannotBeDeletedException;
    import com.project.demo.exceptions.PessoaNotFoundException;
    import com.project.demo.exceptions.ProjetoNotFoundException;
    import com.project.demo.model.Pessoa;
    import com.project.demo.model.Projeto;
    import com.project.demo.service.PessoaService;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Controller;
    import org.springframework.ui.Model;
    import org.springframework.web.bind.annotation.*;
    import org.springframework.web.servlet.mvc.support.RedirectAttributes;

    import java.util.List;

    @Controller
    public class PessoaController {

        PessoaService pessoaService;

        @Autowired
        public PessoaController(PessoaService pessoaService) {
            this.pessoaService = pessoaService;
        }

        @PostMapping("/pessoa")
        public String createPessoa(Pessoa pessoa, RedirectAttributes ra){
            if(pessoa == null){
                ra.addFlashAttribute("message", "Campos não foram preenchidos corretamente");
                ra.addFlashAttribute("alertType", "danger");
            } else {
                pessoaService.create(pessoa);
                ra.addFlashAttribute("message", "Pessoa salva");
                ra.addFlashAttribute("alertType", "success");}
            return "redirect:pessoas";
        }

        @GetMapping("/pessoas")
        public String getPessoas(Model model){
            List<Pessoa> pessoas = pessoaService.findAll();
            model.addAttribute("pessoas", pessoas);
            return "pessoas";
        }

        @GetMapping("/pessoa/new")
        public String newPessoaForm(Model model) {
            model.addAttribute("pessoa", new Pessoa());
            model.addAttribute("pageTitle", "Adicionar nova pessoa");
            return "pessoa_form";
        }

        @GetMapping("/pessoa/delete/{id}")
        public String deletePessoa(@PathVariable Long id, RedirectAttributes ra) {
            try {
                pessoaService.delete(id);
                ra.addFlashAttribute("message", "Pessoa excluída com sucesso.");
                ra.addFlashAttribute("alertType", "success");
            } catch (PessoaCannotBeDeletedException e) { // Captura a exceção personalizada
                ra.addFlashAttribute("message", e.getMessage());
                ra.addFlashAttribute("alertType", "danger");
            } catch (PessoaNotFoundException e) {
                ra.addFlashAttribute("message", e.getMessage());
                ra.addFlashAttribute("alertType", "danger");
            }
            return "redirect:/pessoas";
        }

    }
