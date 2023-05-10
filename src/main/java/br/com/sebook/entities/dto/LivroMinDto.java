package br.com.sebook.entities.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.io.Serializable;

/**
 * A DTO for the {@link br.com.sebook.entities.Livro} entity
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class LivroMinDto implements Serializable {
    private Long id;
    private String titulo;
    private String autor;
    private Integer numeroDePaginas;
    private Double preco;
    private String imgUrl;
}