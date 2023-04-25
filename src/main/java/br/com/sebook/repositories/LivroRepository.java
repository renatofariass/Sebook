package br.com.sebook.repositories;

import br.com.sebook.entities.Livro;
import br.com.sebook.entities.Vendedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface LivroRepository extends JpaRepository<Livro, Long> {
    @Query("SELECT l FROM Livro l WHERE lower(l.titulo) = lower(:titulo)")
    Livro findByTitulo(String titulo);
}
