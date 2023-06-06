package br.com.sebook.services;

import br.com.sebook.entities.DonoSebo;
import br.com.sebook.repositories.DonoSeboRepository;
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

class DonoSeboServiceTest {
    @InjectMocks
    private DonoSeboService service;
    @Mock
    private DonoSeboRepository repository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void buscarTodos_RetornaListaDeDonosSebo() {
        // Mockando o comportamento do repositório
        List<DonoSebo> listaEsperada = new ArrayList<>();
        listaEsperada.add(new DonoSebo(1L, "João Silva", "123456789", "joao@gmail.com", "senha123"));
        listaEsperada.add(new DonoSebo(2L, "Maria Santos", "987654321", "maria@gmail.com", "senha456"));
        Mockito.when(repository.findAll()).thenReturn(listaEsperada);

        // Chamando o método do serviço
        List<DonoSebo> listaAtual = service.findAll();

        // Verificando o resultado
        Assertions.assertEquals(listaEsperada, listaAtual);
    }

    @Test
    void buscarPorId_IdExistente_RetornaDonoSebo() {
        // Mockando o comportamento do repositório
        DonoSebo donoSeboEsperado = new DonoSebo(1L, "João Silva", "123456789", "joao@gmail.com", "senha123");
        Mockito.when(repository.findById(1L)).thenReturn(Optional.of(donoSeboEsperado));

        // Chamando o método do serviço
        DonoSebo donoSeboAtual = service.findById(1L);

        // Verificando o resultado
        Assertions.assertEquals(donoSeboEsperado, donoSeboAtual);
    }

    @Test
    void buscarPorId_IdNaoExistente_LancaResourceNotFoundException() {
        // Mockando o comportamento do repositório
        Mockito.when(repository.findById(1L)).thenReturn(Optional.empty());

        // Chamando o método do serviço e verificando a exceção
        Assertions.assertThrows(ResourceNotFoundException.class, () -> service.findById(1L));
    }

    @Test
    void inserir_RetornaDonoSeboInserido() {
        // Mockando o comportamento do repositório
        DonoSebo donoSeboEsperado = new DonoSebo(1L, "João Silva", "123456789", "joao@gmail.com", "senha123");
        Mockito.when(repository.save(Mockito.any(DonoSebo.class))).thenReturn(donoSeboEsperado);

        // Chamando o método do serviço
        DonoSebo donoSeboAtual = service.insert(donoSeboEsperado);

        // Verificando o resultado
        Assertions.assertEquals(donoSeboEsperado, donoSeboAtual);
    }

    @Test
    void deletar_IdExistente_DeletaDonoSebo() {
        // Chamando o método do serviço
        service.delete(1L);

        // Verificando se o método do repositório foi chamado corretamente
        Mockito.verify(repository, Mockito.times(1)).deleteById(1L);
    }

    @Test
    void atualizar_IdExistente_RetornaDonoSeboAtualizado() {
        // Mockando o comportamento do repositório
        DonoSebo donoSeboExistente = new DonoSebo(1L, "João Silva", "123456789", "joao@gmail.com", "senha123");
        DonoSebo donoSeboAtualizado = new DonoSebo(1L, "João da Silva", "987654321", "joao@gmail.com", "novasenha");
        Mockito.when(repository.findById(1L)).thenReturn(Optional.of(donoSeboExistente));
        Mockito.when(repository.save(Mockito.any(DonoSebo.class))).thenReturn(donoSeboAtualizado);

        // Chamando o método do serviço
        DonoSebo donoSeboAtual = service.update(1L, donoSeboAtualizado);

        // Verificando o resultado
        Assertions.assertEquals(donoSeboAtualizado, donoSeboAtual);
    }

    @Test
    void atualizar_IdNaoExistente_LancaResourceNotFoundException() {
        // Mockando o comportamento do repositório
        Mockito.when(repository.findById(1L)).thenReturn(Optional.empty());

        // Chamando o método do serviço e verificando a exceção
        Assertions.assertThrows(ResourceNotFoundException.class, () -> service.update(1L, new DonoSebo()));
    }
}
