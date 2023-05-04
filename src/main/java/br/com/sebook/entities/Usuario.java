package br.com.sebook.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
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
    @Pattern(regexp = "^\\+55\\d{11}$", message = "Forneça um número de telefone válido")
    @Size(min = 14, max = 14)
    @Column(unique = true)
    private String contato;
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "Forneça um email válido")
    @NotBlank
    @Column(unique = true)
    private String email;
    @Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[-+_!@#$%^&*.,?]).{8,}$", message = "A senha deve conter pelo" +
            "menos 8 caracteres, sendo eles 1 letra maiúscula, 1 minúscula e 1 caractere special")
    @NotBlank
    @Size(min = 8, max = 100)
    private String senha;

    public Usuario(Long id, String nome, String contato, String email, String senha) {
        this.id = id;
        this.nome = nome;
        this.contato = contato;
        this.email = email;
        this.senha = senha;
    }
}
