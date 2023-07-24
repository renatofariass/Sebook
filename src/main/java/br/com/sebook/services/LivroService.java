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
        return livro.orElseThrow(() -> new ResourceNotFoundException("Livro não encontrado."));
    }

    public List<Livro> findByTitulo(String titulo) {
        return livroRepository.findByTituloContaining(titulo);
    }

    public Livro insert(Livro livro) {

        return livroRepository.save(livro);
    }

    public void delete(Long id) {
        livroRepository.deleteById(id);
    }

    public Livro update(Long id, Livro obj) {
        Livro livro = livroRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Livro não encontrado."));
        updateData(livro, obj);
        return livroRepository.save(livro);
    }

    private void updateData(Livro livro, Livro obj) {
        if (obj.getTitulo() != null) {
            livro.setTitulo(obj.getTitulo());
        }
        if (obj.getAutor() != null) {
            livro.setAutor(obj.getAutor());
        }
        if (obj.getEditora() != null) {
            livro.setEditora(obj.getEditora());
        }
        if (obj.getPreco() != null) {
            livro.setPreco(obj.getPreco());
        }
    }
}
