package br.com.sebook.services;

import br.com.sebook.entities.Categoria;
import br.com.sebook.entities.DonoSebo;
import br.com.sebook.repositories.DonoSeboRepository;
import br.com.sebook.services.exceptions.ResourceNotFoundException;
import org.hibernate.ObjectNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DonoSeboServiceTest {
    @InjectMocks
    private DonoSeboService service;
    @Mock
    private DonoSeboRepository repository;

    private DonoSebo donoSebo;
    private DonoSebo donoSebo2;
    private DonoSebo donoSeboAtualizado;
    private List<DonoSebo> listaDonoSebo = new ArrayList<>();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        donoSeboStart();
    }

    @Test
    void buscarTodosRetornaListaDeDonosSebo() {
        listaDonoSebo.add(donoSebo);
        listaDonoSebo.add(donoSebo2);
        when(repository.findAll()).thenReturn(listaDonoSebo);

        List<DonoSebo> listaEsperada = service.findAll();

        assertNotNull(listaEsperada);
        assertEquals(listaDonoSebo, listaEsperada);
        assertEquals(2, listaEsperada.size());
        assertEquals(DonoSebo.class, listaEsperada.get(0).getClass());
        assertEquals(donoSebo.getId(), listaEsperada.get(0).getId());
        assertEquals(donoSebo.getNome(), listaEsperada.get(0).getNome());
        assertEquals(donoSebo.getCpf(), listaEsperada.get(0).getCpf());
        assertEquals(donoSebo.getEmail(), listaEsperada.get(0).getEmail());
        assertEquals(donoSebo.getSenha(), listaEsperada.get(0).getSenha());
    }

    @Test
    void buscarPorIdIdExistenteRetornaDonoSebo() {
        when(repository.findById(1L)).thenReturn(Optional.of(donoSebo));

        DonoSebo donoSeboAtual = service.findById(1L);

        assertEquals(donoSebo, donoSeboAtual);
    }

    @Test
    void buscarPorIdIdNaoExistenteLancarResourceNotFoundException() {
        when(repository.findById(anyLong())).thenThrow(new ResourceNotFoundException("Dono de sebo não encontrado."));
        try {
            service.findById(1L);
        }
        catch (Exception e) {
            assertEquals(ResourceNotFoundException.class, e.getClass());
            assertEquals("Dono de sebo não encontrado.", e.getMessage());
        }
    }

    @Test
    void inserirRetornaDonoSeboInserido() {
        when(repository.save(Mockito.any(DonoSebo.class))).thenReturn(donoSebo);

        DonoSebo donoSeboAtual = service.insert(donoSebo);

        assertEquals(donoSebo, donoSeboAtual);
    }

    @Test
    void deletarIdExistenteDeletaDonoSebo() {
        service.delete(1L);

        verify(repository, times(1)).deleteById(1L);
    }

    @Test
    void atualizarIdExistenteRetornaDonoSeboAtualizado() {
        when(repository.findById(1L)).thenReturn(Optional.of(donoSebo));
        when(repository.save(Mockito.any(DonoSebo.class))).thenReturn(donoSeboAtualizado);

        DonoSebo donoSeboAtual = service.update(1L, donoSeboAtualizado);

        assertEquals(donoSeboAtualizado, donoSeboAtual);
    }

    private void donoSeboStart() {
        donoSebo = new DonoSebo(1L, "João Silva", "123456789", "joao@gmail.com", "senha123");
        donoSebo2 = new DonoSebo(2L, "Maria Santos", "987654321", "maria@gmail.com", "senha456");
        donoSeboAtualizado = new DonoSebo(1L, "João da Silva", "987654321", "joao@gmail.com", "novasenha");
    }
}