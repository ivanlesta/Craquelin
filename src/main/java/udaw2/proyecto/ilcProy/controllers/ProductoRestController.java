package udaw2.proyecto.ilcProy.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import udaw2.proyecto.ilcProy.domain.Producto;
import udaw2.proyecto.ilcProy.services.ProductoService;

@RestController
@RequestMapping("/api/")
public class ProductoRestController {
    @Autowired
    public ProductoService productoService;

    // GET todos
    @GetMapping({ "/productos" })
    public List<Producto> showList(Model model) {
        List<Producto> listaProductos = productoService.obtenerTodos();
        return listaProductos;
    }
}
