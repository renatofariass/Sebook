package br.com.sebook.resources;

import br.com.sebook.entities.Categoria;
import br.com.sebook.entities.Sebo;
import br.com.sebook.entities.dto.categoria.CategoriaDto;
import br.com.sebook.entities.dto.categoria.CategoriaMinDto;
import br.com.sebook.entities.dto.livro.LivroMinDto;
import br.com.sebook.entities.dto.sebo.SeboDto;
import br.com.sebook.entities.dto.sebo.SeboLivrosDto;
import br.com.sebook.mapper.Mapper;
import br.com.sebook.services.CategoriaService;
import br.com.sebook.services.SeboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/sebos")
public class SeboResource {
    @Autowired
    SeboService service;

    @GetMapping
    public ResponseEntity<List<SeboDto>> findAll() {
        List<Sebo> lista = service.findAll();
        List<SeboDto> listaDto = Mapper.parseListObjects(lista, SeboDto.class);
        return ResponseEntity.ok().body(listaDto);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<SeboLivrosDto> findLivros(@PathVariable Long id)  {
        Sebo sebo = service.findById(id);
        SeboLivrosDto seboLivrosDto = Mapper.parseObject(sebo, SeboLivrosDto.class);
        return ResponseEntity.ok().body(seboLivrosDto);
    }
}
