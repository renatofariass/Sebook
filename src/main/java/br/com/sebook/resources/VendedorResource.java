package br.com.sebook.resources;

import br.com.sebook.entities.Livro;
import br.com.sebook.entities.Vendedor;
import br.com.sebook.services.VendedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/vendedores")
public class VendedorResource {
    @Autowired
    VendedorService service;

    @GetMapping
    public ResponseEntity<List<Vendedor>> findAll() {
        List<Vendedor> lista = service.findAll();
        return ResponseEntity.ok().body(lista);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Vendedor> findById(@PathVariable Long id)  {
        Vendedor vendedor = service.findById(id);
        return ResponseEntity.ok().body(vendedor);
    }

    @PostMapping
    public ResponseEntity<Vendedor> insert(@RequestBody Vendedor vendedor) {
        vendedor = service.insert(vendedor);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(vendedor.getId()).toUri();
        return ResponseEntity.created(uri).body(vendedor);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Vendedor> update(@PathVariable Long id, @RequestBody Vendedor vendedor) {
        vendedor = service.update(id, vendedor);
        return ResponseEntity.ok().body(vendedor);
    }

    @GetMapping("/{id}/livros")
    public ResponseEntity<List<Livro>> findLivros(@PathVariable Long id) {
        Vendedor vendedor = service.findById(id);
        return ResponseEntity.ok().body(vendedor.getLivros());
    }
}
