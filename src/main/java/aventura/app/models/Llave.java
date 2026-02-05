package aventura.app.models;

/**
 * Clase Llave que representa un objeto que puede abrir contenedores.
 * Hereda de Item y añade un código de seguridad único.
 */
public class Llave extends Item {

    // Código de seguridad que identifica la llave de manera única
    private final String CODIGO_SEGURIDAD;

    /**
     * Constructor de la clase Llave.
     *
     * @param nombre Nombre de la llave
     * @param descripcion Descripción de la llave
     * @param visible Si la llave es visible o no
     * @param CODIGO_SEGURIDAD Código de seguridad único de la llave
     */
    public Llave(String nombre, String descripcion, boolean visible, String CODIGO_SEGURIDAD) {
        super(nombre, descripcion, visible); // Llama al constructor de la clase padre Item
        this.CODIGO_SEGURIDAD = CODIGO_SEGURIDAD; // Inicializa el código de seguridad
    }

    /**
     * Devuelve el código de seguridad de la llave.
     *
     * @return código de seguridad
     */
    public String getCODIGO_SEGURIDAD() {
        return CODIGO_SEGURIDAD;
    }
}
