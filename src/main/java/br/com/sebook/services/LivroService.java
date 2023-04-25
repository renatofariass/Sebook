package br.com.sebook.services;

import br.com.sebook.entities.Livro;
import br.com.sebook.repositories.LivroRepository;
import br.com.sebook.services.exceptions.NameNotFoundException;
import br.com.sebook.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LivroService {
    @Autowired
    private LivroRepository livroRepository;

    public Page<Livro> findAll(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        return livroRepository.findAll(pageable);
    }

    public Livro findByTitulo(String titulo) {
        Livro livro = livroRepository.findByTitulo(titulo);
        if(livro == null) {
            throw new NameNotFoundException(titulo);
        }
        return livro;
    }

    public Livro insert(Livro livro) {

        return livroRepository.save(livro);
    }

    public void delete(Long id) {
        livroRepository.deleteById(id);
    }

    public Livro update(Long id, Livro obj) {
        Livro livro = livroRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id));
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
        if (obj.getNumeroDePaginas() != null) {
            livro.setNumeroDePaginas(obj.getNumeroDePaginas());
        }
        if (obj.getPreco() != null) {
            livro.setPreco(obj.getPreco());
        }
    }
}
