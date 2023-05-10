package br.com.sebook.resources;

import br.com.sebook.entities.Usuario;
import br.com.sebook.entities.dto.UsuarioMinDto;
import br.com.sebook.mapper.Mapper;
import br.com.sebook.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

@RestController
@RequestMapping(value = "/usuarios")
public class UsuarioResource {
    @Autowired
    UsuarioService service;

    @GetMapping
    public ResponseEntity<List<UsuarioMinDto>> findAll() {
        List<Usuario> lista = service.findAll();
        List<UsuarioMinDto> usuarioMinDtoList = Mapper.parseListObjects(lista, UsuarioMinDto.class);
        return ResponseEntity.ok().body(usuarioMinDtoList);
    }

    @GetMapping(value = "/{username}")
    public ResponseEntity<Usuario> findById(@PathVariable String username)  {
        Usuario usuario = service.findByUsuario(username);
        return ResponseEntity.ok().body(usuario);
    }

    @PostMapping
    public ResponseEntity<Usuario> insert(@RequestBody Usuario usuario) {
        String mensagem = "Olá, o livro (nome_do_livro_aqui) ainda está disponível?";
        String linkWhatsapp = "https://api.whatsapp.com/send?phone=" + usuario.getTelefone() +
                "&text=" + URLEncoder.encode(mensagem, StandardCharsets.UTF_8);
        usuario.setWhatsapp(linkWhatsapp);
        usuario = service.insert(usuario);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(usuario.getId()).toUri();
        return ResponseEntity.created(uri).body(usuario);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Usuario> update(@PathVariable Long id, @RequestBody Usuario usuario) {
        usuario = service.update(id, usuario);
        return ResponseEntity.ok().body(usuario);
    }
}