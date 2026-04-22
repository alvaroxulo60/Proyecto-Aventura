package aventura.app.models;

import aventura.app.exceptions.CombinarException;
import aventura.app.interfaces.Combinable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Clase LibroHechizos que representa un item especial que se puede combinar con otros objetos.
 * Hereda de Item e implementa la interfaz Combinable.
 */
public class LibroHechizos extends Item implements Combinable {

    private static final Logger logger = LoggerFactory.getLogger(LibroHechizos.class);

    /**
     * Constructor de LibroHechizos.
     *
     * @param nombre      Nombre del libro
     * @param descripcion Descripción del libro
     * @param visible     Si el libro es visible o no
     */
    public LibroHechizos(String nombre, String descripcion, boolean visible) {
        super(nombre, descripcion, visible); // Llama al constructor de la clase Item
        logger.info("Libro de hechizos '{}' instanciado correctamente.", nombre);
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
        if (otro == null){
            logger.warn("Se ha instanciado combinar el libro '{}' con un objeto nulo", getNombre());
            return  null;
        }

        // Comprueba si el otro objeto es una LlaveEspecial usando pattern matching (Java 16+)
        if (otro instanceof LlaveEspecial l){
            logger.info("Iniciando combinación del libro '{}' con la llave especial '{}'", getNombre(), otro.getNombre());
            return l.combinar(this); // Llama al método combinar de LlaveEspecial pasando este libro
        }
        logger.warn("El libro '{}' no es compatible para combinarse con el objeto '{}'",getNombre(), otro.getNombre());
        throw new CombinarException("Estos objetos no son combinables...");    }
}
