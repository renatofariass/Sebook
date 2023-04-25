package br.com.sebook.resources;

import br.com.sebook.entities.Categoria;
import br.com.sebook.entities.Livro;
import br.com.sebook.entities.Vendedor;
import br.com.sebook.services.CategoriaService;
import br.com.sebook.services.LivroService;
import br.com.sebook.services.VendedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<Page<Livro>> findAll(@RequestParam(defaultValue = "0") Integer page,
                                               @RequestParam(defaultValue = "10") Integer size) {
        Page<Livro> lista = service.findAll(page, size);
        return ResponseEntity.ok().body(lista);
    }

    @GetMapping(value = "/{titulo}")
    public ResponseEntity<Livro> findByTitulo(@PathVariable String id)  {
        Livro livro = service.findByTitulo(id);
        return ResponseEntity.ok().body(livro);
    }

    @PostMapping
    public ResponseEntity<Livro> insert(@RequestBody Livro livro, @PathVariable String usernameVendedor) {
        Categoria categoria = categoriaService.findByNome(livro.getNomeCategoria());
        livro.setCategoria(categoria);
        Vendedor vendedor = vendedorService.findByUsernameVendedor(usernameVendedor);
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
