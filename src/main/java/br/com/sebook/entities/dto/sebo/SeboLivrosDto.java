package br.com.sebook.entities.dto.sebo;

import br.com.sebook.entities.dto.livro.LivroMinDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.io.Serializable;
import java.util.List;

/**
 * A DTO for the {@link br.com.sebook.entities.Sebo} entity
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
public class SeboLivrosDto implements Serializable {
    private Long id;
    private String imgUrl;
    private String nome;
    private String endereco;
    private String bairro;
    private String cep;
    private List<LivroMinDto> livros;
}