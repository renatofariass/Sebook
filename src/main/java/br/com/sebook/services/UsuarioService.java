package br.com.sebook.services;

import br.com.sebook.entities.Usuario;
import br.com.sebook.repositories.UsuarioRepository;
import br.com.sebook.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    public Usuario findById(Long id) {
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        return usuario.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public Usuario insert(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public void delete(Long id) {
        usuarioRepository.deleteById(id);
    }

    public Usuario update(Long id, Usuario obj) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id));
        updateData(usuario, obj);
        return usuarioRepository.save(usuario);

    }

    private void updateData(Usuario usuario, Usuario obj) {
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
    }
}
