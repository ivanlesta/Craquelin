package udaw2.proyecto.ilcProy.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "idLinea")
public class LineaDePedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idLinea;

    private Long cantidadProducto;

    @ManyToOne
    private Pedido pedido;

    @ManyToOne
    private Producto producto;

    @Override
    public String toString() {
        return "LineaDePedido{" +
                "idLinea=" + idLinea +
                ", cantidadProducto=" + cantidadProducto +
                '}';
    }
}
