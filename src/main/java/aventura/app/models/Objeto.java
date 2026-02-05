package aventura.app.models;

/**
 * Clase Objeto que representa un elemento dentro del juego.
 * Hereda de Entidad y añade la propiedad de visibilidad.
 */
public class Objeto extends Entidad {

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
        this.visible = visible;
    }
}
