package br.com.sebook.entities.dto;

import lombok.*;

import java.io.Serializable;

/**
 * A DTO for the {@link br.com.sebook.entities.Usuario} entity
 */

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class UsuarioMinDto implements Serializable {
    private Long id;
    private String username;
    private String nome;
    private String whatsapp;
}