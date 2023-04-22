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

    public Vendedor update(Long id, Vendedor vendedor) {
        Vendedor entity = vendedorRepository.getReferenceById(id);
        updateData(entity, vendedor);
        return vendedorRepository.save(vendedor);
    }

    private void updateData(Vendedor entity, Vendedor vendedor) {
        entity.setNome(vendedor.getNome());
        entity.setContato(vendedor.getContato());
        entity.setEmail(vendedor.getEmail());
        entity.setSenha(vendedor.getSenha());
    }
}
