package br.com.sebook.config;

import br.com.sebook.entities.Categoria;
import br.com.sebook.repositories.CategoriaRepository;
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

    @Override
    public void run(String... args) throws Exception {
        categoriaRepository.saveAll(Arrays.asList(
                new Categoria(null, "LGBTQ+"),
                new Categoria(null, "Literatura e Ficção"),
                new Categoria(null, "Medicina"),
                new Categoria(null, "Policial, Suspense e Mistério"),
                new Categoria(null, "Política, Filosofia e Ciências Sociais"),
                new Categoria(null, "Religião e Espiritualidade"),
                new Categoria(null, "Romance"),
                new Categoria(null, "Saúde e Família"),
                new Categoria(null, "Turismo e Guias de Viagem"),
                new Categoria(null, "Inglês e Outras Línguas"),
                new Categoria(null, "Jovens e Adolescentes"),
                new Categoria(null, "Administração, Negócios e Economia"),
                new Categoria(null, "Arte, Cinema e Fotografia"),
                new Categoria(null, "Artesanato, Casa e Estilo de Vida"),
                new Categoria(null, "Autoajuda"),
                new Categoria(null, "Biografias e Histórias Reais"),
                new Categoria(null, "Ciências"),
                new Categoria(null, "Computação, Informática e Mídias Digitais"),
                new Categoria(null, "Crônicas, Humor e Entretenimento"),
                new Categoria(null, "Direito"),
                new Categoria(null, "Educação, Referência e Didáticos"),
                new Categoria(null, "Engenharia e Transporte"),
                new Categoria(null, "Erótico"),
                new Categoria(null, "Esportes e Lazer"),
                new Categoria(null, "Fantasia, Horror e Ficção Científica"),
                new Categoria(null, "Gastronomia e Culinária"),
                new Categoria(null, "História"),
                new Categoria(null, "HQs, Mangás e Graphic Novels"),
                new Categoria(null, "Infantil")
        ));
    }
}
