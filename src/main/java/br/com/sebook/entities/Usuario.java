package br.com.sebook.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.io.Serializable;

@Entity
@Table(name = "tb_usuario")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class Usuario implements Serializable {
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
    @Size(min = 8, max = 50)
    private String senha;
}
