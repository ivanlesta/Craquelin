package udaw2.proyecto.ilcProy.services;

import java.util.List;

import udaw2.proyecto.ilcProy.domain.LineaDePedido;
import udaw2.proyecto.ilcProy.domain.Pedido;
import udaw2.proyecto.ilcProy.domain.Producto;

public interface ProductoService {

    Producto añadir(Producto producto);

    Producto editar(Producto producto);

    List<Producto> obtenerTodos();

    Producto obtenerPorId(long id);

    Producto obtenerPorNombre(String nombre);

    List<LineaDePedido> obtenerPorPedido(Pedido pedido);

    List<Producto> obtenerPorCategoria(Long idCategoria);

    void borrar(Producto producto);

    void borrarPorId(Long id);

}