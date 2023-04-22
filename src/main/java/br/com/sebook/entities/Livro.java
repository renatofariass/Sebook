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
    @Column(nullable = false)
    private String titulo;
    @NotBlank
    @Column(nullable = false)
    private String editora;
    @NotBlank
    @Column(nullable = false)
    private String autor;
    @Column(nullable = false)
    private Integer numeroDePaginas;
    @Column(nullable = false)
    private Double preco;
    @NotBlank
    @Column(nullable = false)
    private String imgUrl;
    @Column(nullable = false)
    private Long idDaCategoria;
    @Column(nullable = false)
    private Long idDoVendedor;
    @ManyToOne
    @JoinColumn(name = "categoria_id", nullable = false)
    private Categoria categoria;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "vendedor_id", nullable = false)
    private Vendedor vendedor;


    public Livro(Long id, String titulo, String autor, String editora, Integer numeroDePaginas, Double preco, String imgUrl, Long idDaCategoria, Long idDoVendedor) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.editora = editora;
        this.numeroDePaginas = numeroDePaginas;
        this.preco = preco;
        this.imgUrl = imgUrl;
        this.idDaCategoria = idDaCategoria;
        this.idDoVendedor = idDoVendedor;
    }
}
