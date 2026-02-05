package aventura.app.models;

/**
 * Clase Jugador que representa al personaje controlado por el usuario.
 * Gestiona la posición del jugador y su inventario de objetos.
 */
public class Jugador extends Personaje {

    // Tamaño máximo del inventario del jugador
    private static final int TAM_INV = 10;

    // Posición actual del jugador (por ejemplo, en un mapa o array de habitaciones)
    private int posicionJugador;

    // Array que representa el inventario del jugador
    private Objeto[] inventario;

    /**
     * Constructor de la clase Jugador.
     * Inicializa la posición en 0 y el inventario vacío.
     */
    public Jugador() {
        posicionJugador = 0;
        inventario = new Objeto[TAM_INV]; // Inicializa el inventario con tamaño fijo
    }

    /**
     * Devuelve la posición actual del jugador.
     *
     * @return posición del jugador
     */
    public int getPosicionJugador() {
        return posicionJugador;
    }

    /**
     * Devuelve el array de objetos del inventario.
     *
     * @return inventario del jugador
     */
    public Objeto[] getInventario() {
        return inventario;
    }

    /**
     * Establece la posición del jugador.
     *
     * @param posicionJugador nueva posición
     */
    public void setPosicionJugador(int posicionJugador) {
        this.posicionJugador = posicionJugador;
    }

    /**
     * Busca un objeto en el inventario por su nombre.
     *
     * @param objeto nombre del objeto a buscar
     * @return el objeto si se encuentra, null si no existe
     */
    public Objeto buscarObjetoInventario(String objeto){
        for (int i = 0; i < inventario.length; i++) {
            if (inventario[i] != null){
                if (inventario[i].getNombre().equalsIgnoreCase(objeto)){ // Comparación ignorando mayúsculas
                    return inventario[i];
                }
            }
        }
        return null; // No se encontró el objeto
    }

    /**
     * Busca una llave específica en el inventario por su código de seguridad.
     *
     * @param codigo código de la llave
     * @return la llave encontrada, o null si no existe
     */
    public Llave buscarLlaveInventario(String codigo){
        for (int i = 0; i < inventario.length; i++) {
            if (inventario[i] != null){
                // Comprueba si el objeto es una llave usando pattern matching (Java 16+)
                if (inventario[i] instanceof Llave l){
                    if (l.getCODIGO_SEGURIDAD().equals(codigo)){ // Comparación de códigos
                        return l; // Retorna la llave encontrada
                    }
                }
            }
        }
        return null; // No se encontró la llave
    }

    /**
     * Guarda un objeto en el inventario del jugador.
     *
     * @param o objeto a guardar
     * @return true si se ha añadido correctamente, false si no hay espacio
     */
    public boolean guardarInventario(Objeto o){
        for (int i = 0; i < inventario.length; i++) {
            if (inventario[i] == null){
                inventario[i] = o; // Guarda el objeto en la primera posición libre
                return true; // Acción completada
            }
        }
        return false; // No hay espacio en el inventario
    }

    /**
     * Devuelve un String con la lista de objetos en el inventario.
     *
     * @return String con todos los nombres de los objetos
     */
    public String verInventario(){
        StringBuilder inv = new StringBuilder();
        int contador = 1;
        for (int i = 0; i < inventario.length; i++) {
            if (inventario[i] != null){
                // Agrega un número consecutivo y el nombre del objeto
                inv.append(contador++).append(".").append(inventario[i].getNombre())
                        .append(System.lineSeparator());
            }
        }
        return inv.toString();
    }

    /**
     * Cuenta el número de objetos presentes en el inventario.
     *
     * @return cantidad de objetos
     */
    public int contarObjetosInventario(){
        int contador = 0;
        for (int i = 0; i < inventario.length; i++) {
            if (inventario[i] != null){
                contador++;
            }
        }
        return contador;
    }

    /**
     * Elimina un objeto específico del inventario, por ejemplo si se consume o se usa.
     *
     * @param o objeto a eliminar
     */
    public void consumirObjetosInventario(Objeto o){
        for (int i = 0; i < inventario.length; i++) {
            if (inventario[i] != null){
                if (inventario[i].equals(o)){
                    inventario[i] = null; // Elimina el objeto
                    return; // Sale del método una vez consumido
                }
            }
        }
    }

    /**
     * Metodo para mostrar todos los objetos en la habitación
     * @return un string con todos los objetos
     */
    public String mostrarObjetosInventario(){
        int contador = 1;
        StringBuilder contenido = new StringBuilder();
        for (int i = 0; i < inventario.length; i++) {
            if (inventario[i] != null) {
                contenido.append(contador++).append(inventario[i].getNombre()).append(System.lineSeparator());
            }
        }
        return contenido.toString();
    }
}
