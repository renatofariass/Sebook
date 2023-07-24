package br.com.sebook.services;

import br.com.sebook.entities.DonoSebo;
import br.com.sebook.entities.Usuario;
import br.com.sebook.repositories.DonoSeboRepository;
import br.com.sebook.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;

@Service
public class DonoSeboService {
    @Autowired
    private DonoSeboRepository donoSeboRepository;

    public List<DonoSebo> findAll() {
        return donoSeboRepository.findAll();
    }

    public DonoSebo findById(Long id) {
        Optional<DonoSebo> donoSebo = donoSeboRepository.findById(id);
        return donoSebo.orElseThrow(() -> new ResourceNotFoundException("Dono de sebo não encontrado"));
    }

    public DonoSebo insert(DonoSebo donoSebo) {
        return donoSeboRepository.save(donoSebo);
    }

    public void delete(Long id) {
        donoSeboRepository.deleteById(id);
    }

    public DonoSebo update(Long id, DonoSebo obj) {
        DonoSebo donoSebo = donoSeboRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Dono de sebo não encontrado."));
        updateData(donoSebo, obj);
        return donoSeboRepository.save(donoSebo);

    }

    private void updateData(DonoSebo donoSebo, DonoSebo obj) {
        if (obj.getEmail() != null) {
            donoSebo.setEmail(obj.getEmail());
        }
        if (obj.getSenha() != null) {
            donoSebo.setSenha(obj.getSenha());
        }
    }
}
