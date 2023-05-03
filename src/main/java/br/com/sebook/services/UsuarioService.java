package br.com.sebook.services;

import br.com.sebook.dto.UsuarioDTO;
import br.com.sebook.entities.Livro;
import br.com.sebook.entities.Usuario;
import br.com.sebook.mapper.Mapper;
import br.com.sebook.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.sebook.services.exceptions.ResourceNotFoundException;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<UsuarioDTO> findAll() {
        return Mapper.parseListObjects(usuarioRepository.findAll(), UsuarioDTO.class);
    }

    public UsuarioDTO findById(Long id) {
        var entity = usuarioRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
        return Mapper.parseObject(entity, UsuarioDTO.class);
    }

    public UsuarioDTO insert(UsuarioDTO usuario) {
        var entity = Mapper.parseObject(usuario, Usuario.class);
        return Mapper.parseObject(usuarioRepository.save(entity), UsuarioDTO.class);
    }

    public void delete(Long id) {
        usuarioRepository.deleteById(id);
    }

    public UsuarioDTO update(Long id, UsuarioDTO obj) {
        var usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id));
        if (obj.getNome() != null) {
            usuario.setNome(obj.getNome());
        }
        if (obj.getContato() != null) {
            usuario.setContato(obj.getContato());
        }
        if (obj.getEmail() != null) {
            usuario.setEmail(obj.getEmail());
        }
        if (obj.getSenha() != null) {
            usuario.setSenha(obj.getSenha());
        }
        return Mapper.parseObject(usuarioRepository.save(usuario), UsuarioDTO.class);
    }
}
