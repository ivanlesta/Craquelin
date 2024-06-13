package udaw2.proyecto.ilcProy.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import udaw2.proyecto.ilcProy.domain.Categoria;
import udaw2.proyecto.ilcProy.domain.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Long> {

    Producto findBynombreProducto(String nombreProducto);

    List<Producto> findByCategoria(Categoria categoria);

}
