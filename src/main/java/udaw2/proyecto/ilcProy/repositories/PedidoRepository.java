package udaw2.proyecto.ilcProy.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import udaw2.proyecto.ilcProy.domain.Cliente;
import udaw2.proyecto.ilcProy.domain.EstadoPedido;
import udaw2.proyecto.ilcProy.domain.Pedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    List<Pedido> findByComprador(Cliente comprador);

    List<Pedido> findByEstadoPedido(EstadoPedido estado);
    
    void deleteByCompradorIdCliente(Long idCliente);

}

