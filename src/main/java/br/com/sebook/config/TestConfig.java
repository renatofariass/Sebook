package br.com.sebook.config;

import br.com.sebook.entities.Categoria;
import br.com.sebook.repositories.CategoriaRepository;
import br.com.sebook.repositories.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.Arrays;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {
    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private LivroRepository livroRepository;

    @Override
    public void run(String... args) throws Exception {
        Categoria cat1 = new Categoria(null, "Literatura e Ficção");
        Categoria cat2 = new Categoria(null, "Policial, Suspense e Mistério");
        categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
    }
}
