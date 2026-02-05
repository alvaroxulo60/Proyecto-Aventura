package aventura.app.models;

import aventura.app.interfaces.Inventariable;

/**
 * Clase Item que representa un objeto que puede ser añadido al inventario.
 * Hereda de Objeto e implementa la interfaz Inventariable.
 */
public class Item extends Objeto implements Inventariable {

    /**
     * Constructor de la clase Item.
     *
     * @param nombre Nombre del item
     * @param descripcion Descripción del item
     * @param visible Si el item es visible o no
     */
    public Item(String nombre, String descripcion, boolean visible) {
        super(nombre, descripcion, visible); // Llama al constructor de la clase padre Objeto
    }
}
