package br.com.sebook.services;

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

class LivroServiceTest {
    @InjectMocks
    private LivroService service;
    @Mock
    private LivroRepository repository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void buscarTodos_RetornaListaDeLivros() {
        // Mockando o comportamento do repositório
        List<Livro> listaEsperada = new ArrayList<>();
        listaEsperada.add(new Livro(1L, "Livro 1", "Editora 1", "Autor 1", 10.0, "", "Categoria 1"));
        listaEsperada.add(new Livro(2L, "Livro 2", "Editora 2", "Autor 2", 20.0, "", "Categoria 2"));
        Mockito.when(repository.findAll()).thenReturn(listaEsperada);

        // Chamando o método do serviço
        List<Livro> listaAtual = service.findAll();

        // Verificando o resultado
        Assertions.assertEquals(listaEsperada, listaAtual);
    }

    @Test
    void buscarPorId_IdExistente_RetornaLivro() {
        // Mockando o comportamento do repositório
        Livro livroEsperado = new Livro(1L, "Livro 1", "Editora 1", "Autor 1", 10.0, "", "Categoria 1");
        Mockito.when(repository.findById(1L)).thenReturn(Optional.of(livroEsperado));

        // Chamando o método do serviço
        Livro livroAtual = service.findById(1L);

        // Verificando o resultado
        Assertions.assertEquals(livroEsperado, livroAtual);
    }

    @Test
    void buscarPorId_IdNaoExistente_LancaResourceNotFoundException() {
        // Mockando o comportamento do repositório
        Mockito.when(repository.findById(1L)).thenReturn(Optional.empty());

        // Chamando o método do serviço e verificando a exceção
        Assertions.assertThrows(ResourceNotFoundException.class, () -> service.findById(1L));
    }

    @Test
    void buscarPorTitulo_TituloExistente_RetornaListaDeLivros() {
        // Mockando o comportamento do repositório
        List<Livro> listaEsperada = new ArrayList<>();
        listaEsperada.add(new Livro(1L, "Livro 1", "Editora 1", "Autor 1", 10.0, "", "Categoria 1"));
        listaEsperada.add(new Livro(2L, "Livro 2", "Editora 2", "Autor 2", 20.0, "", "Categoria 2"));
        Mockito.when(repository.findByTituloContaining("Livro")).thenReturn(listaEsperada);

        // Chamando o método do serviço
        List<Livro> listaAtual = service.findByTitulo("Livro");

        // Verificando o resultado
        Assertions.assertEquals(listaEsperada, listaAtual);
    }

    @Test
    void inserir_RetornaLivroInserido() {
        // Mockando o comportamento do repositório
        Livro livroInserido = new Livro(1L, "Livro 1", "Editora 1", "Autor 1", 10.0, "", "Categoria 1");
        Mockito.when(repository.save(Mockito.any(Livro.class))).thenReturn(livroInserido);

        // Chamando o método do serviço
        Livro livroAtual = service.insert(livroInserido);

        // Verificando o resultado
        Assertions.assertEquals(livroInserido, livroAtual);
    }

    @Test
    void deletar_IdExistente_DeletaLivro() {
        // Chamando o método do serviço
        service.delete(1L);

        // Verificando se o método do repositório foi chamado corretamente
        Mockito.verify(repository, Mockito.times(1)).deleteById(1L);
    }

    @Test
    void atualizar_IdExistente_RetornaLivroAtualizado() {
        // Mockando o comportamento do repositório
        Livro livroExistente = new Livro(1L, "Livro 1", "Editora 1", "Autor 1", 10.0, "", "Categoria 1");
        Livro livroAtualizado = new Livro(1L, "Livro Atualizado", "Editora Atualizada", "Autor Atualizado", 20.0, "", "Categoria Atualizada");
        Mockito.when(repository.findById(1L)).thenReturn(Optional.of(livroExistente));
        Mockito.when(repository.save(Mockito.any(Livro.class))).thenReturn(livroAtualizado);

        // Chamando o método do serviço
        Livro livroAtual = service.update(1L, livroAtualizado);

        // Verificando o resultado
        Assertions.assertEquals(livroAtualizado, livroAtual);
    }

    @Test
    void atualizar_IdNaoExistente_LancaResourceNotFoundException() {
        // Mockando o comportamento do repositório
        Mockito.when(repository.findById(1L)).thenReturn(Optional.empty());

        // Chamando o método do serviço e verificando a exceção
        Assertions.assertThrows(ResourceNotFoundException.class, () -> service.update(1L, new Livro()));
    }
}
