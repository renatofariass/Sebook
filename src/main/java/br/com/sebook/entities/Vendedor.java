package br.com.sebook.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
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
    @Pattern(regexp = "^[a-zA-Z0-9_]+$", message = "O username não pode conter espaços em branco")
    @Size(min = 5, max=10)
    @Column(unique = true)
    private String usernameVendedor;
    @NotBlank
    @Pattern(regexp = "^\\+55\\d{11}$\n", message = "Forneça um número de telefone válido")
    @Size(min = 11, max = 11)
    @Column(unique = true)
    private String contato;
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$\n", message = "Forneça um email válido")
    @NotBlank
    @Column(unique = true)
    private String email;
    @Pattern(regexp = "^(?!(\\d)\\1{10})\\d{3}\\.?\\d{3}\\.?\\d{3}-?\\d{2}$", message = "Forneça um CPF válido")
    @Size(min = 11, max = 11)
    @Column(unique = true)
    private String cpf;
    @Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[-+_!@#$%^&*.,?]).{8,}$", message = "A senha deve conter pelo" +
            "menos 8 caracteres, sendo eles 1 letra maiúscula, 1 minúscula e 1 caractere special")
    @NotBlank
    @Size(min = 8, max = 50)
    private String senha;
    @JsonIgnore
    @OneToMany(mappedBy = "vendedor", cascade = CascadeType.ALL)
    private List<Livro> livros = new ArrayList<>();

    public Vendedor(Long id, String usernameVendedor, String contato, String email, String cpf, String senha) {
        this.id = id;
        this.usernameVendedor = usernameVendedor;
        this.contato = contato;
        this.email = email;
        this.cpf = cpf;
        this.senha = senha;
    }
}
