package br.com.sebook.repositories;

import br.com.sebook.entities.Livro;
import br.com.sebook.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
