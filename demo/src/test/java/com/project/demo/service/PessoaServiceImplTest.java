package com.project.demo.service;

import com.project.demo.exceptions.PessoaNotFoundException;
import com.project.demo.model.Pessoa;
import com.project.demo.repository.PessoaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PessoaServiceImplTest {

    @InjectMocks
    private PessoaServiceImpl pessoaService;

    @Mock
    private PessoaRepository pessoaRepository;

    @Test
    void findAllShouldReturnListOfPessoas() {
        Pessoa pessoa1 = new Pessoa();
        Pessoa pessoa2 = new Pessoa();
        when(pessoaRepository.findAll()).thenReturn(Arrays.asList(pessoa1, pessoa2));

        var pessoas = pessoaService.findAll();
        assertEquals(2, pessoas.size());
        verify(pessoaRepository, times(1)).findAll();
    }

    @Test
    void findByIdShouldReturnPessoaWhenFound() {
        Pessoa pessoa = new Pessoa();
        pessoa.setId(1L);
        when(pessoaRepository.findById(1L)).thenReturn(Optional.of(pessoa));

        var foundPessoa = pessoaService.findById(1L);
        assertNotNull(foundPessoa);
        assertEquals(1L, foundPessoa.getId());
        verify(pessoaRepository, times(1)).findById(1L);
    }

    @Test
    void findByIdShouldThrowExceptionWhenNotFound() {
        when(pessoaRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(PessoaNotFoundException.class, () -> pessoaService.findById(1L));
        verify(pessoaRepository, times(1)).findById(1L);
    }

    @Test
    void createShouldReturnSavedPessoa() {
        Pessoa pessoa = new Pessoa();
        when(pessoaRepository.save(pessoa)).thenReturn(pessoa);

        var savedPessoa = pessoaService.create(pessoa);
        assertNotNull(savedPessoa);
        verify(pessoaRepository, times(1)).save(pessoa);
    }

    @Test
    void deleteShouldRemovePessoa() {
        Pessoa pessoa = new Pessoa();
        pessoa.setId(1L);
        when(pessoaRepository.findById(1L)).thenReturn(Optional.of(pessoa));

        pessoaService.delete(1L);
        verify(pessoaRepository, times(1)).findById(1L);
        verify(pessoaRepository, times(1)).delete(pessoa);
    }
}
