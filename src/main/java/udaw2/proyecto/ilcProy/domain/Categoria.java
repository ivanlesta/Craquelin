package udaw2.proyecto.ilcProy.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "idCategoria")

@Entity
public class Categoria {
    @Id
    @GeneratedValue
    @Column(name = "id_categoria")
    private Long idCategoria;

    private String nombreCategoria;
}
