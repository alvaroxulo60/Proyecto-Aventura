package aventura.app.models;

import aventura.app.interfaces.Combinable;
import aventura.app.interfaces.Inventariable;

public class LibroHechizos extends Objeto implements Inventariable, Combinable {

    public LibroHechizos(String nombre, String descripcion, boolean visible) {
        super(nombre, descripcion, visible);
    }

    @Override
    public Objeto combinar(Objeto otro) {
        if(otro instanceof LlaveEspecial l){
            l.combinar(this);
        }
    }
}
