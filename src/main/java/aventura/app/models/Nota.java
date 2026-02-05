package aventura.app.models;

import aventura.app.interfaces.Leible;

/**
 * Clase Nota que representa un objeto que se puede leer.
 * Hereda de Item e implementa la interfaz Leible.
 */
public class Nota extends Item implements Leible {

    // Contenido de la nota que se puede leer
    private String contenido;

    /**
     * Constructor de la clase Nota.
     *
     * @param nombre Nombre de la nota
     * @param descripcion Descripción de la nota
     * @param visible Si la nota es visible en el entorno
     * @param contenido Texto que contiene la nota
     */
    public Nota(String nombre, String descripcion, boolean visible, String contenido) {
        super(nombre, descripcion, visible); // Llama al constructor de la clase Item
        this.contenido = contenido; // Inicializa el contenido de la nota
    }

    /**
     * Método que devuelve el contenido de la nota.
     *
     * @return contenido de la nota
     */
    @Override
    public String leer() {
        return contenido; // Devuelve el texto almacenado en la nota
    }
}
