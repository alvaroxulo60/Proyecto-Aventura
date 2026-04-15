package aventura.app.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase Jugador que representa al personaje controlado por el usuario.
 * Gestiona la posición del jugador y su inventario de objetos.
 */
public class Jugador extends Personaje {

    // Tamaño máximo del inventario del jugador

    private String posicionIncialJugador;

    // Posición actual del jugador (por ejemplo, en un mapa o array de habitaciones)
    private String posicionJugador;

    // Array que representa el inventario del jugador
    private List<Objeto> inventario;

    /**
     * Constructor de la clase Jugador.
     * Inicializa la posición en 0 y el inventario vacío.
     */
    public Jugador() {
        posicionIncialJugador = "Tu Habitación";
        inventario = new ArrayList<>();
    }

    public String getPosicionIncialJugador() {
        return posicionIncialJugador;
    }

    /**
     * Devuelve la posición actual del jugador.
     *
     * @return posición del jugador
     */


    public String getPosicionJugador() {
        return posicionJugador;
    }

    /**
     * Devuelve el array de objetos del inventario.
     *
     * @return inventario del jugador
     */
    public List<Objeto> getInventario() {
        return inventario;
    }

    /**
     * Establece la posición del jugador.
     *
     * @param posicionJugador nueva posición
     */
    public void setPosicionJugador(String posicionJugador) {
        this.posicionJugador = posicionJugador;
    }

    /**
     * Busca un objeto en el inventario por su nombre.
     *
     * @param objeto nombre del objeto a buscar
     * @return el objeto si se encuentra, null si no existe
     */
    public Objeto buscarObjetoInventario(String objeto) {
        for (Objeto o : inventario) {
            if (o != null) {
                if (o.getNombre().equalsIgnoreCase(objeto)) { // Comparación ignorando mayúsculas
                    return o;
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
    public Llave buscarLlaveInventario(String codigo) {
        for (Objeto o : inventario) {
            if (o != null) {
                // Comprueba si el objeto es una llave usando pattern matching (Java 16+)
                if (o instanceof Llave l) {
                    if (l.getCODIGO_SEGURIDAD().equals(codigo)) { // Comparación de códigos
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
    public boolean guardarInventario(Objeto o) {
        return inventario.add(o);
    }

    /**
     * Cuenta el número de objetos presentes en el inventario.
     *
     * @return cantidad de objetos
     */
    public int contarObjetosInventario() {
        return inventario.size();
    }

    /**
     * Elimina un objeto específico del inventario, por ejemplo si se consume o se usa.
     *
     * @param o objeto a eliminar
     */
    public void consumirObjetosInventario(Objeto o) {
        inventario.remove(o);
    }

    /**
     * Metodo para mostrar todos los objetos en la habitación
     *
     * @return un string con todos los objetos
     */
    public String mostrarObjetosInventario() {
        int contador = 1;
        StringBuilder contenido = new StringBuilder();
        for (Objeto o: inventario) {
            if (o != null) {
                contenido.append(contador++).append(". ").append(o.getNombre()).append(System.lineSeparator());
            }
        }
        return contenido.toString();
    }
}
