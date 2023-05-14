package br.com.sebook.entities.dto.livro;

import lombok.*;

import java.io.Serializable;

/**
 * A DTO for the {@link br.com.sebook.entities.Livro} entity
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
public class LivroMinDto implements Serializable {
    private Long id;
    private String titulo;
    private String autor;
    private Double preco;
    private String imgUrl;
}