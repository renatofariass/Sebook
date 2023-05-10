package br.com.sebook.resources;

import br.com.sebook.entities.Categoria;
import br.com.sebook.entities.Livro;
import br.com.sebook.entities.dto.CategoriaDto;
import br.com.sebook.entities.dto.CategoriaMinDto;
import br.com.sebook.entities.dto.LivroDto;
import br.com.sebook.entities.dto.LivroMinDto;
import br.com.sebook.mapper.Mapper;
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
