package udaw2.proyecto.ilcProy.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import udaw2.proyecto.ilcProy.domain.Categoria;
import udaw2.proyecto.ilcProy.domain.Cliente;
import udaw2.proyecto.ilcProy.domain.LineaDePedido;
import udaw2.proyecto.ilcProy.domain.Pedido;
import udaw2.proyecto.ilcProy.domain.Producto;
import udaw2.proyecto.ilcProy.repositories.CategoriaRepository;
import udaw2.proyecto.ilcProy.repositories.LineaDePedidoRepository;
import udaw2.proyecto.ilcProy.repositories.PedidoRepository;
import udaw2.proyecto.ilcProy.repositories.ProductoRepository;



@Service
public class ProductoServiceImplBD implements ProductoService {

    @Autowired
    ProductoRepository productoRepository;

    @Autowired
    PedidoRepository pedidoRepository;

    @Autowired
    LineaDePedidoRepository lineaDePedidoRepository;

    @Autowired
    CategoriaRepository categoriaRepository;

    @Autowired
    ClienteService clienteService;

    @Autowired
    @Lazy
    PedidoService pedidoService;

    public Producto añadir(Producto producto) {
        return productoRepository.save(producto);
        }
        
    public Producto editar(Producto producto) {
        return productoRepository.save(producto);
    }
    public List<Producto> obtenerTodos() {
        return productoRepository.findAll();
    }
    
    public Producto obtenerPorId(long id) {
        return productoRepository.findById(id).orElse(null); 
    }
    
    public Producto obtenerPorNombre(String nombre) {
        return productoRepository.findBynombreProducto(nombre);
    }   
    
    public List<Producto> obtenerPorCategoria(Long idCategoria) {
        Categoria categoria = categoriaRepository.findById(idCategoria).orElse(null);
        if (categoria != null)
        return productoRepository.findByCategoria(categoria);
        return null;
    }   
    
    public List<LineaDePedido> obtenerPorPedido(Pedido pedido) {
        return lineaDePedidoRepository.findByPedido(pedido);
    }
    
    public void borrar(Producto producto) {
        productoRepository.delete(producto);
    }

    public void borrarPorId(Long id) {
        productoRepository.deleteById(id);
    }  

}
