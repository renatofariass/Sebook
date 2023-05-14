package br.com.sebook.entities.dto.categoria;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.io.Serializable;

/**
 * A DTO for the {@link br.com.sebook.entities.Categoria} entity
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
public class CategoriaMinDto implements Serializable {
    private Long id;
    @NotBlank
    private String nome;

}