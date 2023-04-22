package br.com.sebook.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_categoria")
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class Categoria implements Serializable {
    private static final Long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    @JsonIgnore
    @OneToMany(mappedBy = "categoria", cascade = CascadeType.ALL, targetEntity = Livro.class)
    private List<Livro> livros = new ArrayList<>();

    public Categoria(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

}
