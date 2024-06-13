package udaw2.proyecto.ilcProy.controllers;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import udaw2.proyecto.ilcProy.domain.Cliente;
import udaw2.proyecto.ilcProy.services.ClienteService;
import udaw2.proyecto.ilcProy.services.PedidoService;

@ControllerAdvice
public class GlobalControllerAdvice {
    private final PedidoService pedidoService;
    private final ClienteService clienteService;

    public GlobalControllerAdvice(PedidoService pedidoService, ClienteService clienteService) {
        this.pedidoService = pedidoService;
        this.clienteService = clienteService;
    }

    @ModelAttribute("numeroItems")
    public Long getNumeroItems() {
        Cliente cliente = clienteService.obtenerClienteConectado();
        if (cliente != null) {
            return pedidoService.obtenerNumeroItemsEnCarrito(cliente);
        } else {
            return 0L;
        }
    }
}

