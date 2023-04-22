package br.com.sebook.resources;

import br.com.sebook.entities.Categoria;
import br.com.sebook.entities.Livro;
import br.com.sebook.services.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResource {
    @Autowired
    CategoriaService service;

    @GetMapping
    public ResponseEntity<List<Categoria>> findAll() {
        List<Categoria> lista = service.findAll();
        return ResponseEntity.ok().body(lista);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Categoria> findById(@PathVariable Long id)  {
        Categoria categoria = service.findById(id);
        return ResponseEntity.ok().body(categoria);
    }

    @GetMapping(value = "/{id}/livros")
    public ResponseEntity<List<Livro>> findLivros(@PathVariable Long id)  {
        Categoria categoria = service.findById(id);
        return ResponseEntity.ok().body(categoria.getLivros());
    }
}
