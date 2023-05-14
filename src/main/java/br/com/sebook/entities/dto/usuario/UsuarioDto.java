package br.com.sebook.entities.dto.usuario;

import lombok.*;

import java.io.Serializable;

/**
 * A DTO for the {@link br.com.sebook.entities.Usuario} entity
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
public class UsuarioDto implements Serializable {
    private Long id;
    private String nome;
    private String email;
}