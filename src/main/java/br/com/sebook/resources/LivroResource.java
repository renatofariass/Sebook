package br.com.sebook.resources;

import br.com.sebook.entities.Categoria;
import br.com.sebook.entities.Livro;
import br.com.sebook.entities.Usuario;
import br.com.sebook.entities.dto.LivroDto;
import br.com.sebook.entities.dto.LivroMinDto;
import br.com.sebook.entities.dto.UsuarioLivroDto;
import br.com.sebook.mapper.Mapper;
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
    public ResponseEntity<List<LivroMinDto>> findAll() {
        List<Livro> livros = service.findAll();
        List<LivroMinDto> livroMinDtos = Mapper.parseListObjects(livros, LivroMinDto.class);
        return ResponseEntity.ok().body(livroMinDtos);
    }

    @GetMapping(value = "/buscar")
    public ResponseEntity<List<LivroMinDto>> findByTitulo(@RequestParam String titulo)  {
        List<Livro> livros = service.findByTitulo(titulo);
        List<LivroMinDto> livroMinDtos = Mapper.parseListObjects(livros, LivroMinDto.class);
        return ResponseEntity.ok().body(livroMinDtos);
    }

    @GetMapping(value = "{username}")
    public ResponseEntity<List<LivroMinDto>> livrosUsuario(@PathVariable String username) {
        Usuario usuario = usuarioService.findByUsuario(username);
        UsuarioLivroDto usuarioLivroDto = Mapper.parseObject(usuario, UsuarioLivroDto.class);
        return ResponseEntity.ok().body(usuarioLivroDto.getLivros());
    }
    @PostMapping(value = "{username}")
    public ResponseEntity<LivroDto> insert(@PathVariable String username, @RequestBody Livro livro) {
        Categoria categoria = categoriaService.findByNome(livro.getNomeCategoria());
        livro.setCategoria(categoria);
        livro.setUsernameUsuario(username);
        Usuario usuario = usuarioService.findByUsuario(livro.getUsernameUsuario());
        livro.setUsuario(usuario);
        livro = service.insert(livro);
        LivroDto livroDto = Mapper.parseObject(livro, LivroDto.class);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(livro.getId()).toUri();
        return ResponseEntity.created(uri).body(livroDto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<LivroDto> update(@PathVariable Long id, @RequestBody Livro livro) {
        livro = service.update(id, livro);
        LivroDto livroDto = Mapper.parseObject(livro, LivroDto.class);
        return ResponseEntity.ok().body(livroDto);
    }

}
