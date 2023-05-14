package br.com.sebook.resources;

import br.com.sebook.entities.DonoSebo;
import br.com.sebook.entities.Sebo;
import br.com.sebook.entities.dto.dono.DonoSeboDto;
import br.com.sebook.mapper.Mapper;
import br.com.sebook.services.DonoSeboService;
import br.com.sebook.services.SeboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/donos")
public class DonoSeboResource {
    @Autowired
    DonoSeboService service;

    @Autowired
    SeboService seboService;

    @GetMapping
    public ResponseEntity<List<DonoSeboDto>> findAll() {
        List<DonoSebo> lista = service.findAll();
        List<DonoSeboDto> donoSeboDto = Mapper.parseListObjects(lista, DonoSeboDto.class);
        return ResponseEntity.ok().body(donoSeboDto);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<DonoSeboDto> findById(@PathVariable Long id)  {
        DonoSebo donoSebo = service.findById(id);
        DonoSeboDto donoSeboDto = Mapper.parseObject(donoSebo, DonoSeboDto.class);
        return ResponseEntity.ok().body(donoSeboDto);
    }

    @PostMapping
    public ResponseEntity<DonoSeboDto> insert(@RequestBody DonoSebo donoSebo) {
        donoSebo = service.insert(donoSebo);
        DonoSeboDto donoSeboDto = Mapper.parseObject(donoSebo, DonoSeboDto.class);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(donoSeboDto.getId()).toUri();
        return ResponseEntity.created(uri).body(donoSeboDto);
    }

    @PostMapping(value = "/{id}/sebo")
    public ResponseEntity<?> insertSebo(@RequestBody Sebo sebo, @PathVariable Long id) {
        DonoSebo donoSebo = service.findById(id);
        sebo.setDonoSebo(donoSebo);
        sebo = seboService.insert(sebo);
        donoSebo.setSebo(sebo);
        DonoSeboDto donoSeboDto = Mapper.parseObject(donoSebo, DonoSeboDto.class);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(donoSeboDto.getId()).toUri();
        return ResponseEntity.created(uri).body(donoSeboDto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<DonoSeboDto> update(@PathVariable Long id, @RequestBody DonoSebo donoSebo) {
        donoSebo = service.update(id, donoSebo);
        DonoSeboDto donoSeboDto = Mapper.parseObject(donoSebo, DonoSeboDto.class);
        return ResponseEntity.ok().body(donoSeboDto);
    }
}