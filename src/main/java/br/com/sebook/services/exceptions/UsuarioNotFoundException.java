package br.com.sebook.services.exceptions;

public class UsuarioNotFoundException extends RuntimeException {
    public UsuarioNotFoundException(String username) {
        super("Usuário não encontrado: " + username);
    }
}
