package br.com.sebook.repositories;

import br.com.sebook.entities.DonoSebo;
import br.com.sebook.entities.Sebo;
import br.com.sebook.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface DonoSeboRepository extends JpaRepository<DonoSebo, Long> {
    @Query("SELECT ds FROM DonoSebo ds WHERE lower(ds.nome) = lower(:nome)")
    DonoSebo findByNome(String nome);
}
