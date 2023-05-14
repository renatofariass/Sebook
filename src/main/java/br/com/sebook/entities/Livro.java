package br.com.sebook.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Table(name = "tb_livros")
@NoArgsConstructor
@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
public class Livro implements Serializable {
    private static final Long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty
    @NotBlank
    private String titulo;
    @NotEmpty
    @NotBlank
    private String editora;
    @NotEmpty
    @NotBlank
    private String autor;
    @NotNull
    private Double preco;
    private String imgUrl;
    @NotEmpty
    @NotBlank
    private String nomeCategoria;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "categoria_id", nullable = false)
    private Categoria categoria;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "sebo_id", nullable = false)
    private Sebo sebo;

    public Livro(Long id, String titulo, String editora, String autor, Double preco, String imgUrl, String nomeCategoria) {
        this.id = id;
        this.titulo = titulo;
        this.editora = editora;
        this.autor = autor;
        this.preco = preco;
        this.imgUrl = imgUrl;
        this.nomeCategoria = nomeCategoria;
    }
}
