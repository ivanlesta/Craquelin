package udaw2.proyecto.ilcProy.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;
import udaw2.proyecto.ilcProy.domain.Cliente;
import udaw2.proyecto.ilcProy.domain.FormPass;
import udaw2.proyecto.ilcProy.services.ClienteService;

@Controller
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    public ClienteService clienteService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping({ "/list" })
    public String showList(Model model) {
        model.addAttribute("listaClientes", clienteService.obtenerTodos());
        return "clientes/clientesView";
    }

    @GetMapping("/new")
    public String showNew(Model model) {
        model.addAttribute("clienteForm", new Cliente());
        return "clientes/newClientesView";
    }

    @PostMapping("/new/submit")
    public String showNewSubmit(@Valid Cliente clienteForm,
            RedirectAttributes redirectAttributes) {
        // Verificar si ya existe un cliente con el mismo email.
        Cliente clienteExiste = clienteService.obtenerPorEmail(clienteForm.getEmail());
        if (clienteExiste != null) {
            // Si el email está duplicado, agregar un mensaje de error al.
            redirectAttributes.addFlashAttribute("errorMessage",
                    "Ya existe un socio registrado con este email.");
            return "redirect:/clientes/new";
        }

        // Guardar el nuevo cliente.
        clienteService.añadir(clienteForm);
        // Agregar un mensaje de éxito para mostrar en la página de inicio de sesión.
        redirectAttributes.addFlashAttribute("successMessage",
                "Te has registrado con éxito. Por favor, inicia sesión.");
        return "redirect:/public/signin";
    }

    @GetMapping("/perfil")
    public String showProfile(Model model) {
        Cliente cliente = clienteService.obtenerClienteConectado();
        if (cliente != null) {
            model.addAttribute("cliente", cliente);
        } else {
            System.out.println("Cliente no autenticado o no encontrado.");
        }
        System.out.println(cliente);
        return "clientes/clientePerfil";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable long id, Model model) {
        Cliente cliente = clienteService.obtenerPorId(id);
        if (cliente != null) {
            model.addAttribute("clienteForm", cliente);
        } else {
            return "redirect:/clientes/list";
        }
        return "clientes/editClientesView";
    }

    @PostMapping("/edit/submit")
    public String showEditSubmit(
            @Valid @ModelAttribute("clienteForm") Cliente clienteForm,
            BindingResult bindingResult,
            Model model) {
        System.out.println("Iniciando el proceso de edición del cliente.");

        if (bindingResult.hasErrors()) {
            System.out.println("Errores de validación encontrados:");
            for (FieldError error : bindingResult.getFieldErrors()) {
                System.out.println(error.getField() + ": " + error.getDefaultMessage());
            }
            return "clientes/editClientesView";
        }

        try {
            System.out.println("Editando cliente: " + clienteForm.getIdCliente());
            System.out.println("Nombre: " + clienteForm.getNombreCliente());
            System.out.println("Contraseña: " + clienteForm.getContraseña());

            clienteService.editar(clienteForm);

            System.out.println("Cliente editado exitosamente.");
        } catch (DataIntegrityViolationException e) {
            bindingResult.rejectValue("email", "error.clienteForm", "El email ya está en uso.");
            return "clientes/editClientesView";
        }
        return "redirect:/clientes/perfil";
    }

    @GetMapping("/delete/{id}")
    public String showDelete(@PathVariable long id) {
        clienteService.borrarPorId(id);
        return "redirect:/clientes/list";
    }

    @GetMapping("/cambiarContraseña")
    public String showChangePasswordForm(Model model) {
        model.addAttribute("formPass", new FormPass());
        return "clientes/cambiarPass";
    }

    @PostMapping("/cambiarContraseña/submit")
    public String changePassword(@Valid @ModelAttribute("formPass") FormPass formPass,
            BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "redirect:/clientes/cambiarContraseña";
        }

        String nuevaContraseña = formPass.getNuevaContraseña();
        String confirmaContraseña = formPass.getConfirmaContraseña();

        // Verificar si las contraseñas coinciden
        if (!nuevaContraseña.equals(confirmaContraseña)) {
            model.addAttribute("errorMessage", "Las contraseñas no coinciden");
            return "clientes/cambiarPass";
        }

        // Obtener el cliente conectado
        Cliente cliente = clienteService.obtenerClienteConectado();
        if (cliente == null) {
            model.addAttribute("errorMessage", "No se pudo obtener el cliente conectado");
            return "clientes/cambiarPass";
        }

        // Encriptar la nueva contraseña
        String nuevaContraseñaEncriptada = passwordEncoder.encode(nuevaContraseña);

        // Verificar si la nueva contraseña es igual a la contraseña actual
        if (passwordEncoder.matches(nuevaContraseña, cliente.getContraseña())) {
            model.addAttribute("errorMessage", "La nueva contraseña es igual a la contraseña actual");
            return "clientes/cambiarPass";
        }

        // Actualizar la contraseña del cliente con la nueva contraseña encriptada
        cliente.setContraseña(nuevaContraseñaEncriptada);
        clienteService.editar(cliente);

        // Redirigir al perfil del cliente con un mensaje de éxito
        model.addAttribute("successMessage", "Contraseña cambiada exitosamente");
        return "redirect:/clientes/perfil";
    }

}
