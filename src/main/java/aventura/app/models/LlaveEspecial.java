package aventura.app.models;

import aventura.app.exceptions.CombinarException;
import aventura.app.interfaces.Combinable;
import aventura.app.interfaces.Inventariable;

public class LlaveEspecial extends Item implements Combinable {

    public LlaveEspecial(String nombre, String descripcion, boolean visible) {
        super(nombre, descripcion, visible);
    }

    @Override
    public Objeto combinar(Objeto otro) throws CombinarException {
        if (otro instanceof LibroHechizos){
            return new TomoDeLasSombras("Tomo de las sombras","Un antiguo libro de hechizos ahora abierto después de haber utilizado la llave de kitsune en el..." +
                    "Parece poderoso y que nos servirá más adelante", true);
        }
        throw new CombinarException("Estos objetos no son combinables...");
    }
}
