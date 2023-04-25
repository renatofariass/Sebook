package br.com.sebook.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.io.Serializable;

@Entity
@Table(name = "tb_usuario")
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class Usuario implements Serializable {
    private static final Long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String nome;
    @NotBlank
    @Size(min = 11, max = 11)
    @Column(unique = true)
    private String contato;
    @NotBlank
    @Column(unique = true)
    private String email;
    @NotBlank
    @Size(min = 8, max = 50)
    private String senha;

    public Usuario(Long id, String nome, String contato, String email, String senha) {
        this.id = id;
        this.nome = nome;
        this.contato = contato;
        this.email = email;
        this.senha = senha;
    }
}
