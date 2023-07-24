package br.com.sebook.services;

import br.com.sebook.entities.Sebo;
import br.com.sebook.repositories.SeboRepository;
import br.com.sebook.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;

@Service
public class SeboService {
    @Autowired
    private SeboRepository seboRepository;

    public List<Sebo> findAll() {
        return seboRepository.findAll();
    }

    public List<Sebo> findBySebo(String nome) {
        return seboRepository.findBySebo(nome);
    }

    public List<Sebo> findBySeboBairro(String bairro) {
        return seboRepository.findBySeboBairro(bairro);
    }

    public Sebo findById(Long id) {
        Optional<Sebo> sebo = seboRepository.findById(id);
        return sebo.orElseThrow(() -> new ResourceNotFoundException("Sebo não encontrado."));
    }

    public Sebo insert(Sebo sebo) {
        String mensagem = "Olá, o livro (nome_do_livro_aqui) ainda está disponível?";
        String linkWhatsapp = "https://api.whatsapp.com/send?phone=" + sebo.getTelefone() +
                "&text=" + URLEncoder.encode(mensagem, StandardCharsets.UTF_8);
        sebo.setWhatsapp(linkWhatsapp);
        return seboRepository.save(sebo);
    }

    public void delete(Long id) {
        seboRepository.deleteById(id);
    }

    public Sebo update(Long id, Sebo obj) {
        Sebo sebo = seboRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Sebo não encontrado."));
        updateData(sebo, obj);
        return seboRepository.save(sebo);

    }

    private void updateData(Sebo sebo, Sebo obj) {
        if (obj.getNome() != null) {
            sebo.setNome(obj.getNome());
        }
        if (obj.getTelefone() != null) {
            sebo.setTelefone(obj.getTelefone());
            String mensagem = "Olá, o livro (nome_do_livro_aqui) ainda está disponível?";
            String linkWhatsapp = "https://api.whatsapp.com/send?phone=" + obj.getTelefone() +
                    "&text=" + URLEncoder.encode(mensagem, StandardCharsets.UTF_8);
            sebo.setWhatsapp(linkWhatsapp);
        }
        if (obj.getEndereco() != null) {
            sebo.setEndereco(obj.getEndereco());
        }
        if (obj.getBairro() != null) {
            sebo.setBairro(obj.getBairro());
        }
        if (obj.getCep() != null) {
            sebo.setCep(obj.getCep());
        }
    }
}