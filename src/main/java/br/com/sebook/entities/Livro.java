package br.com.sebook.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Table(name = "tb_livro")
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class Livro implements Serializable {
    private static final Long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String titulo;
    @NotBlank
    private String editora;
    @NotBlank
    private String autor;
    private Integer numeroDePaginas;
    private Double preco;
    private String imgUrl;
    @NotBlank
    private String nomeCategoria;
    @NotBlank
    private String usernameUsuario;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "categoria_id", nullable = false)
    private Categoria categoria;
    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    public Livro(Long id, String titulo, String editora, String autor, Integer numeroDePaginas, Double preco, String imgUrl, String nomeCategoria) {
        this.id = id;
        this.titulo = titulo;
        this.editora = editora;
        this.autor = autor;
        this.numeroDePaginas = numeroDePaginas;
        this.preco = preco;
        this.imgUrl = imgUrl;
        this.nomeCategoria = nomeCategoria;
    }
}
