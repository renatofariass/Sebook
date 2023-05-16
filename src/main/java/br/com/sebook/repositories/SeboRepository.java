package br.com.sebook.repositories;

import br.com.sebook.entities.Livro;
import br.com.sebook.entities.Sebo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SeboRepository extends JpaRepository<Sebo, Long> {
    @Query("SELECT s FROM Sebo s WHERE lower(s.nome) LIKE lower(concat('%', :nome, '%'))")
    List<Sebo> findBySebo(String nome);

    @Query("SELECT s FROM Sebo s WHERE lower(s.bairro) LIKE lower(concat('%', :bairro, '%'))")
    List<Sebo> findBySeboBairro(String bairro);
}
