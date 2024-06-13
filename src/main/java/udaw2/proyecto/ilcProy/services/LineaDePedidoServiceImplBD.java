package udaw2.proyecto.ilcProy.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import udaw2.proyecto.ilcProy.domain.Cliente;
import udaw2.proyecto.ilcProy.domain.EstadoPedido;
import udaw2.proyecto.ilcProy.domain.LineaDePedido;
import udaw2.proyecto.ilcProy.domain.Pedido;
import udaw2.proyecto.ilcProy.domain.Producto;
import udaw2.proyecto.ilcProy.repositories.LineaDePedidoRepository;
import udaw2.proyecto.ilcProy.repositories.PedidoRepository;

@Service
public class LineaDePedidoServiceImplBD implements LineaDePedidoService {

    @Autowired
    private LineaDePedidoRepository lineaDePedidoRepository;

    @Autowired
    private PedidoService pedidoService;

    @Autowired
    ClienteService clienteService;

    public LineaDePedido añadir(LineaDePedido lineaDePedido) {
        return lineaDePedidoRepository.save(lineaDePedido);
    }

    @Override
    public void añadirLineaDePedido(Long cantidad, Producto producto) {
        LineaDePedido lineaDePedido = new LineaDePedido();
        lineaDePedido.setCantidadProducto(cantidad);
        lineaDePedido.setProducto(producto);

        Cliente comprador = clienteService.obtenerClienteConectado();
        List<Pedido> pendientes = pedidoService.obtenerPedidosConEstadoCarritoPorComprador(comprador);

        if (pendientes.isEmpty()) {
            Pedido nuevoPedido = new Pedido();
            nuevoPedido.setComprador(comprador);
            nuevoPedido.setFechaPedido(LocalDateTime.now());
            pedidoService.añadir(nuevoPedido);
            lineaDePedido.setPedido(nuevoPedido);
            añadir(lineaDePedido);
            nuevoPedido.setLineasDePedido(Arrays.asList(lineaDePedido));
            nuevoPedido.setUnidadesTotales(cantidad);
            nuevoPedido.setPrecioTotal(producto.getPrecioProducto() * cantidad);
            pedidoService.editar(nuevoPedido);
            
        } else {
            Pedido pedidoActivo = pendientes.get(0);
            pedidoActivo.getLineasDePedido().add(lineaDePedido);
            pedidoActivo.setUnidadesTotales(sumarLineasDePedido(pedidoActivo.getLineasDePedido()));
            pedidoService.editar(pedidoActivo);
            lineaDePedido.setPedido(pedidoActivo);
            añadir(lineaDePedido);
        }

    }

    private Long sumarLineasDePedido(List<LineaDePedido> lineasDePedidos) {
        Long cantidadesTotales = 0L;
        for (LineaDePedido lineaDePedido : lineasDePedidos) {
            cantidadesTotales += lineaDePedido.getCantidadProducto();
        }
        return cantidadesTotales;
    }

    @Transactional
    @Override
    public void quitarLineaDePedido(Long lineaId, Pedido pedido) {
        LineaDePedido lineaDePedido = lineaDePedidoRepository.findById(lineaId).orElse(null);
        if (lineaDePedido != null && lineaDePedido.getPedido().equals(pedido)) {
            pedido.setUnidadesTotales(pedido.getUnidadesTotales() - lineaDePedido.getCantidadProducto());
            pedido.setPrecioTotal(pedido.getPrecioTotal()
                    - (lineaDePedido.getCantidadProducto() * lineaDePedido.getProducto().getPrecioProducto()));

            lineaDePedidoRepository.delete(lineaDePedido);

            System.out.println("Línea de pedido eliminada: " + lineaDePedido);
        } else {
            System.out.println("No se encontró la línea de pedido o no coincide con el pedido.");
        }
    }

    @Override
    public void quitarTodasLasLineasDePedido(Pedido pedido) {
        List<LineaDePedido> lineasDelPedido = lineaDePedidoRepository.findByPedido(pedido);
        lineaDePedidoRepository.deleteAll(lineasDelPedido);
    }
}
