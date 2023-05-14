package br.com.sebook.entities.dto.dono;

import br.com.sebook.entities.dto.sebo.SeboMinDto;
import lombok.*;

import java.io.Serializable;

/**
 * A DTO for the {@link br.com.sebook.entities.DonoSebo} entity
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
public class DonoSeboDto implements Serializable {
    private Long id;
    private String nome;
    private String email;
    private SeboMinDto sebo;
}