package udaw2.proyecto.ilcProy.domain;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "idProducto")

@Entity
public class Producto {
    @Id
    @GeneratedValue
    private Long idProducto;

    private String nombreProducto;

    private Double precioProducto;

    private String descripcionProducto;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Categoria categoria;

    private String foto;
}