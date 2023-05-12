package br.com.sebook.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_usuario")
@NoArgsConstructor
@Getter
@Setter
public class Usuario implements Serializable {
    private static final Long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @Column(unique = true)
    private String username;
    @NotBlank
    private String nome;
    @Column(unique = true)
    private String telefone;
    @NotBlank
    private String whatsapp;
    @Email
    @NotBlank
    @Column(unique = true)
    private String email;
    @NotBlank
    @Size(min = 8, max = 100)
    private String senha;
    @JsonIgnore
    @OneToMany(mappedBy = "usuario")
    private List<Livro> livros = new ArrayList<>();

    public Usuario(Long id, String nome, String telefone, String email, String senha) {
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.senha = senha;
    }
}
