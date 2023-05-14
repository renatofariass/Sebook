package br.com.sebook.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_sebos")
@AllArgsConstructor
@NoArgsConstructor
@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
public class Sebo implements Serializable {
    private static final Long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty
    @NotBlank
    @Column(unique = true)
    private String nome;
    @NotEmpty
    @NotBlank
    @Column(unique = true)
    private String telefone;
    @NotEmpty
    @NotBlank
    private String whatsapp;
    @NotEmpty
    @NotBlank
    private String endereco;
    @NotEmpty
    @NotBlank
    private String bairro;
    @NotEmpty
    @NotBlank
    private String cep;
    private String imgUrl;
    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "dono_sebo_id", nullable = false, unique = true)
    private DonoSebo donoSebo;
    @JsonIgnore
    @OneToMany(mappedBy = "sebo")
    private List<Livro> livros = new ArrayList<>();

    public Sebo(Long id, String nome, String telefone, String endereco, String bairro, String cep, String imgUrl) {
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
        this.endereco = endereco;
        this.bairro = bairro;
        this.cep = cep;
        this.imgUrl = imgUrl;
    }
}
