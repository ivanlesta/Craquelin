package udaw2.proyecto.ilcProy.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;
import udaw2.proyecto.ilcProy.domain.Categoria;
import udaw2.proyecto.ilcProy.domain.Producto;
import udaw2.proyecto.ilcProy.services.CategoriaService;
import udaw2.proyecto.ilcProy.services.ProductoService;

@Controller
@RequestMapping("/categorias")
public class CategoriaController {

    @Autowired
    public CategoriaService categoriaService;

    @Autowired
    public ProductoService productoService;

    @GetMapping({ "/list" })
    public String showList(Model model) {
        model.addAttribute("listaCategorias", categoriaService.obtenerTodos());
        return "/categorias/categoriesView";
    }

    @GetMapping("/list/{idCategoria}")
    public String showCategory(@PathVariable("idCategoria") Long idCategoria, Model model) {
        List<Producto> productos = productoService.obtenerPorCategoria(idCategoria);
        model.addAttribute("listaProductos", productos);
        return "/productos/productsView";
    }

    @GetMapping("/new")
    public String showNew(Model model) {
        model.addAttribute("categoryForm", new Categoria());
        return "/categorias/newCategoryView";
    }

    @PostMapping("/new/submit")
    public String showNewSubmit(@Valid Categoria categoryForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "redirect:/categorias/new";
        }
        String nombreCategoria = categoryForm.getNombreCategoria();
        String nombreCarpeta = nombreCategoria.toLowerCase();

        categoryForm.setNombreCategoria(nombreCategoria);
        categoriaService.añadir(categoryForm);

        Path directorioCategoria = Paths.get("src/main/resources/static/images/productos/" + nombreCarpeta);
        if (!Files.exists(directorioCategoria)) {
            try {
                Files.createDirectory(directorioCategoria);
            } catch (IOException e) {
                e.printStackTrace();
                return "redirect:/categorias/new";
            }
        }
        return "redirect:/categorias/list";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable long id, Model model) {
        Categoria categoria;
        categoria = categoriaService.obtenerPorId(id);

        if (categoria != null)
            model.addAttribute("categoryForm", categoria);
        model.addAttribute("listaCategorias", categoriaService.obtenerTodos());
        return "/categorias/editCategoryView";
    }

    @PostMapping("/edit/submit")
    public String showEditSubmit(
            @Valid Categoria categoryForm,
            BindingResult bindingResult) {
        categoriaService.editar(categoryForm);
        return "redirect:/categorias/list";
    }

    @GetMapping("/delete/{id}")
    public String showDelete(@PathVariable long id) {
        if (productoService.obtenerPorCategoria(id).size() == 0)
            categoriaService.borrarPorId(id);
        return "redirect:/categorias/list";
    }
}
