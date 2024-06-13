package udaw2.proyecto.ilcProy.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import udaw2.proyecto.ilcProy.domain.Cliente;
import udaw2.proyecto.ilcProy.domain.EstadoPedido;
import udaw2.proyecto.ilcProy.domain.Pedido;
import udaw2.proyecto.ilcProy.repositories.PedidoRepository;

@Service
public class PedidoServiceImplBD implements PedidoService {
    @Autowired
    PedidoRepository repositorio;

    @Autowired
    @Lazy
    ProductoService productoService;

    public Pedido añadir(Pedido pedido) {
        pedido.setEstadoPedido(EstadoPedido.CARRITO);
        return repositorio.save(pedido);
    }

    public Pedido editar(Pedido pedido) {
        return repositorio.save(pedido);
    }

    public List<Pedido> obtenerTodos() {
        return repositorio.findAll();
    }

    public Pedido obtenerPorId(long id) {
        return repositorio.findById(id).orElse(null);
    }

    public void borrar(Pedido pedido) {
        repositorio.delete(pedido);
    }

    public void borrarPorId(Long id) {
        repositorio.deleteById(id);
    }

    public List<Pedido> obtenerPorComprador(Cliente comprador) {
        return repositorio.findByComprador(comprador);
    }

    public List<Pedido> obtenerPorCarrito(EstadoPedido CARRITO) {
        return repositorio.findByEstadoPedido(CARRITO);
    }

    public List<Pedido> obtenerPorConfirmado(EstadoPedido CONFIRMADO) {
        return repositorio.findByEstadoPedido(CONFIRMADO);
    }

    public List<Pedido> obtenerPorElaborandose(EstadoPedido ELABORANDOSE) {
        return repositorio.findByEstadoPedido(ELABORANDOSE);
    }

    public List<Pedido> obtenerPorPreparado(EstadoPedido PREPARADO) {
        return repositorio.findByEstadoPedido(PREPARADO);
    }

    public List<Pedido> obtenerPorAnulado(EstadoPedido ANULADO) {
        return repositorio.findByEstadoPedido(ANULADO);
    }

    public List<Pedido> obtenerPorFinalizado(EstadoPedido FINALIZADO) {
        return repositorio.findByEstadoPedido(FINALIZADO);
    }

    public List<Pedido> obtenerPedidosConEstadoCarritoPorComprador(Cliente comprador) {
        List<Pedido> listaPedidos = obtenerPorComprador(comprador);
        
        List<Pedido> pendientes = listaPedidos.stream().filter(pedPendiente -> pedPendiente.getEstadoPedido() == EstadoPedido.CARRITO).toList();

        return pendientes;
    }

    public Pedido verPedidoPendiente(Cliente comprador) {
        List<Pedido> pendientes = obtenerPedidosConEstadoCarritoPorComprador(comprador);   
        if(pendientes.isEmpty())
            return null;
        return pendientes.get(0);
    }

    public void cambiarEstado(Long idPedido) {
        Pedido pedido = obtenerPorId(idPedido);
        switch (pedido.getEstadoPedido()) {
            case CONFIRMADO:
                pedido.setEstadoPedido(EstadoPedido.ELABORANDOSE);
                editar(pedido);
                break;

            case ELABORANDOSE:
                pedido.setEstadoPedido(EstadoPedido.FINALIZADO);
                editar(pedido);
                break;

            default:
                break;
        }
    }

    public void confirmarPedido(Pedido pedido) {
        pedido.setFechaPedido(LocalDateTime.now());
        pedido.setEstadoPedido(EstadoPedido.CONFIRMADO);
        editar(pedido);
    }

    public void finalizarPedido(Pedido pedido) {
        pedido.setFechaPedido(LocalDateTime.now());
        pedido.setEstadoPedido(EstadoPedido.FINALIZADO);
        editar(pedido);
    }

    public void anularPedido(Pedido pedido) {
        pedido.setFechaPedido(LocalDateTime.now());
        pedido.setEstadoPedido(EstadoPedido.ANULADO);
        editar(pedido);
    }

    public long obtenerNumeroItemsEnCarrito(Cliente comprador) {
        Pedido carrito = verPedidoPendiente(comprador);
        if (carrito != null) {
            return carrito.getLineasDePedido().size();
        } else {
            return 0;
        }
    }
}
