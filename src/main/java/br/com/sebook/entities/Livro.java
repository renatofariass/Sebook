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
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "categoria_id", nullable = false)
    private Categoria categoria;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "vendedor_id", nullable = false)
    private Vendedor vendedor;

    public Livro(Long id, String titulo, String editora, String autor, Integer numeroDePaginas, Double preco, Categoria categoria, Vendedor vendedor) {
        this.id = id;
        this.titulo = titulo;
        this.editora = editora;
        this.autor = autor;
        this.numeroDePaginas = numeroDePaginas;
        this.preco = preco;
        this.categoria = categoria;
        this.vendedor = vendedor;
    }
}
