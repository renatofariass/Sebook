package br.com.sebook.resources;

import br.com.sebook.entities.Categoria;
import br.com.sebook.entities.Livro;
import br.com.sebook.entities.Sebo;
import br.com.sebook.entities.dto.livro.LivroDto;
import br.com.sebook.entities.dto.livro.LivroMinDto;
import br.com.sebook.entities.dto.sebo.SeboLivrosDto;
import br.com.sebook.mapper.Mapper;
import br.com.sebook.services.CategoriaService;
import br.com.sebook.services.LivroService;
import br.com.sebook.services.SeboService;
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
    LivroService livroService;
    @Autowired
    CategoriaService categoriaService;
    @Autowired
    SeboService seboService;

    @GetMapping
    public ResponseEntity<List<LivroMinDto>> findAll() {
        List<Livro> livros = livroService.findAll();
        List<LivroMinDto> livroMinDtos = Mapper.parseListObjects(livros, LivroMinDto.class);
        return ResponseEntity.ok().body(livroMinDtos);
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<LivroDto> findById(@PathVariable Long id) {
        Livro livro = livroService.findById(id);
        LivroDto livroDto = Mapper.parseObject(livro, LivroDto.class);
        return ResponseEntity.ok().body(livroDto);
    }

    @GetMapping(value = "/buscar")
    public ResponseEntity<List<LivroMinDto>> findByTitulo(@RequestParam String titulo)  {
        List<Livro> livros = livroService.findByTitulo(titulo);
        List<LivroMinDto> livroMinDtos = Mapper.parseListObjects(livros, LivroMinDto.class);
        return ResponseEntity.ok().body(livroMinDtos);
    }

    @GetMapping(value = "/sebo/{id}/livros")
    public ResponseEntity<List<LivroMinDto>> livrosUsuario(@PathVariable Long id) {
        Sebo sebo = seboService.findById(id);
        SeboLivrosDto seboLivrosDto = Mapper.parseObject(sebo, SeboLivrosDto.class);
        return ResponseEntity.ok().body(seboLivrosDto.getLivros());
    }
    @PostMapping(value = "/sebo/{id}")
    public ResponseEntity<LivroDto> insert(@RequestBody Livro livro, @PathVariable Long id) {
        Categoria categoria = categoriaService.findByNome(livro.getNomeCategoria());
        livro.setCategoria(categoria);
        Sebo sebo = seboService.findById(id);
        livro.setSebo(sebo);
        livro = livroService.insert(livro);
        LivroDto livroDto = Mapper.parseObject(livro, LivroDto.class);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(livro.getId()).toUri();
        return ResponseEntity.created(uri).body(livroDto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        livroService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<LivroDto> update(@PathVariable Long id, @RequestBody Livro livro) {
        livro = livroService.update(id, livro);
        LivroDto livroDto = Mapper.parseObject(livro, LivroDto.class);
        return ResponseEntity.ok().body(livroDto);
    }

}
