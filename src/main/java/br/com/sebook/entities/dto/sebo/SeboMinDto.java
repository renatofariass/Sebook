package br.com.sebook.entities.dto.sebo;

import lombok.*;

import java.io.Serializable;

/**
 * A DTO for the {@link br.com.sebook.entities.Sebo} entity
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
public class SeboMinDto implements Serializable {
    private Long id;
    private String nome;
    private String whatsapp;
}