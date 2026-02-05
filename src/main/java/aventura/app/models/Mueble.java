package aventura.app.models;

/**
 * Clase Mueble que representa un objeto del entorno que puede ser interactivo o decorativo.
 * Hereda de Objeto.
 */
public class Mueble extends Objeto {

    /**
     * Constructor de la clase Mueble.
     *
     * @param nombre Nombre del mueble
     * @param descripcion Descripción del mueble
     * @param visible Si el mueble es visible o no en la habitación
     */
    public Mueble(String nombre, String descripcion, boolean visible) {
        super(nombre, descripcion, visible); // Llama al constructor de la clase padre Objeto
    }
}
