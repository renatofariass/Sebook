package br.com.sebook.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class UsuarioDTO implements Serializable {
    private static final Long serialVersionUID = 1L;

    private String nome;
    private String contato;
    private String email;
    private String senha;

    public UsuarioDTO(String nome, String contato, String email, String senha) {
        this.nome = nome;
        this.contato = contato;
        this.email = email;
        this.senha = senha;
    }
}
