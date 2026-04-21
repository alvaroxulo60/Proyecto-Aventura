package aventura.app.models;

import aventura.app.exceptions.CombinarException;
import aventura.app.interfaces.Combinable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Clase NotaRota que representa un objeto que puede combinarse con otra NotaRota
 * para formar una Nota completa.
 * Hereda de Item e implementa la interfaz Combinable.
 */
public class NotaRota extends Item implements Combinable {

    private static final Logger logger = LoggerFactory.getLogger(NotaRota.class);

    /**
     * Constructor de la clase NotaRota.
     *
     * @param nombre Nombre de la nota rota
     * @param descripcion Descripción de la nota rota
     * @param visible Si la nota es visible o no
     */
    public NotaRota(String nombre, String descripcion, boolean visible) {
        super(nombre, descripcion, visible); // Llama al constructor de Item
        logger.info("Nota rota '{}' instanciada correctamente",nombre);
    }

    /**
     * Combina esta NotaRota con otro objeto.
     * Solo se puede combinar con otra NotaRota para formar una Nota completa.
     *
     * @param otro Objeto con el que se intenta combinar
     * @return Nueva Nota si la combinación es exitosa
     * @throws CombinarException Si los objetos no son combinables
     */
    @Override
    public Objeto combinar(Objeto otro) throws CombinarException {
        if (otro == null){
            logger.warn("Se ha intentado combinar la nota rota '{}' con un objeto nulo",getNombre());
            throw new CombinarException("No es posible combinar estos objetos");
        }
        // Comprueba que el otro objeto sea una NotaRota
        if (otro instanceof NotaRota){
            logger.info("Combinación exitosa: Las partes '{}' y '{}' se han unido para formar la nota completa", getNombre(), otro.getNombre());
            // Devuelve una nueva Nota completa al combinar ambas notas rotas
            return new Nota(
                    "Nota arreglada",
                    "La nota resultante al juntar las dos notas rotas",
                    true,
                    "Has de continuar con tu travesía para poder aprender el poder latente que hay en tí. " +
                            "Yo, tu hermano, he preparado esta aventura para que comprendas lo difícil que es ser mago, " +
                            "si la superas te convertirás en un mago espléndido, sino morirás en el intento jajajajajaja."
            );
        }

        logger.warn("Intento de combinación fallido: La nota rota '{}' no es compatible con '{}'", getNombre(), otro.getNombre());
        // Lanza excepción si no se puede combinar
        throw new CombinarException("No es posible combinar estos objetos");
    }
}
