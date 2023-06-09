package br.com.sebook.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_categorias")
@NoArgsConstructor
@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
public class Categoria implements Serializable {
    private static final Long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String nome;
    @JsonIgnore
    @OneToMany(mappedBy = "categoria")
    private List<Livro> livros = new ArrayList<>();

    public Categoria(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }
}
