package br.com.sebook.entities.dto;

import lombok.*;

import java.io.Serializable;
import java.util.List;

/**
 * A DTO for the {@link br.com.sebook.entities.Usuario} entity
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class UsuarioLivroDto implements Serializable {
    private String username;
    private String nome;
    private List<LivroMinDto> livros;
}