package br.com.sebook.services.exceptions;

public class CategoriaNotFoundException extends RuntimeException {
    public CategoriaNotFoundException(String nome) {
        super("Categoria não encontrada: " + nome);
    }
}
