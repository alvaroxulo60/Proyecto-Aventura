package aventura.app.models;

import java.util.ArrayList;
import java.util.List;

/**
 *Clase Jugador que representa al personaje controlado por el usuario.
 *Gestiona la posición actual del jugador y su inventario de objetos mediante una lista.
 */
public class Jugador extends Personaje {

    //Posición inicial al crear al jugador
    private String posicionIncialJugador;

    //Nombre de la posición actual del jugador
    private String posicionJugador;

    //Lista que representa el inventario de objetos del jugador
    private List<Objeto> inventario;

    /**
     *Constructor de la clase Jugador.
     *Inicializa la posición inicial en "Tu habitación", establece la posición actual
     *y crea un inventario vacío.
     */
    public Jugador() {
        posicionIncialJugador = "Tu habitación";
        posicionJugador = getPosicionIncialJugador();
        inventario = new ArrayList<>();
    }

    /**
     *Devuelve la posición inicial predeterminada del jugador.
     *
     *@return posición inicial del jugador
     */
    public String getPosicionIncialJugador() {
        return posicionIncialJugador;
    }

    /**
     *Devuelve la posición actual del jugador.
     *
     *@return posición actual del jugador
     */
    public String getPosicionJugador() {
        return posicionJugador;
    }

    /**
     *Devuelve la lista de objetos.
     *
     *@return lista de objetos
     */
    public List<Objeto> getInventario() {
        return inventario;
    }

    /**
     *Establece la nueva posición para el jugador.
     *
     *@param posicionJugador nueva posición
     */
    public void setPosicionJugador(String posicionJugador) {
        this.posicionJugador = posicionJugador;
    }

    /**
     *Busca un objeto en el inventario por su nombre.
     *
     *@param objeto nombre del objeto a buscar
     *@return el objeto si se encuentra, null si no existe en el inventario
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
     *Busca una llave específica en el inventario utilizando su código de seguridad.
     *
     *@param codigo código de la llave a buscar
     *@return la llave encontrada, o null si no existe
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
     *Añade un objeto al inventario del jugador.
     *
     *@param o objeto a guardar
     *@return true si se ha añadido correctamente a la lista
     */
    public boolean guardarInventario(Objeto o) {
        return inventario.add(o);
    }

    /**
     *Cuenta el número de objetos actuales en el inventario.
     *
     *@return cantidad total de objetos
     */
    public int contarObjetosInventario() {
        return inventario.size();
    }

    /**
     *Elimina un objeto específico del inventario.
     *
     *@param o objeto a eliminar
     */
    public void consumirObjetosInventario(Objeto o) {
        inventario.remove(o);
    }

    /**
     *Genera un String con la lista numerada de todos los objetos del inventario.
     *
     *@return un string con los nombres de los objetos del inventario
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