package aventura.app.models;

import aventura.app.exceptions.CombinarException;
import aventura.app.interfaces.Combinable;

/**
 * Clase LibroHechizos que representa un item especial que se puede combinar con otros objetos.
 * Hereda de Item e implementa la interfaz Combinable.
 */
public class LibroHechizos extends Item implements Combinable {

    /**
     * Constructor de LibroHechizos.
     *
     * @param nombre Nombre del libro
     * @param descripcion Descripción del libro
     * @param visible Si el libro es visible o no
     */
    public LibroHechizos(String nombre, String descripcion, boolean visible) {
        super(nombre, descripcion, visible); // Llama al constructor de la clase Item
    }

    /**
     * Método para combinar este libro con otro objeto.
     *
     * @param otro Objeto con el que se intenta combinar
     * @return El nuevo objeto resultante de la combinación, o null si no se puede combinar
     * @throws CombinarException Si ocurre un error al intentar combinar
     */
    @Override
    public Objeto combinar(Objeto otro) throws CombinarException {
        Objeto aux = null; // Variable para almacenar el resultado de la combinación

        // Comprueba si el otro objeto es una LlaveEspecial usando pattern matching (Java 16+)
        if (otro instanceof LlaveEspecial l){
            aux = l.combinar(this); // Llama al método combinar de LlaveEspecial pasando este libro
        }

        return aux; // Devuelve el objeto combinado o null si no se pudo combinar
    }
}
