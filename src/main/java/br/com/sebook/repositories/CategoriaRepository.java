package br.com.sebook.repositories;

import br.com.sebook.entities.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
    @Query("SELECT c FROM Categoria c WHERE lower(c.nome) = lower(:nome)")
    Categoria findByNome(String nome);
}
