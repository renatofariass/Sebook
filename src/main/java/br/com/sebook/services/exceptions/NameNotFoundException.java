package br.com.sebook.services.exceptions;

public class NameNotFoundException extends RuntimeException {
    public NameNotFoundException(String nome) {
        super("Categoria não encontrada: " + nome);
    }
}
