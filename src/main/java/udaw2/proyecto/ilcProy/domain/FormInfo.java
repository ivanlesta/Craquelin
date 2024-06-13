package udaw2.proyecto.ilcProy.domain;

import lombok.Data;

@Data
public class FormInfo {
    private String nombre;
    private String email;
    private Integer motivo;
    private String comentarios;
    private Boolean aceptarCondiciones;
}
