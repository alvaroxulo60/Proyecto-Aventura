package aventura.app.models;

import aventura.app.interfaces.Inventariable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 *Clase Habitación que representa un espacio del juego donde pueden colocarse objetos.
 *Gestiona una lista dinámica de objetos y permite añadir, buscar, eliminar y filtrar elementos.
 */
public class Habitacion {

    private static final Logger logger = LoggerFactory.getLogger(Habitacion.class);

    //Identificador o nombre único de cada habitación
    private final String NOMBRE_HABITACION;

    //Descripción de la habitación
    private final String DESCRIPCION;

    //Mapa que relaciona las direcciones de salida con los nombres de otras habitaciones
    private Map<String,String> mapa;

    //Lista que almacena los objetos que haya en la habitación
    private List<Objeto> objetos;

    /**
     *Constructor de la clase Habitacion.
     *
     *@param DESCRIPCION Descripción de la habitación
     *@param nombreHabitacion Nombre identificador de la habitación
     *@param salidas Mapa con las direcciones y las habitaciones conectadas
     */
    public Habitacion(String DESCRIPCION, String nombreHabitacion, Map<String,String> salidas ) {
        if(nombreHabitacion == null || nombreHabitacion.trim().isEmpty()){
            logger.warn("Se ha inicializado una habitación sin un nombre válido");
        }
        if(salidas == null || salidas.isEmpty()){
            logger.warn("La habitación '{}' se ha creado sin salidas asignadas.", nombreHabitacion);
        }

        this.NOMBRE_HABITACION = nombreHabitacion;
        this.DESCRIPCION = DESCRIPCION;
        this.objetos = new ArrayList<>(); // Inicializa la lista dinámica de objetos
        this.mapa = salidas;

        logger.info("Habitación '{}' creada exitosamente.", nombreHabitacion);
    }

    /**
     *Devuelve la descripción de la habitación.
     *
     *@return descripción
     */
    public String getDESCRIPCION() {
        return DESCRIPCION;
    }

    /**
     *Devuelve la lista de objetos de la habitación.
     *
     *@return lista de objetos
     */
    public List<Objeto> getObjetos() {
        return objetos;
    }

    /**
     *Devuelve el nombre de la habitación.
     * @return nombre de la habitación
     */
    public String getNOMBRE_HABITACION() {
        return NOMBRE_HABITACION;
    }

    /**
     *Devuelve el mapa de salidas disponibles desde esta habitación.
     *@return mapa de salidas
     */
    public Map<String, String> getMapa() {
        return mapa;
    }

    /**
     *Añade un nuevo objeto a la lista de la habitación.
     *
     *@param objeto Objeto a añadir
     */
    public void añadirObjetosHabitacion(Objeto objeto){
        if (objeto == null){
            logger.warn("Intento de añadir un objeto nulo a la habitación '{}'.", NOMBRE_HABITACION);
            return;
        }
        objetos.add(objeto);
        logger.info("Objeto '{}' añadido a la habitación '{}'.", objeto.getNombre(), NOMBRE_HABITACION);
    }

    /**
     *Busca un objeto en la habitación por su nombre, ignorando mayúsculas y minúsculas.
     *
     *@param o Nombre del objeto a buscar
     *@return El objeto si se encuentra, o null si no existe en la habitación
     */
    public Objeto buscarObjetoHabitacion(String o){
        if (o == null || o.trim().isEmpty()){
            logger.warn("Intento de buscar un objeto con nombre nulo o vacío en la habitación '{}'.", NOMBRE_HABITACION);
        }
        return objetos.stream()
                .filter(objeto -> objeto.getNombre().equalsIgnoreCase(o))
                .findFirst().orElse(null); // Retorna el primero que coincida o null
    }

    /**
     * Elimina un objeto específico de la habitación (ej. cuando el jugador lo recoge).
     *
     * @param o Objeto a eliminar
     */
    public void quitarObjetoHabitacion(Objeto o){
        if (o == null){
            logger.warn("Intento de eliminar un objeto nulo de la habitación '{}'.", NOMBRE_HABITACION);
            return;
        }
        if (objetos.remove(o)){
            logger.info("Objeto '{}' retirado de la habitación '{}'.", o.getNombre(), NOMBRE_HABITACION);
        }else {
            logger.warn("Se intentó retirar el objeto '{}' de la habitación '{}', pero no se encontraba allí.", o.getNombre() ,NOMBRE_HABITACION);
        }
    }

    /**
     * Devuelve la cantidad total de objetos presentes en la habitación.
     *
     * @return número de objetos en la lista
     */
    public int contarObjetosHabitacion(){
        return objetos.size();
    }

    /**
     * Cuenta cuántos de los objetos presentes en la habitación pueden ser recogidos por el jugador.
     *
     * @return número de objetos que implementan la interfaz Inventariable
     */
    public long contarObjetosInventariablesHabitacion(){
        return objetos.stream()
                .filter(objeto -> objeto instanceof Inventariable).count();
    }

    /**
     * Cuenta cuántos contenedores (objetos que pueden guardar otros objetos) hay en la habitación.
     *
     * @return número de objetos que son instancias de Contenedor
     */
    public long contarContenedoresHabitacion(){
        return objetos.stream()
                .filter(objeto -> objeto instanceof Contenedor).count();
    }

    /**
     * Genera una cadena de texto con la lista numerada de todos los objetos en la habitación.
     * * @return un string con los nombres de todos los objetos
     */
    public String mostrarObjetosHabitacion(){
        int contador = 1;
        StringBuilder contenido = new StringBuilder();
        for (Objeto o: objetos) {
            if (o != null) {
                contenido.append(contador++).append(". ").append(o.getNombre()).append(System.lineSeparator());
            }
        }
        return contenido.toString();
    }

    /**
     * Genera una cadena de texto con la lista numerada solo de los objetos que se pueden recoger.
     * * @return un string con los objetos inventariables
     */
    public String mostrarObjetosInventariables(){
        int contador = 1;
        StringBuilder contenido = new StringBuilder();
        for (Objeto o: objetos) {
            if (o instanceof Inventariable){
                contenido.append(contador++).append(". ").append(o.getNombre()).append(System.lineSeparator());
            }
        }
        return contenido.toString();
    }

    /**
     * Genera una cadena de texto con la lista numerada solo de los contenedores presentes.
     * * @return un string con los contenedores
     */
    public String mostrarContenedores(){
        int contador = 1;
        StringBuilder contenido = new StringBuilder();
        for (Objeto o: objetos) {
            if (o instanceof Contenedor){
                contenido.append(contador++).append(". ").append(o.getNombre()).append(System.lineSeparator());
            }
        }
        return contenido.toString();
    }

    @Override
    public final boolean equals(Object o) {
        if (!(o instanceof Habitacion that)) return false;

        return NOMBRE_HABITACION.equals(that.NOMBRE_HABITACION);
    }

    @Override
    public int hashCode() {
        return NOMBRE_HABITACION.hashCode();
    }
}