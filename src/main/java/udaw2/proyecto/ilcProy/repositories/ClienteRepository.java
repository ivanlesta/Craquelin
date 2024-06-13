package udaw2.proyecto.ilcProy.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import udaw2.proyecto.ilcProy.domain.Cliente;


public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    Cliente findBynombreCliente(String nombreCliente);
    Cliente findByEmail(String email);
}
