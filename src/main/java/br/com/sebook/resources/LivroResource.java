package br.com.sebook.resources;

import br.com.sebook.entities.Categoria;
import br.com.sebook.entities.Livro;
import br.com.sebook.entities.Usuario;
import br.com.sebook.services.CategoriaService;
import br.com.sebook.services.LivroService;
import br.com.sebook.services.UsuarioService;
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
    UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<List<Livro>> findAll() {
        return ResponseEntity.ok().body(service.findAll());
    }

    @GetMapping(value = "/buscar")
    public ResponseEntity<List<Livro>> findByTitulo(@RequestParam String titulo)  {
        return ResponseEntity.ok().body(service.findByTitulo(titulo));
    }

    @PostMapping(value = "{username}")
    public ResponseEntity<Livro> insert(@PathVariable String username, @RequestBody Livro livro) {
        Categoria categoria = categoriaService.findByNome(livro.getNomeCategoria());
        livro.setCategoria(categoria);
        livro.setUsernameUsuario(username);
        Usuario usuario = usuarioService.findByUsuario(livro.getUsernameUsuario());
        livro.setUsuario(usuario);
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
