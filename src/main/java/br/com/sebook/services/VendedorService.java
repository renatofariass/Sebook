package br.com.sebook.services;

import br.com.sebook.entities.Vendedor;
import br.com.sebook.repositories.VendedorRepository;
import br.com.sebook.services.exceptions.NameNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VendedorService {
    @Autowired
    private VendedorRepository vendedorRepository;

    public List<Vendedor> findAll() {
        return vendedorRepository.findAll();
    }

    public Vendedor findByUsernameVendedor(String usernameVendedor) {
        Vendedor vendedor = vendedorRepository.findByUsernameVendedor(usernameVendedor);
        if(vendedor == null) {
            throw new NameNotFoundException(usernameVendedor);
        }
        return vendedor;
    }

    public Vendedor insert(Vendedor vendedor) {
        return vendedorRepository.save(vendedor);
    }

    public void delete(Long id) {
        vendedorRepository.deleteById(id);
    }

    public Vendedor update(String nome, Vendedor obj) {
        Vendedor vendedor = vendedorRepository.findByUsernameVendedor(nome);
        if (vendedor == null) {
            throw new NameNotFoundException(nome);
        }
        updateData(vendedor, obj);
        return vendedorRepository.save(vendedor);
    }

    private void updateData(Vendedor vendedor, Vendedor obj) {
        if (obj.getUsernameVendedor() != null) {
            vendedor.setUsernameVendedor(obj.getUsernameVendedor());
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
