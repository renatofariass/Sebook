package br.com.sebook.repositories;

import br.com.sebook.entities.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LivroRepository extends JpaRepository<Livro, Long> {
    @Query("SELECT l FROM Livro l WHERE lower(l.titulo) = lower(:titulo)")
    Livro findByTitulo(String titulo);

    @Query("SELECT l FROM Livro l WHERE lower(l.titulo) LIKE lower(concat('%', :titulo, '%'))")
    List<Livro> findByTituloContaining(String titulo);
}
