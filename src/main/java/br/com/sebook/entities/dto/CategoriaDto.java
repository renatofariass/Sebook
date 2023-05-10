package br.com.sebook.entities.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.io.Serializable;
import java.util.List;

/**
 * A DTO for the {@link br.com.sebook.entities.Categoria} entity
 */

@AllArgsConstructor
@NoArgsConstructor
public class CategoriaDto implements Serializable {
    private String nome;
    private List<LivroMinDto> livros;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<LivroMinDto> getLivros() {
        return livros;
    }

    public void setLivros(List<LivroMinDto> livros) {
        this.livros = livros;
    }
}