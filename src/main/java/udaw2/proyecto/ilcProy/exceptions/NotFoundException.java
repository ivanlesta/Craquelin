package udaw2.proyecto.ilcProy.exceptions;

public class NotFoundException extends Exception{
    public NotFoundException (){
        super("Elemento no encontrado");
    }
    public NotFoundException (String msg){
        super (msg);
    }
}
