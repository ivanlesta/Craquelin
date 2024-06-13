package udaw2.proyecto.ilcProy.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import udaw2.proyecto.ilcProy.domain.Categoria;
import udaw2.proyecto.ilcProy.repositories.CategoriaRepository;

@Service
public class CategoriaServiceImplBD implements CategoriaService {
    @Autowired
    CategoriaRepository categoriaRepository;

    public Categoria añadir(Categoria categoria){
        return categoriaRepository.save(categoria);
    }
    public List<Categoria> obtenerTodos(){
        return categoriaRepository.findAll();
    }
    public Categoria obtenerPorId(long id){
        return categoriaRepository.findById(id).orElse(null);
    }
    public Categoria editar(Categoria categoria){
        return categoriaRepository.save(categoria);
    }
    public void borrar(Categoria categoria){
        categoriaRepository.delete(categoria);
    }
    public void borrarPorId(Long id){
        categoriaRepository.deleteById(id);
    }

    public Categoria obtenerPorNombre(String nombre) {
        return categoriaRepository.findByNombreCategoria(nombre);
    }

}
