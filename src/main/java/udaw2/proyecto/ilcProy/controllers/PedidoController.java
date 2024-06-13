package udaw2.proyecto.ilcProy.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;
import udaw2.proyecto.ilcProy.domain.Cliente;
import udaw2.proyecto.ilcProy.domain.EstadoPedido;
import udaw2.proyecto.ilcProy.domain.FormInfo;
import udaw2.proyecto.ilcProy.domain.LineaDePedido;
import udaw2.proyecto.ilcProy.domain.Pedido;
import udaw2.proyecto.ilcProy.services.ClienteService;
import udaw2.proyecto.ilcProy.services.LineaDePedidoService;
import udaw2.proyecto.ilcProy.services.PedidoService;
import udaw2.proyecto.ilcProy.services.ProductoService;

@Controller
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    public PedidoService pedidoService;

    @Autowired
    public ClienteService clienteService;

    @Autowired
    public ProductoService productoService;

    @Autowired
    public LineaDePedidoService lineaDePedidoService;

    @GetMapping("/cliente/{id}")
    public String showPedidosByUsers(@PathVariable long id, Model model) {
        Cliente cliente = clienteService.obtenerPorId(id);
        model.addAttribute("listaPedidos", pedidoService.obtenerPorComprador(cliente));
        model.addAttribute("cliente", cliente);
        return "pedidos/clienteListView";
    }

    @GetMapping("/list")
    public String showAllOrders(Model model) {
        model.addAttribute("listaPedidos", pedidoService.obtenerTodos());
        return "pedidos/pedidoListView";
    }

    @GetMapping("/delete/{id}")
    public String showDeletePed(@PathVariable long id) {
        pedidoService.borrar(pedidoService.obtenerPorId(id));
        return "redirect:/pedidos/list";
    }

    @GetMapping("/pedidoPendiente")
    public String showCurrentOrder(Model model) {
        Cliente cliente = clienteService.obtenerClienteConectado();
        Pedido carrito = pedidoService.verPedidoPendiente(cliente);

        double precioTotal = 0;
        long unidadesTotales = 0;

        if (carrito == null)
            return "pedidos/carritoVacioView";

        if (carrito != null) {
            for (LineaDePedido linea : carrito.getLineasDePedido()) {
                precioTotal += linea.getProducto().getPrecioProducto() * linea.getCantidadProducto();
                unidadesTotales += linea.getCantidadProducto();
            }
            carrito.setPrecioTotal(precioTotal);
            carrito.setUnidadesTotales(unidadesTotales);
        }

        System.out.println("Carrito obtenido: " + carrito);
        if (carrito != null) {
            System.out.println("Líneas de pedido en el carrito: " + carrito.getLineasDePedido());
        }
        List<LineaDePedido> productosCarrito = carrito != null ? carrito.getLineasDePedido() : new ArrayList<>();
        model.addAttribute("pedido", carrito);
        model.addAttribute("listaProductos", productosCarrito);
        model.addAttribute("numeroItems", pedidoService.obtenerNumeroItemsEnCarrito(cliente));
        return "pedidos/pedidoPendienteView";
    }

    @PostMapping("/pedidoPendiente/submit")
    public String showCurrentOrderSubmit(@ModelAttribute("pedido") Pedido carrito, BindingResult bindingResult,
            RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "pedidos/pedidoRealizado";
        }

        System.out.println(carrito);
        pedidoService.confirmarPedido(carrito);
        return "pedidos/pedidoRealizado";
    }

    @GetMapping("/pedidoPendiente/actualizar")
    public String actualizarPedidoPendiente(Model model) {
        Cliente cliente = clienteService.obtenerClienteConectado();
        
        Long numeroItemsEnCarrito = pedidoService.obtenerNumeroItemsEnCarrito(cliente);
        model.addAttribute("numeroItems", numeroItemsEnCarrito);
        return "pedidos/actualizarPedidoPendiente";
    }

    @PostMapping("/pedidoPendiente/eliminarLinea/{idLinea}")
    public String eliminarLineaDelCarrito(@PathVariable Long idLinea,
            RedirectAttributes redirectAttributes) {
        System.out.println("Eliminando linea del carrito. ID de línea: " + idLinea);
        Cliente cliente = clienteService.obtenerClienteConectado();
        Pedido pedido = pedidoService.verPedidoPendiente(cliente);

        lineaDePedidoService.quitarLineaDePedido(idLinea, pedido);
        pedido = pedidoService.verPedidoPendiente(cliente);

        return "redirect:/pedidos/pedidoPendiente";
    }

    @PostMapping("/pedidoPendiente/vaciarCarrito")
    public String vaciarCarrito(RedirectAttributes redirectAttributes, Model model) {
        Cliente cliente = clienteService.obtenerClienteConectado();
        Pedido pedido = pedidoService.verPedidoPendiente(cliente);

        if (pedido != null) {
            lineaDePedidoService.quitarTodasLasLineasDePedido(pedido);
        }

        return "redirect:/pedidos/pedidoPendiente/actualizar";
    }

    @GetMapping("/detalle/{id}")
    public String showOrderDetails(@PathVariable long id, Model model) {
        Pedido pedido = pedidoService.obtenerPorId(id);
        List<LineaDePedido> productos = productoService.obtenerPorPedido(pedido);
        model.addAttribute("pedido", pedido);
        model.addAttribute("listaProductos", productos);
        return "pedidos/detallesPedido";
    }

    @GetMapping("/cambiarEstado/{id}")
    public String changeOrderState(@PathVariable long id, Model model) {
        model.addAttribute("listaPedidos", pedidoService.obtenerTodos());

        pedidoService.cambiarEstado(id);

        return "/pedidos/pedidoListView";
    }

    @GetMapping("/anularPedido/{id}")
    public String denyOrder(@PathVariable long id, Model model) {
        model.addAttribute("listaPedidos", pedidoService.obtenerTodos());

        Pedido pedido = pedidoService.obtenerPorId(id);

        pedidoService.anularPedido(pedido);

        return "/pedidos/pedidoListView";
    }

    @PostMapping("/contacto/submit")
    public String showMyformSubmit(@Valid @ModelAttribute FormInfo formInfo, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "redirect:/public/contacto";
        }
        String motivoElegido = switch (formInfo.getMotivo()) {
            case 1 -> "Queja";
            case 2 -> "Consulta";
            case 3 -> "Otros";
            default -> "Otros";
        };
        model.addAttribute("motivoElegido", motivoElegido);
        return "/public/formSubmitView";
    }
}
