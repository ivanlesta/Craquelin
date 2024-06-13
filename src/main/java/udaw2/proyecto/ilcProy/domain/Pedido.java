package udaw2.proyecto.ilcProy.domain;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "idPedido")
public class Pedido {
    @Id
    @GeneratedValue
    private Long idPedido;

    private Long unidadesTotales;

    private Double precioTotal;

    private LocalDateTime fechaPedido;

    @Enumerated(EnumType.STRING)
    private EstadoPedido estadoPedido;

    @ManyToOne
    @JoinColumn(name = "comprador_id_cliente")
    private Cliente comprador;
    
    // , cascade = CascadeType.ALL, orphanRemoval = true
    @OneToMany(mappedBy = "pedido")
    private List<LineaDePedido> lineasDePedido;

    // Método para obtener la fecha formateada
    public String getFechaPedidoFormateada() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm dd/MM/yyyy");
        return this.fechaPedido.format(formatter);
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "idPedido=" + idPedido +
                ", unidadesTotales=" + unidadesTotales +
                ", precioTotal=" + precioTotal +
                ", fechaPedido=" + fechaPedido +
                ", estadoPedido=" + estadoPedido +
                ", comprador=" + comprador +
                '}';
    }
}
