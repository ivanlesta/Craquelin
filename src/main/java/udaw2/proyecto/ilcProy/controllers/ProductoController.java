package udaw2.proyecto.ilcProy.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.Valid;
import udaw2.proyecto.ilcProy.domain.Categoria;
import udaw2.proyecto.ilcProy.domain.Producto;
import udaw2.proyecto.ilcProy.services.CategoriaService;
import udaw2.proyecto.ilcProy.services.ClienteService;
import udaw2.proyecto.ilcProy.services.LineaDePedidoService;
import udaw2.proyecto.ilcProy.services.PedidoService;
import udaw2.proyecto.ilcProy.services.ProductoService;

@Controller
@RequestMapping("/productos")
public class ProductoController {

    @Autowired
    public ProductoService productoService;

    @Autowired
    public PedidoService pedidoService;

    @Autowired
    public ClienteService clienteService;

    @Autowired
    public CategoriaService categoriaService;

    @Autowired
    public LineaDePedidoService lineaDePedidoService;

    @GetMapping("/list")
    public String showList(Model model) {
        model.addAttribute("listaProductos", productoService.obtenerTodos());
        model.addAttribute("listaCategorias", categoriaService.obtenerTodos());
        return "productos/productsView";
    }

    @GetMapping("/categoria/{idCategoria}")
    public String showCategorias(@PathVariable long idCategoria, Model model) {
        List<Producto> listaProductos;
        if (idCategoria == 0) {
            listaProductos = productoService.obtenerTodos();
        } else {
            listaProductos = productoService.obtenerPorCategoria(idCategoria);
        }
        model.addAttribute("listaProductos", listaProductos);
        model.addAttribute("listaCategorias", categoriaService.obtenerTodos()); 
        model.addAttribute("categoriaSeleccionadaId", idCategoria);

        return "productos/productsView";
    }

    @GetMapping("/new")
    public String showNew(Model model) {
        model.addAttribute("productoForm", new Producto());
        model.addAttribute("listaCategorias", categoriaService.obtenerTodos());
        return "productos/newProductView";
    }

    @PostMapping("/new/submit")
    public String showNewSubmit(@ModelAttribute @Valid Producto productoForm,
            BindingResult bindingResult,
            @RequestParam("imagenProducto") MultipartFile imagenProducto,
            Model model) {
        if (bindingResult.hasErrors()) {
            System.out.println("Binding errors: " + bindingResult.getAllErrors());
            return "redirect:/productos/new";
        }
        String nombreCategoria = productoForm.getCategoria().getNombreCategoria();
        Categoria catSeleccionada = categoriaService.obtenerPorNombre(nombreCategoria);

        try {
            String nombreImagen = StringUtils.cleanPath(imagenProducto.getOriginalFilename());
            Path directorioBase = Paths.get("src/main/resources/static/images/productos/");
            Path directorioCategoria = directorioBase.resolve(productoForm.getCategoria().getNombreCategoria());

            System.out.println("Directorio de la categoría: " + directorioCategoria);

            if (!Files.exists(directorioCategoria)) {
                Files.createDirectories(directorioCategoria);
            }

            try (var inputStream = imagenProducto.getInputStream()) {
                Files.copy(inputStream, directorioCategoria.resolve(nombreImagen));
            }

            productoForm.setFoto("/images/productos/" + productoForm.getCategoria().getNombreCategoria() + "/" +
                    nombreImagen);
            System.out.println("Ruta de la imagen guardada: " + productoForm.getFoto());

        } catch (IOException e) {
            e.printStackTrace();
            model.addAttribute("error", "Error al cargar la imagen del producto.");
            return "redirect:/productos/new";
        }
        productoForm.setCategoria(catSeleccionada);
        productoService.añadir(productoForm);
        return "redirect:/productos/list";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable long id, Model model) {
        Producto producto;
        producto = productoService.obtenerPorId(id);
        
        if (producto != null) {
            model.addAttribute("productoForm", producto);
            model.addAttribute("listaCategorias", categoriaService.obtenerTodos());
        }
        return "productos/editProductView";
    }

    @PostMapping("/edit/submit")
    public String showEditSubmit(
            @Valid Producto productoForm,
            BindingResult bindingResult) {
        productoService.editar(productoForm);
        return "redirect:/productos/list";
    }

    @GetMapping("/delete/{id}")
    public String showDelete(@PathVariable long id) {
        System.out.println(id);
        productoService.borrarPorId(id);
        return "redirect:/productos/list";
    }

}
