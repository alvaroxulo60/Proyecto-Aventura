package aventura.app.models;

import aventura.app.exceptions.CombinarException;
import aventura.app.interfaces.Combinable;
import aventura.app.interfaces.Inventariable;

public class LibroHechizos extends Item implements Combinable {

    public LibroHechizos(String nombre, String descripcion, boolean visible) {
        super(nombre, descripcion, visible);
    }

    @Override
    public Objeto combinar(Objeto otro) throws CombinarException {
        Objeto aux = null;
        if(otro instanceof LlaveEspecial l){
           aux = l.combinar(this);
        }
        return aux;
    }
}
