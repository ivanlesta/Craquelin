package udaw2.proyecto.ilcProy.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import udaw2.proyecto.ilcProy.domain.Cliente;
import udaw2.proyecto.ilcProy.domain.Pedido;
import udaw2.proyecto.ilcProy.domain.Producto;
import udaw2.proyecto.ilcProy.services.ClienteService;
import udaw2.proyecto.ilcProy.services.LineaDePedidoService;
import udaw2.proyecto.ilcProy.services.PedidoService;
import udaw2.proyecto.ilcProy.services.ProductoService;

@Controller
@RequestMapping("/lineaDePedido")
public class LineaDePedidoController {

    @Autowired
    private ProductoService productoService;

    @Autowired
    private LineaDePedidoService lineaPedidoService;


    @PostMapping("/add/{idProducto}/{cantidad}/{idCategoria}")
    public String addProductoToPedido(@PathVariable Long idProducto, @PathVariable Long cantidad,
            @PathVariable Long idCategoria) {

        Producto productoActivo = productoService.obtenerPorId(idProducto);

        lineaPedidoService.añadirLineaDePedido(cantidad, productoActivo);

        return "redirect:/productos/categoria/" + idCategoria;
    }
}
