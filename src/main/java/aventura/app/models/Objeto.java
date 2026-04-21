package aventura.app.models;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Clase Objeto que representa un elemento dentro del juego.
 * Hereda de Entidad y añade la propiedad de visibilidad.
 */
public abstract class Objeto extends Entidad {

    private static final Logger logger = LoggerFactory.getLogger(Objeto.class);

    // Indica si el objeto es visible en la habitación o entorno
    private boolean visible;

    /**
     * Constructor de la clase Objeto.
     *
     * @param nombre Nombre del objeto
     * @param descripcion Descripción del objeto
     * @param visible Si el objeto es visible o no
     */
    public Objeto(String nombre, String descripcion, boolean visible) {
        super(nombre, descripcion); // Llama al constructor de la clase padre Entidad
        this.visible = visible; // Inicializa la visibilidad del objeto

        if (!visible){
            logger.info("El objeto '{}' se ha inicializado en estado oculto", nombre);
        }
    }

    /**
     * Método getter para saber si el objeto es visible.
     *
     * @return true si el objeto es visible, false si no
     */
    public boolean isVisible() {
        return visible;
    }

    /**
     * Método setter para cambiar la visibilidad del objeto.
     *
     * @param visible nuevo estado de visibilidad
     */
    public void setVisible(boolean visible) {
        if (this.visible == visible){
            logger.warn("Se intentó cambiar la visibilidad del objeto'{}' a '{}', pero ya se encontraba en ese estado", getNombre(), visible);
        }else {
            logger.info("La visibilidad del objeto '{}' ha cambiado. Nuevo estado visible:{}", getNombre(), visible);
            this.visible = visible;
        }
    }
}
