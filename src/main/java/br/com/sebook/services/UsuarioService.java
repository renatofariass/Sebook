package br.com.sebook.services;

import br.com.sebook.entities.Usuario;
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

    public Usuario update(Long id, Usuario usuario) {
        Usuario entity = usuarioRepository.getReferenceById(id);
        updateData(entity, usuario);
        return usuarioRepository.save(usuario);

    }

    private void updateData(Usuario entity, Usuario usuario) {
        entity.setNome(usuario.getNome());
        entity.setContato(usuario.getContato());
        entity.setEmail(usuario.getEmail());
        entity.setSenha(usuario.getSenha());
    }
}
