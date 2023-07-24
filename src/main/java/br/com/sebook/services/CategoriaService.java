package br.com.sebook.services;

import br.com.sebook.entities.Categoria;
import br.com.sebook.repositories.CategoriaRepository;
import br.com.sebook.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {
    @Autowired
    private CategoriaRepository categoriaRepository;

    public List<Categoria> findAll() {
        return categoriaRepository.findAll();
    }

    public Categoria findByNome(String nome) {
        Optional<Categoria> categoria = Optional.ofNullable(categoriaRepository.findByNome(nome));
        return categoria.orElseThrow(() -> new ResourceNotFoundException("Categoria não encontrada."));
    }

}
