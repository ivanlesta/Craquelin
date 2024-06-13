package udaw2.proyecto.ilcProy.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import udaw2.proyecto.ilcProy.domain.LineaDePedido;
import udaw2.proyecto.ilcProy.domain.Pedido;

public interface LineaDePedidoRepository extends JpaRepository<LineaDePedido, Long> {
    List<LineaDePedido> findByPedido(Pedido pedido);
}