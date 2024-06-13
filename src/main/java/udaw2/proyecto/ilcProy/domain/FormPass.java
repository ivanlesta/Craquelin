package udaw2.proyecto.ilcProy.domain;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class FormPass {

        @NotEmpty
        private String nuevaContraseña;
    
        @NotEmpty
        private String confirmaContraseña;
    
}
