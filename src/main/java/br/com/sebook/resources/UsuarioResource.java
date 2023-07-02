package br.com.sebook.resources;

import br.com.sebook.entities.Usuario;
import br.com.sebook.entities.dto.usuario.DadosUsuario;
import br.com.sebook.entities.dto.usuario.UsuarioDto;
import br.com.sebook.mapper.Mapper;
import br.com.sebook.services.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/usuarios")
public class UsuarioResource {
    @Autowired
    UsuarioService service;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @PostMapping("/registro")
    public ResponseEntity<String> registrarUsuario(@RequestBody @Valid DadosUsuario dadosUsuario) {
        var usuario = new Usuario(dadosUsuario.login(), dadosUsuario.senha());
        usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
        service.insert(usuario);
        return ResponseEntity.ok().body("Usuário registrado com sucesso!");
    }

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

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}