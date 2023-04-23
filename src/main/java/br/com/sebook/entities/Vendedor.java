package br.com.sebook.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_vendedor")
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class Vendedor implements Serializable {
    private static final Long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    @Size(min = 11, max = 11)
    @Column(unique = true)
    private String contato;
    @Column(unique = true)
    private String email;
    @Size(min = 11, max = 11)
    @Column(unique = true)
    private String cpf;
    @Size(min = 8, max = 50)
    private String senha;
    @JsonIgnore
    @OneToMany(mappedBy = "vendedor", cascade = CascadeType.ALL)
    private List<Livro> livros = new ArrayList<>();

    public Vendedor(Long id, String nome, String contato, String email, String cpf, String senha) {
        this.id = id;
        this.nome = nome;
        this.contato = contato;
        this.email = email;
        this.cpf = cpf;
        this.senha = senha;
    }
}
