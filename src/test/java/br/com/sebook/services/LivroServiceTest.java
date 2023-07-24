package br.com.sebook.services;

import br.com.sebook.entities.Categoria;
import br.com.sebook.entities.Livro;
import br.com.sebook.repositories.LivroRepository;
import br.com.sebook.services.exceptions.ResourceNotFoundException;
import org.junit.jupiter.api.Assertions;
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

class LivroServiceTest {
    @InjectMocks
    private LivroService service;
    @Mock
    private LivroRepository repository;

    private Livro livro;
    private Livro livro2;
    private Livro livroAtualizado;
    private List<Livro> livros = new ArrayList<>();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        livroStart();
    }

    @Test
    void buscarTodosRetornaListaDeLivros() {
        livros.add(livro);
        livros.add(livro2);
        when(repository.findAll()).thenReturn(livros);

        List<Livro> listaEsperada = service.findAll();

        assertNotNull(listaEsperada);
        assertEquals(livros, listaEsperada);
        assertEquals(2, listaEsperada.size());
        assertEquals(Livro.class, listaEsperada.get(0).getClass());
        assertEquals(livro.getId(), listaEsperada.get(0).getId());
        assertEquals(livro.getTitulo(), listaEsperada.get(0).getTitulo());
        assertEquals(livro.getAutor(), listaEsperada.get(0).getAutor());
        assertEquals(livro.getEditora(), listaEsperada.get(0).getEditora());
        assertEquals(livro.getNomeCategoria(), listaEsperada.get(0).getNomeCategoria());
        assertEquals(livro.getImgUrl(), listaEsperada.get(0).getImgUrl());
        assertEquals(livro.getPreco(), listaEsperada.get(0).getPreco());
    }

    @Test
    void buscarPorIdIdExistenteRetornaLivro() {
        when(repository.findById(1L)).thenReturn(Optional.of(livro));

        Livro livroEsperado = service.findById(1L);

        assertEquals(livro, livroEsperado);
    }

    @Test
    void buscarPorIdIdNaoExistenteLancaResourceNotFoundException() {
        when(repository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> service.findById(1L));
    }

    @Test
    void buscarPorTituloExistenteRetornaListaDeLivros() {
        when(repository.findByTituloContaining("Livro")).thenReturn(livros);

        List<Livro> listaEsperada = service.findByTitulo("Livro");

        assertEquals(livros, listaEsperada);
    }

    @Test
    void inserirRetornaLivroInserido() {
        when(repository.save(any(Livro.class))).thenReturn(livro);

        Livro livroEsperado = service.insert(livro);

        assertNotNull(livroEsperado);
        assertEquals(livro, livroEsperado);
        assertEquals(livro.getClass(), livroEsperado.getClass());
        assertEquals(livro.getId(), livroEsperado.getId());
        assertEquals(livro.getTitulo(), livroEsperado.getTitulo());
        assertEquals(livro.getAutor(), livroEsperado.getAutor());
        assertEquals(livro.getEditora(), livroEsperado.getEditora());
        assertEquals(livro.getNomeCategoria(), livroEsperado.getNomeCategoria());
        assertEquals(livro.getImgUrl(), livroEsperado.getImgUrl());
        assertEquals(livro.getPreco(), livroEsperado.getPreco());
    }

    @Test
    void deletarIdExistenteDeletaLivro() {
        service.delete(1L);

        verify(repository, times(1)).deleteById(1L);
    }

    @Test
    void atualizarIdExistenteRetornaLivroAtualizado() {
        when(repository.findById(1L)).thenReturn(Optional.of(livro));
        when(repository.save(any(Livro.class))).thenReturn(livroAtualizado);

        Livro livroEsperado = service.update(1L, livroAtualizado);

        assertEquals(livroAtualizado, livroEsperado);
    }

    @Test
    void atualizarIdNaoExistenteLancaResourceNotFoundException() {
        when(repository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> service.update(1L, new Livro()));
    }

    private void livroStart() {
        livro = new Livro(1L, "Livro 1", "Editora 1", "Autor 1", 10.0, "", "Categoria 1");
        livro2 = new Livro(2L, "Livro 2", "Editora 2", "Autor 2", 20.0, "", "Categoria 2");
        livroAtualizado = new Livro(1L, "Livro Atualizado", "Editora Atualizada", "Autor Atualizado", 20.0, "", "Categoria Atualizada");
    }
}
