package br.com.sebook.entities.dto;

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
    @NotBlank
    private String titulo;
    @NotBlank
    private String editora;
    @NotBlank
    private String autor;
    @NotBlank
    private Integer numeroDePaginas;
    @NotBlank
    private Double preco;
    @NotBlank
    private String imgUrl;
    @NotBlank
    private String nomeCategoria;
    @NotBlank
    private UsuarioMinDto usuario;
}