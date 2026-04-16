package aventura.app.models;

import aventura.app.interfaces.Inventariable;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Clase Habitación que representa un espacio donde pueden colocarse objetos.
 * Gestiona un array fijo de objetos, permite añadir, buscar, eliminar y contar objetos.
 */
public class Habitacion {

    // Nombre de cada habitación
    private final String NOMBRE_HABITACION;

    // Descripción de la habitación
    private final String DESCRIPCION;

    // Lista de salidas de cada habitación
    private Map<String,String> mapa;

    // Array que almacena los objetos de la habitación
    private List<Objeto> objetos;

    /**
     * Constructor de la clase Habitacion.
     *
     * @param DESCRIPCION Descripción de la habitación
     */
    public Habitacion(String DESCRIPCION, String nombreHabitacion, Map<String,String> salidas ) {
        this.NOMBRE_HABITACION = nombreHabitacion;
        this.DESCRIPCION = DESCRIPCION;
        this.objetos = new ArrayList<>(); // Inicializa el array de objetos
        this.mapa = salidas;
    }

    /**
     * Devuelve la descripción de la habitación.
     *
     * @return descripción
     */
    public String getDESCRIPCION() {
        return DESCRIPCION;
    }

    /**
     * Devuelve el array de objetos en la habitación.
     *
     * @return array de objetos
     */
    public List<Objeto> getObjetos() {
        return objetos;
    }

    public String getNOMBRE_HABITACION() {
        return NOMBRE_HABITACION;
    }

    /**
     * Añade un objeto al primer espacio disponible del array de objetos.
     *
     * @param objeto Objeto a añadir
     */


    public void añadirObjetosHabitacion(Objeto objeto){
        objetos.add(objeto);
    }

    /**
     * Busca un objeto en la habitación por su nombre.
     *
     * @param o Nombre del objeto a buscar
     * @return El objeto si se encuentra, o null si no existe
     */
    public Objeto buscarObjetoHabitacion(String o){

        return objetos.stream()
                .filter(objeto -> objeto.getNombre().equalsIgnoreCase(o))
                .findFirst().orElse(null); // No se encontró el objeto
    }

    /**
     * Quita un objeto de la habitación.
     *
     * @param o Objeto a eliminar
     */
    public void quitarObjetoHabitacion(Objeto o){
        objetos.remove(o);
    }

    /**
     * Cuenta el número de objetos presentes en la habitación.
     *
     * @return número de objetos no nulos
     */
    public int contarObjetosHabitacion(){

        return objetos.size();
    }

    /**
     * Cuenta el número de objetos que pueden ser añadidos al inventario.
     *
     * @return número de objetos inventariables
     */
    public long contarObjetosInventariablesHabitacion(){

        return objetos.stream()
                .filter(objeto -> objeto instanceof Inventariable).count();
    }

    /**
     * Cuenta el número de contenedores presentes en la habitación.
     *
     * @return número de objetos que son instancias de Contenedor
     */
    public long contarContenedoresHabitacion(){

        return objetos.stream()
                .filter(objeto -> objeto instanceof  Contenedor).count();
    }

    /**
     * Muestra los objetos en la habitación
     * @return el string con todos los objetos
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
     *Metodo para mostrar los objetos inventariables
     * @return los objetos inventariables
     */
    public String  mostrarObjetosInventariables(){
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
     *Metodo para mostrar los contenedores
     * @return los contenedores
     */
    public String  mostrarContenedores(){
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
