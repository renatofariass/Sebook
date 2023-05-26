package br.com.sebook.resources;

import br.com.sebook.entities.Categoria;
import br.com.sebook.entities.dto.categoria.CategoriaDto;
import br.com.sebook.entities.dto.categoria.CategoriaMinDto;
import br.com.sebook.entities.dto.livro.LivroMinDto;
import br.com.sebook.mapper.Mapper;
import br.com.sebook.services.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("${url_origin}")
@RequestMapping(value = "/categorias")
public class CategoriaResource {
    @Autowired
    CategoriaService service;

    @GetMapping
    public ResponseEntity<List<CategoriaMinDto>> findAll() {
        List<Categoria> lista = service.findAll();
        List<CategoriaMinDto> listaDto = Mapper.parseListObjects(lista, CategoriaMinDto.class);
        return ResponseEntity.ok().body(listaDto);
    }

    @GetMapping(value = "/{nome}/livros")
    public ResponseEntity<List<LivroMinDto>> findLivros(@PathVariable String nome)  {
        Categoria categoria = service.findByNome(nome);
        CategoriaDto categoriaDto = Mapper.parseObject(categoria, CategoriaDto.class);
        return ResponseEntity.ok().body(categoriaDto.getLivros());
    }
}
