package com.project.demo.service;

import com.project.demo.exceptions.AcessDeniedException;
import com.project.demo.exceptions.ProjetoNotFoundException;
import com.project.demo.model.Pessoa;
import com.project.demo.model.Projeto;
import com.project.demo.repository.PessoaRepository;
import com.project.demo.repository.ProjetoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MembroServiceImplTest {

    @InjectMocks
    private MembroServiceImpl membroService;

    @Mock
    private PessoaRepository pessoaRepository;

    @Mock
    private ProjetoRepository projetoRepository;

    @Test
    void getMembrosShouldReturnListOfMembers() {
        Projeto projeto = new Projeto();
        Pessoa membro1 = new Pessoa();
        Pessoa membro2 = new Pessoa();
        projeto.setMembros(Arrays.asList(membro1, membro2));
        when(projetoRepository.findById(1L)).thenReturn(Optional.of(projeto));

        List<Pessoa> membros = membroService.getMembros(1L);
        assertEquals(2, membros.size());
        assertTrue(membros.contains(membro1));
        assertTrue(membros.contains(membro2));
        verify(projetoRepository, times(1)).findById(1L);
    }

    @Test
    void getMembrosShouldThrowExceptionWhenProjetoNotFound() {
        when(projetoRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ProjetoNotFoundException.class, () -> membroService.getMembros(1L));
        verify(projetoRepository, times(1)).findById(1L);
    }

    @Test
    void createMembroShouldAddMemberToProjeto() {
        Pessoa pessoa = new Pessoa();
        Projeto projeto = new Projeto();
        pessoa.setFuncionario(true);
        when(pessoaRepository.save(pessoa)).thenReturn(pessoa);
        when(projetoRepository.save(projeto)).thenReturn(projeto);

        membroService.createMembro(pessoa, projeto);

        assertTrue(projeto.getMembros().contains(pessoa));
        assertTrue(pessoa.getProjetos().contains(projeto));
        verify(pessoaRepository, times(1)).save(pessoa);
        verify(projetoRepository, times(1)).save(projeto);
    }

    @Test
    void createMembroShouldThrowExceptionWhenPessoaIsNotFuncionario() {
        Pessoa pessoa = new Pessoa();
        Projeto projeto = new Projeto();
        pessoa.setFuncionario(false);

        assertThrows(AcessDeniedException.class, () -> membroService.createMembro(pessoa, projeto));
        verify(pessoaRepository, never()).save(pessoa);
        verify(projetoRepository, never()).save(projeto);
    }
}
