package br.com.sebook.entities.dto.categoria;

import br.com.sebook.entities.dto.livro.LivroMinDto;
import lombok.*;

import java.io.Serializable;
import java.util.List;

/**
 * A DTO for the {@link br.com.sebook.entities.Categoria} entity
 */

@AllArgsConstructor
@NoArgsConstructor
@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
public class CategoriaDto implements Serializable {
    private Long id;
    private String nome;
    private List<LivroMinDto> livros;
}