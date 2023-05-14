package br.com.sebook.resources;

import br.com.sebook.entities.Usuario;
import br.com.sebook.entities.dto.usuario.UsuarioDto;
import br.com.sebook.mapper.Mapper;
import br.com.sebook.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/usuarios")
public class UsuarioResource {
    @Autowired
    UsuarioService service;

    @GetMapping
    public ResponseEntity<List<UsuarioDto>> findAll() {
        List<Usuario> lista = service.findAll();
        List<UsuarioDto> usuarioDto = Mapper.parseListObjects(lista, UsuarioDto.class);
        return ResponseEntity.ok().body(usuarioDto);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<UsuarioDto> findById(@PathVariable Long id)  {
        Usuario usuario = service.findById(id);
        UsuarioDto usuarioDto = Mapper.parseObject(usuario, UsuarioDto.class);
        return ResponseEntity.ok().body(usuarioDto);
    }

    @PostMapping
    public ResponseEntity<UsuarioDto> insert(@RequestBody Usuario usuario) {
        usuario = service.insert(usuario);
        UsuarioDto usuarioDto = Mapper.parseObject(usuario, UsuarioDto.class);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(usuarioDto.getId()).toUri();
        return ResponseEntity.created(uri).body(usuarioDto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<UsuarioDto> update(@PathVariable Long id, @RequestBody Usuario usuario) {
        usuario = service.update(id, usuario);
        UsuarioDto usuarioDto = Mapper.parseObject(usuario, UsuarioDto.class);
        return ResponseEntity.ok().body(usuarioDto);
    }
}