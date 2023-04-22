package br.com.sebook.resources;

import br.com.sebook.entities.Categoria;
import br.com.sebook.entities.Livro;
import br.com.sebook.entities.Vendedor;
import br.com.sebook.services.CategoriaService;
import br.com.sebook.services.LivroService;
import br.com.sebook.services.VendedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/livros")
public class LivroResource {
    @Autowired
    LivroService service;
    @Autowired
    CategoriaService categoriaService;

    @Autowired
    VendedorService vendedorService;

    @GetMapping
    public ResponseEntity<List<Livro>> findAll() {
        List<Livro> lista = service.findAll();
        return ResponseEntity.ok().body(lista);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Livro> findById(@PathVariable Long id)  {
        Livro livro = service.findById(id);
        return ResponseEntity.ok().body(livro);
    }

    @PostMapping
    public ResponseEntity<Livro> insert(@RequestBody Livro livro) {
        Categoria categoria = categoriaService.findById(livro.getIdDaCategoria());
        livro.setCategoria(categoria);
        Vendedor vendedor = vendedorService.findById(livro.getIdDoVendedor());
        livro.setVendedor(vendedor);
        livro = service.insert(livro);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(livro.getId()).toUri();
        return ResponseEntity.created(uri).body(livro);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Livro> update(@PathVariable Long id, @RequestBody Livro livro) {
        livro = service.update(id, livro);
        return ResponseEntity.ok().body(livro);
    }

}
