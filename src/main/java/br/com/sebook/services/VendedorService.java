package br.com.sebook.services;

import br.com.sebook.entities.Vendedor;
import br.com.sebook.repositories.VendedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.sebook.services.exceptions.ResourceNotFoundException;

import java.util.List;
import java.util.Optional;

@Service
public class VendedorService {
    @Autowired
    private VendedorRepository vendedorRepository;

    public List<Vendedor> findAll() {
        return vendedorRepository.findAll();
    }

    public Vendedor findById(Long id) {
        Optional<Vendedor> vendedor = vendedorRepository.findById(id);
        return vendedor.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public Vendedor insert(Vendedor vendedor) {
        return vendedorRepository.save(vendedor);
    }

    public void delete(Long id) {
        vendedorRepository.deleteById(id);
    }

    public Vendedor update(Long id, Vendedor obj) {
        Vendedor vendedor = vendedorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id));
        updateData(vendedor, obj);
        return vendedorRepository.save(vendedor);
    }

    private void updateData(Vendedor vendedor, Vendedor obj) {
        if (obj.getNome() != null) {
            vendedor.setNome(obj.getNome());
        }
        if (obj.getContato() != null) {
            vendedor.setContato(obj.getContato());
        }
        if (obj.getEmail() != null) {
            vendedor.setEmail(obj.getEmail());
        }
        if (obj.getSenha() != null) {
            vendedor.setSenha(obj.getSenha());
        }
    }
}
