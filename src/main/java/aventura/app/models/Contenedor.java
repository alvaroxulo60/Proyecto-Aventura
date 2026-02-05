package aventura.app.models;

// Importaciones necesarias para la interfaz Abrible y el registro de respuesta
import aventura.app.interfaces.Abrible;
import aventura.app.records.RespuestaAccion;

/**
 * Clase Contenedor que representa un objeto que puede contener otro objeto
 * y puede ser abierto mediante un código secreto o una llave.
 * Hereda de Objeto e implementa la interfaz Abrible.
 */
public class Contenedor extends Objeto implements Abrible {

    // Objeto contenido dentro del contenedor
    private Objeto elemento;

    // Código secreto necesario para abrir el contenedor, si aplica
    private final String CODIGO_SECRETO;

    // Estado del contenedor: abierto o cerrado
    private boolean estaAbierto;

    /**
     * Constructor de la clase Contenedor.
     *
     * @param nombre Nombre del contenedor
     * @param descripcion Descripción del contenedor
     * @param visible Si el contenedor es visible o no
     * @param CODIGO_SECRETO Código secreto requerido para abrirlo
     * @param elemento Objeto contenido dentro del contenedor
     * @param estaAbierto Estado inicial del contenedor
     */
    public Contenedor(String nombre, String descripcion, boolean visible, String CODIGO_SECRETO, Objeto elemento, boolean estaAbierto) {
        super(nombre, descripcion, visible); // Llamada al constructor de la clase padre
        this.CODIGO_SECRETO = CODIGO_SECRETO;
        this.elemento = elemento;
        this.estaAbierto = estaAbierto;
    }

    /**
     * Intenta abrir el contenedor usando una llave.
     *
     * @param llave Llave usada para abrir el contenedor
     * @return RespuestaAccion indicando si la acción fue exitosa y un mensaje
     */
    @Override
    public RespuestaAccion abrir(Llave llave) {
        // Si el contenedor ya está abierto
        if(estaAbierto){
            return new RespuestaAccion(false, "Ya está abierto");
        }

        // Si no hay código secreto, se puede abrir directamente
        if (CODIGO_SECRETO == null){
            estaAbierto = true;
            return new RespuestaAccion(true,"Se ha abierto");
        }

        // Si la llave es válida y coincide con el código secreto
        if(llave != null && llave.getCODIGO_SEGURIDAD().equals(CODIGO_SECRETO)){
            estaAbierto = true;
            return new RespuestaAccion(true,"Click, se ha abierto") ;
        }
        else {
            // Si no se puede abrir, indicar que se necesita una llave
            return new RespuestaAccion(false, "Necesitas una llave para abrir esto");
        }
    }

    /**
     * Método que indica si el contenedor está abierto.
     *
     * @return true si está abierto, false en caso contrario
     */
    @Override
    public boolean estaAbierto() {
        return estaAbierto;
    }

    /**
     * Devuelve el objeto contenido dentro del contenedor.
     * @return elemento contenido
     */
    public Objeto getElemento() {
        return elemento;
    }

    /**
     * Devuelve el código secreto del contenedor.
     * @return CODIGO_SECRETO
     */
    public String getCODIGO_SECRETO() {
        return CODIGO_SECRETO;
    }

    // Elimina el objeto contenido, dejando el contenedor vacío
    public void eliminarObjeto(){
        this.elemento = null;
    }
}
