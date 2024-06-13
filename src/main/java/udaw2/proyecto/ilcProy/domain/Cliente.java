package udaw2.proyecto.ilcProy.domain;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "idCliente")

@Entity
public class Cliente {
    @Id
    @GeneratedValue
    private Long idCliente;

    @Column(unique = true)
    private String email;

    private String contraseña;

    private String nombreCliente;

    private String apellidos;

    private String direccionHabitual;

    private Long telefono;    

    @Enumerated(EnumType.STRING)
    private Rol rol;

    private LocalDateTime fechaRegistro;
}