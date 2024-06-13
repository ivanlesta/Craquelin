package udaw2.proyecto.ilcProy.services;

import java.util.List;

import udaw2.proyecto.ilcProy.domain.Pedido;
import udaw2.proyecto.ilcProy.domain.Cliente;
import udaw2.proyecto.ilcProy.domain.EstadoPedido;

public interface PedidoService {
    Pedido añadir(Pedido pedido);

    List<Pedido> obtenerTodos();

    Pedido obtenerPorId(long id);

    Pedido editar(Pedido pedido);

    void borrar(Pedido pedido);

    void borrarPorId(Long id);

    List<Pedido> obtenerPorComprador(Cliente comprador);
    
    List<Pedido> obtenerPorCarrito(EstadoPedido CARRITO);
    
    List<Pedido> obtenerPorConfirmado(EstadoPedido CONFIRMADO);
    
    List<Pedido> obtenerPorElaborandose(EstadoPedido ELABORANDOSE);
    
    List<Pedido> obtenerPorPreparado(EstadoPedido PREPARADO);
    
    List<Pedido> obtenerPorAnulado(EstadoPedido ANULADO);
    
    List<Pedido> obtenerPorFinalizado(EstadoPedido FINALIZADO);

    List<Pedido> obtenerPedidosConEstadoCarritoPorComprador(Cliente comprador);
    
    Pedido verPedidoPendiente(Cliente comprador);

    void cambiarEstado(Long idPedido);
    
    void confirmarPedido(Pedido pedido);

    void anularPedido(Pedido pedido);

    void finalizarPedido(Pedido pedido);

    long obtenerNumeroItemsEnCarrito(Cliente comprador);

}
