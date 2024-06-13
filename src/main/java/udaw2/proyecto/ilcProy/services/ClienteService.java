package udaw2.proyecto.ilcProy.services;

import java.util.List;

import udaw2.proyecto.ilcProy.domain.Cliente;

public interface ClienteService {
    
    Cliente añadir(Cliente cliente);
    List<Cliente> obtenerTodos();
    Cliente obtenerPorId(long idCliente);
    Cliente editar(Cliente cliente);
    void borrar(Cliente cliente);
    void borrarPorId(Long idCliente);

    Cliente obtenerPorEmail (String email);
    Cliente obtenerClienteConectado ();
}
