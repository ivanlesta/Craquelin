package udaw2.proyecto.ilcProy.services;

import java.util.List;

import udaw2.proyecto.ilcProy.domain.Categoria;

public interface CategoriaService {
    Categoria añadir(Categoria categoria);
    List<Categoria> obtenerTodos();
    Categoria obtenerPorId(long idCategoria);
    Categoria editar(Categoria categoria);
    void borrar(Categoria categoria);
    void borrarPorId(Long idCategoria);

    Categoria obtenerPorNombre (String nombreCategoria);
}
