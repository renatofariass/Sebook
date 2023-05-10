package br.com.sebook.entities.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.io.Serializable;

/**
 * A DTO for the {@link br.com.sebook.entities.Livro} entity
 */
@AllArgsConstructor
@NoArgsConstructor
public class LivroDto implements Serializable {
    private Long id;
    @NotBlank
    private String titulo;
    @NotBlank
    private String editora;
    @NotBlank
    private String autor;
    @NotBlank
    private Integer numeroDePaginas;
    @NotBlank
    private Double preco;
    @NotBlank
    private String imgUrl;
    @NotBlank
    private String nomeCategoria;
    @NotBlank
    private UsuarioMinDto usuario;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getEditora() {
        return editora;
    }

    public void setEditora(String editora) {
        this.editora = editora;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public Integer getNumeroDePaginas() {
        return numeroDePaginas;
    }

    public void setNumeroDePaginas(Integer numeroDePaginas) {
        this.numeroDePaginas = numeroDePaginas;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getNomeCategoria() {
        return nomeCategoria;
    }

    public void setNomeCategoria(String nomeCategoria) {
        this.nomeCategoria = nomeCategoria;
    }

    public UsuarioMinDto getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioMinDto usuario) {
        this.usuario = usuario;
    }
}