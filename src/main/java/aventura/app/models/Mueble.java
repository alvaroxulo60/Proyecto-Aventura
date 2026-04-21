package aventura.app.models;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Clase Mueble que representa un objeto del entorno que puede ser interactivo o decorativo.
 * Hereda de Objeto.
 */
public class Mueble extends Objeto {

    private static final Logger logger = LoggerFactory.getLogger(Mueble.class);

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
