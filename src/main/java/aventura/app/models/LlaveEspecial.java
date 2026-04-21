package aventura.app.models;

import aventura.app.exceptions.CombinarException;
import aventura.app.interfaces.Combinable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Clase LlaveEspecial que representa un item único que puede combinarse con ciertos objetos.
 * Hereda de Item e implementa la interfaz Combinable.
 */
public class LlaveEspecial extends Item implements Combinable {

    private static final Logger logger = LoggerFactory.getLogger(LlaveEspecial.class);

    /**
     * Constructor de LlaveEspecial.
     *
     * @param nombre Nombre de la llave especial
     * @param descripcion Descripción de la llave especial
     * @param visible Si la llave es visible o no
     */
    public LlaveEspecial(String nombre, String descripcion, boolean visible) {
        super(nombre, descripcion, visible); // Llama al constructor de la clase Item
        logger.info("Llave especial '{}' instancida", nombre);
    }

    /**
     * Combina esta llave especial con otro objeto.
     * Solo puede combinarse con un LibroHechizos para crear un TomoDeLasSombras.
     *
     * @param otro Objeto con el que se intenta combinar
     * @return Nuevo objeto resultante de la combinación
     * @throws CombinarException Si los objetos no son combinables
     */
    @Override
    public Objeto combinar(Objeto otro) throws CombinarException {
        if (otro == null){
            logger.warn("Se ha intentado combinar la llave especial '{}' con un objeto nulo", getNombre());
            throw new CombinarException("Estos objetos no son combinables...");
        }

        // Comprueba si el otro objeto es un LibroHechizos
        if (otro instanceof LibroHechizos){
            logger.info("Combinación exitosa: La llave especial '{}' ha transformado el '{}' en el Tomo de las Sombras", getNombre(), otro.getNombre());
            // Devuelve un nuevo TomoDeLasSombras como resultado de la combinación
            return new TomoDeLasSombras(
                    "Tomo de las sombras",
                    "Un antiguo libro de hechizos ahora abierto después de haber utilizado la llave de kitsune en el..." +
                            "Parece poderoso y que nos servirá más adelante",
                    true
            );
        }

        logger.warn("Intento de combinación fallido: La llave espcial '{}' no es compatible con '{}'", getNombre(), otro.getNombre());
        // Si no se puede combinar, lanza una excepción indicando que no son combinables
        throw new CombinarException("Estos objetos no son combinables...");
    }
}
