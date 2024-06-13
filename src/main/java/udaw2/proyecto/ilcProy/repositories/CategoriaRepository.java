package udaw2.proyecto.ilcProy.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import udaw2.proyecto.ilcProy.domain.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

    Categoria findByNombreCategoria(String nombreCategoria);

}