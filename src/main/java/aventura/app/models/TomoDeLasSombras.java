package aventura.app.models;

import aventura.app.interfaces.Inventariable;

/**
 * Clase TomoDeLasSombras que representa un libro especial que se puede recoger.
 * Hereda de Objeto e implementa la interfaz Inventariable,
 * por lo que puede añadirse al inventario del jugador.
 */
public class TomoDeLasSombras extends Objeto implements Inventariable {

    /**
     * Constructor de la clase TomoDeLasSombras.
     *
     * @param nombre Nombre del tomo
     * @param descripcion Descripción del tomo
     * @param visible Si el tomo es visible en la habitación o entorno
     */
    public TomoDeLasSombras(String nombre, String descripcion, boolean visible) {
        super(nombre, descripcion, visible); // Llama al constructor de la clase padre Objeto
    }
}
