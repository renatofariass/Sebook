package br.com.sebook.config;

import br.com.sebook.entities.Categoria;
import br.com.sebook.entities.Livro;
import br.com.sebook.entities.Vendedor;
import br.com.sebook.repositories.CategoriaRepository;
import br.com.sebook.repositories.LivroRepository;
import br.com.sebook.repositories.VendedorRepository;
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
    private VendedorRepository vendedorRepository;

    @Autowired
    private LivroRepository livroRepository;

    @Override
    public void run(String... args) throws Exception {
        Categoria cat1 = new Categoria(null, "LGBTQ+");
        Categoria cat2 = new Categoria(null, "Literatura e Ficção");
        Categoria cat3 = new Categoria(null, "Medicina");
        Categoria cat4 = new Categoria(null, "Policial, Suspense e Mistério");
        categoriaRepository.saveAll(Arrays.asList(cat1, cat2, cat3, cat4));


       /* Vendedor vend1 = new Vendedor(null, "Renato", "81983675820", "renato@gmail.com", "12345678910", "12345678");
        Vendedor vend2 = new Vendedor(null, "João", "81983675821", "joao@gmail.com", "12345678911", "12345678");
        Vendedor vend3 = new Vendedor(null, "Madu", "81983675822", "madu@gmail.com", "12345678912", "12345678");
        Vendedor vend4 = new Vendedor(null, "Thales", "81983675823", "thales@gmail.com", "12345678913", "12345678");
        vendedorRepository.saveAll(Arrays.asList(vend1, vend2, vend3, vend4));

        Livro liv1 = new Livro(null, "titulo1", "editora1", "autor1", 126, 126.0, cat1, vend1);
        Livro liv2 = new Livro(null, "titulo2", "editora2", "autor2", 127, 127.0, cat2, vend2);
        Livro liv3 = new Livro(null, "titulo3", "editora3", "autor3", 128, 128.0, cat3, vend3);
        Livro liv4 = new Livro(null, "titulo4", "editora4", "autor4", 129, 129.0, cat4, vend4);
        livroRepository.saveAll(Arrays.asList(liv1, liv2, liv3, liv4));
        */
    }
}
