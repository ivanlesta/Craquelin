package udaw2.proyecto.ilcProy.services;

import udaw2.proyecto.ilcProy.domain.LineaDePedido;
import udaw2.proyecto.ilcProy.domain.Pedido;
import udaw2.proyecto.ilcProy.domain.Producto;

public interface LineaDePedidoService {
    LineaDePedido añadir(LineaDePedido lineaDePedido);
    void añadirLineaDePedido(Long cantidad, Producto producto);
    void quitarLineaDePedido(Long lineaId, Pedido pedido);
    void quitarTodasLasLineasDePedido(Pedido pedido);
}
