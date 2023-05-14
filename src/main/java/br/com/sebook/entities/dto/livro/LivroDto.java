package br.com.sebook.entities.dto.livro;

import br.com.sebook.entities.Sebo;
import br.com.sebook.entities.dto.sebo.SeboMinDto;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.io.Serializable;

/**
 * A DTO for the {@link br.com.sebook.entities.Livro} entity
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
public class LivroDto implements Serializable {
    private Long id;
    private String titulo;
    private String editora;
    private String autor;
    private Double preco;
    private String imgUrl;
    private String nomeCategoria;
    private SeboMinDto sebo;
}