package br.com.sebook.services;

import br.com.sebook.entities.Categoria;
import br.com.sebook.repositories.CategoriaRepository;
import br.com.sebook.services.exceptions.ResourceNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class CategoriaServiceTest {
    @InjectMocks
    private CategoriaService service;
    @Mock
    private CategoriaRepository repository;
    private Categoria categoria;
    private List<Categoria> categoriaLista = new ArrayList<>();
    private Categoria categoria2;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        categoriaStart();
    }

    @Test
    void BuscarTodosRetornaListaDeCategorias() {
        categoriaLista.add(categoria);
        categoriaLista.add(categoria2);

        when(repository.findAll()).thenReturn(categoriaLista);

        List<Categoria> listaEsperada = service.findAll();

        assertNotNull(listaEsperada);
        assertEquals(categoriaLista, listaEsperada);
        assertEquals(2, listaEsperada.size());
        assertEquals(Categoria.class, listaEsperada.get(0).getClass());
        assertEquals(categoria.getId(), listaEsperada.get(0).getId());
        assertEquals(categoria.getNome(), listaEsperada.get(0).getNome());
    }

    @Test
    void BuscarPorNomeRetornaCategoria() {
        when(repository.findByNome(anyString())).thenReturn(categoria);

        Categoria resposta = service.findByNome("Romance");

        assertNotNull(resposta);
        assertEquals(categoria.getClass(), resposta.getClass());
        assertEquals("Romance", resposta.getNome());
        assertEquals(categoria, resposta);
    }

    @Test
    void BuscarPorIdInexistenteRetornarObjectNotFoundException() {
        when(repository.findByNome(anyString())).thenThrow(new ResourceNotFoundException("Categoria não encontrada."));
        try {
            service.findByNome("Ação");
        }
        catch (Exception e) {
            assertEquals(ResourceNotFoundException.class, e.getClass());
            assertEquals("Categoria não encontrada.", e.getMessage());
        }
    }

    private void categoriaStart() {
        categoria = new Categoria(1L, "Romance");
        categoria2 = new Categoria(1L, "Suspense");
    }
}