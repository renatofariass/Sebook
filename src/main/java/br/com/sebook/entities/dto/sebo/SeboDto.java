package br.com.sebook.entities.dto.sebo;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.io.Serializable;

/**
 * A DTO for the {@link br.com.sebook.entities.Sebo} entity
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
public class SeboDto implements Serializable {
    private Long id;
    private String imgUrl;
    private String nome;
    private String telefone;
    private String endereco;
    private String bairro;
    private String cep;
}