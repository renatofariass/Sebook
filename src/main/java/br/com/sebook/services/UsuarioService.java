package br.com.sebook.services;

import br.com.sebook.entities.Usuario;
import br.com.sebook.repositories.UsuarioRepository;
import br.com.sebook.services.exceptions.ResourceNotFoundException;
import br.com.sebook.services.exceptions.UsuarioNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    public Usuario findByUsuario(String username) {
        Usuario usuario = usuarioRepository.findByUsername(username);
        if(usuario == null) {
            throw new UsuarioNotFoundException(username);
        }
        return usuario;
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
        if (obj.getTelefone() != null) {
            usuario.setTelefone(obj.getTelefone());
            String mensagem = "Olá, o livro (nome_do_livro_aqui) ainda está disponível?";
            String linkWhatsapp = "https://api.whatsapp.com/send?phone=" + obj.getTelefone() +
                    "&text=" + URLEncoder.encode(mensagem, StandardCharsets.UTF_8);
            usuario.setWhatsapp(linkWhatsapp);
        }
        if (obj.getEmail() != null) {
            usuario.setEmail(obj.getEmail());
        }
        if (obj.getSenha() != null) {
            usuario.setSenha(obj.getSenha());
        }
    }
}
