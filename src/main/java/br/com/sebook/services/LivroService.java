package br.com.sebook.services;

import br.com.sebook.entities.Livro;
import br.com.sebook.repositories.LivroRepository;
import br.com.sebook.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LivroService {
    @Autowired
    private LivroRepository livroRepository;

    public List<Livro> findAll() {
        return livroRepository.findAll();
    }

    public Livro findById(Long id) {
        Optional<Livro> livro = livroRepository.findById(id);
        return livro.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public Livro insert(Livro livro) {
        return livroRepository.save(livro);
    }

    public void delete(Long id) {
        livroRepository.deleteById(id);
    }

    public Livro update(Long id, Livro livro) {
        Livro entity = livroRepository.getReferenceById(id);
        updateData(entity, livro);
        return livroRepository.save(livro);
    }

    private void updateData(Livro entity, Livro livro) {
        entity.setTitulo(livro.getTitulo());
        entity.setAutor(livro.getAutor());
        entity.setEditora(livro.getEditora());
        entity.setNumeroDePaginas(livro.getNumeroDePaginas());
        entity.setPreco(livro.getPreco());
    }
}
