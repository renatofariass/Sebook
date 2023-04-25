package br.com.sebook.repositories;

import br.com.sebook.entities.Vendedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface VendedorRepository extends JpaRepository<Vendedor, Long> {
    @Query("SELECT v FROM Vendedor v WHERE lower(v.usernameVendedor) = lower(:usernameVendedor)")
    Vendedor findByUsernameVendedor(String usernameVendedor);
}
