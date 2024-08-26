package com.project.demo.service;

import com.project.demo.exceptions.CannotDeleteProjectException;
import com.project.demo.exceptions.ProjetoNotFoundException;
import com.project.demo.model.Projeto;
import com.project.demo.model.Status;
import com.project.demo.repository.ProjetoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProjetoServiceImplTest {

    @InjectMocks
    ProjetoServiceImpl projetoService;

    @Mock
    ProjetoRepository projetoRepository;

    @Test
    void createShouldReturnUser() {
        var projeto = new Projeto();
        projeto.setNome("Novo Projeto");
        projeto.setDataInicio(LocalDate.now());
        projeto.setDataFim(LocalDate.now().plusDays(30));

        when(projetoRepository.save(projeto)).thenReturn(projeto);

        Projeto result = projetoService.create(projeto);

        verify(projetoRepository).save(projeto);
        assertEquals(projeto, result);

    }

    @Test
    void findAllShouldReturnListOfProjetos() {
        Projeto projeto1 = new Projeto();
        Projeto projeto2 = new Projeto();
        when(projetoRepository.findAll()).thenReturn(Arrays.asList(projeto1, projeto2));

        var projetos = projetoService.findAll();
        assertEquals(2, projetos.size());
        verify(projetoRepository, times(1)).findAll();
    }

    @Test
    void findByIdShouldReturnProjetoWhenFound() {
        Projeto projeto = new Projeto();
        projeto.setId(1L);
        when(projetoRepository.findById(1L)).thenReturn(Optional.of(projeto));

        var foundProjeto = projetoService.findById(1L);
        assertNotNull(foundProjeto);
        assertEquals(1L, foundProjeto.getId());
        verify(projetoRepository, times(1)).findById(1L);
    }

    @Test
    void findByIdShouldThrowExceptionWhenNotFound() {
        when(projetoRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ProjetoNotFoundException.class, () -> projetoService.findById(1L));
        verify(projetoRepository, times(1)).findById(1L);
    }

    @Test
    void createShouldReturnSavedProjeto() {
        Projeto projeto = new Projeto();
        when(projetoRepository.save(projeto)).thenReturn(projeto);

        var savedProjeto = projetoService.create(projeto);
        assertNotNull(savedProjeto);
        verify(projetoRepository, times(1)).save(projeto);
    }

    @Test
    void updateShouldReturnUpdatedProjeto() {
        Projeto existingProjeto = new Projeto();
        existingProjeto.setId(1L);
        Projeto updatedProjeto = new Projeto();
        updatedProjeto.setId(1L);
        updatedProjeto.setNome("Updated Name");

        when(projetoRepository.findById(1L)).thenReturn(Optional.of(existingProjeto));
        when(projetoRepository.save(existingProjeto)).thenReturn(updatedProjeto);

        var result = projetoService.update(updatedProjeto);
        assertEquals("Updated Name", result.getNome());
        verify(projetoRepository, times(1)).findById(1L);
        verify(projetoRepository, times(1)).save(existingProjeto);
    }

    @Test
    void deleteShouldThrowExceptionIfStatusNotAllowingDeletion() {
        Projeto projeto = new Projeto();
        projeto.setId(1L);
        projeto.setStatus(Status.INICIADO.name());

        when(projetoRepository.findById(1L)).thenReturn(Optional.of(projeto));

        assertThrows(CannotDeleteProjectException.class, () -> projetoService.delete(1L));
        verify(projetoRepository, never()).delete(projeto);
    }

    @Test
    void deleteShouldDeleteProjetoIfStatusAllows() {
        Projeto projeto = new Projeto();
        projeto.setId(1L);
        projeto.setStatus(Status.CANCELADO.name());

        when(projetoRepository.findById(1L)).thenReturn(Optional.of(projeto));

        projetoService.delete(1L);
        verify(projetoRepository, times(1)).delete(projeto);
    }
}
