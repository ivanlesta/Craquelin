package udaw2.proyecto.ilcProy.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import udaw2.proyecto.ilcProy.domain.Cliente;
import udaw2.proyecto.ilcProy.domain.Rol;
import udaw2.proyecto.ilcProy.repositories.ClienteRepository;
import udaw2.proyecto.ilcProy.repositories.PedidoRepository;

@Service
public class ClienteServiceImplBD implements ClienteService {
    @Autowired
    ClienteRepository clienteRepositorio
    ;
    @Autowired
    PedidoRepository pedidoRepositorio;

    @Autowired
    PasswordEncoder passwordEncoder;

    public Cliente añadir(Cliente cliente) {
        cliente.setFechaRegistro(LocalDateTime.now());
        String passCrypted = passwordEncoder.encode(cliente.getContraseña());
        cliente.setContraseña(passCrypted);
        if (cliente.getRol() == null)
            cliente.setRol(Rol.USER);

        Cliente clienteExistente = clienteRepositorio.findByEmail(cliente.getEmail());
        if (clienteExistente != null) {
            throw new RuntimeException("La dirección de correo electrónico ya está en uso");
        }

        try {
            Cliente clienteGuardado = clienteRepositorio.save(cliente);
            System.out.println("Contraseña encriptada para cliente " + clienteGuardado.getIdCliente() + ": "
                    + clienteGuardado.getContraseña());
            return clienteGuardado;
        } catch (DataIntegrityViolationException e) {
            e.printStackTrace();
            throw new RuntimeException("Error al guardar el cliente en la base de datos", e);
        }
    }

    public List<Cliente> obtenerTodos() {
        return clienteRepositorio.findAll();
    }

    public Cliente obtenerPorId(long id) {
        return clienteRepositorio.findById(id).orElse(null);
    }

    public Cliente editar(Cliente cliente) {
        System.out.println("Contraseña del cliente que le llega al método editar: " + cliente.getContraseña());
        Optional<Cliente> existente = clienteRepositorio.findById(cliente.getIdCliente());
        if (existente.isPresent()) {
            Cliente clienteExistente = existente.get();
            System.out.println("Contraseña del cliente existente: " + clienteExistente.getContraseña());
    
            clienteExistente.setNombreCliente(cliente.getNombreCliente());
            clienteExistente.setApellidos(cliente.getApellidos());
            clienteExistente.setDireccionHabitual(cliente.getDireccionHabitual());
            clienteExistente.setTelefono(cliente.getTelefono());
    
            String contraseña = cliente.getContraseña();
            System.out.println("Contraseña proporcionada: " + contraseña);
    
            if (contraseña != null && !contraseña.isEmpty()) {
                System.out.println("La contraseña no es nula ni está vacía.");
                // Verificar si la contraseña ha cambiado
                if (!contraseña.equals(clienteExistente.getContraseña())) {
                    System.out.println("La contraseña ha cambiado.");
                    // Encriptar solo si la contraseña es nueva
                    String passCrypted = passwordEncoder.encode(contraseña);
                    clienteExistente.setContraseña(passCrypted);
    
                    System.out.println("Contraseña encriptada para cliente " + clienteExistente.getIdCliente() + ": "
                            + clienteExistente.getContraseña());
                } else {
                    System.out.println("La contraseña no ha cambiado. No es necesario encriptar.");
                }
            } else {
                System.out.println("La contraseña es nula o está vacía. No se realizará ninguna acción.");
            }
    
            return clienteRepositorio.save(clienteExistente);
        } else {
            throw new IllegalArgumentException("Cliente no encontrado.");
        }
    }
    
    public void borrar(Cliente cliente) {
        clienteRepositorio.delete(cliente);
    }
    
    @Transactional
    public void borrarPorId(Long idCliente) {
        Cliente cliente = obtenerPorId(idCliente);
        if (cliente != null) {
            pedidoRepositorio.deleteByCompradorIdCliente(idCliente);
            clienteRepositorio.deleteById(idCliente);
        }
    }

    public Cliente obtenerPorEmail(String email) {
        return clienteRepositorio.findByEmail(email);
    }

    public Cliente obtenerClienteConectado() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()
                && !(authentication instanceof AnonymousAuthenticationToken)) {
            String nombreUsuario = authentication.getName();
            return obtenerPorEmail(nombreUsuario);
        }
        return null;
    }

}
