package aventura.app.models;

import aventura.app.interfaces.Inventariable;

/**
 * Clase Habitacion que representa un espacio donde pueden colocarse objetos.
 * Gestiona un array fijo de objetos, permite añadir, buscar, eliminar y contar objetos.
 */
public class Habitacion {

    // Descripción de la habitación
    private final String DESCRIPCION;

    // Tamaño máximo del array de objetos en la habitación
    private final static int TAMAÑO_ARRAY_OBJETOS = 8;

    // Array que almacena los objetos de la habitación
    private Objeto[] objetos;

    /**
     * Constructor de la clase Habitacion.
     *
     * @param DESCRIPCION Descripción de la habitación
     */
    public Habitacion(String DESCRIPCION) {
        this.DESCRIPCION = DESCRIPCION;
        this.objetos = new Objeto[TAMAÑO_ARRAY_OBJETOS]; // Inicializa el array de objetos
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
    public Objeto[] getObjetos() {
        return objetos;
    }

    /**
     * Añade un objeto al primer espacio disponible del array de objetos.
     *
     * @param objeto Objeto a añadir
     */
    public void añadirObjetosHabitacion(Objeto objeto){
        for (int i = 0; i < objetos.length; i++) {
            if (objetos[i] == null){
                objetos[i] = objeto; // Inserta el objeto en la primera posición vacía
                break; // Sale del bucle después de añadirlo
            }
        }
    }

    /**
     * Busca un objeto en la habitación por su nombre.
     *
     * @param o Nombre del objeto a buscar
     * @return El objeto si se encuentra, o null si no existe
     */
    public Objeto buscarObjetoHabitacion(String o){
        for (int i = 0; i < objetos.length; i++) {
            if (objetos[i] != null){
                if (objetos[i].getNombre().equalsIgnoreCase(o)){ // Comparación ignorando mayúsculas/minúsculas
                    return objetos[i];
                }
            }
        }
        return null; // No se encontró el objeto
    }

    /**
     * Quita un objeto de la habitación.
     *
     * @param o Objeto a eliminar
     */
    public void quitarObjetoHabitacion(Objeto o){
        for (int i = 0; i < objetos.length; i++) {
            if (objetos[i] != null && objetos[i].equals(o)){
                objetos[i] = null; // Elimina el objeto asignando null
                return; // Sale del método después de eliminar
            }
        }
    }

    /**
     * Cuenta el número de objetos presentes en la habitación.
     *
     * @return número de objetos no nulos
     */
    public int contarObjetosHabitacion(){
        int contador = 0;
        for (int i = 0; i < objetos.length; i++) {
            if (objetos[i] != null){
                contador++; // Incrementa por cada objeto presente
            }
        }
        return contador;
    }

    /**
     * Cuenta el número de objetos que pueden ser añadidos al inventario.
     *
     * @return número de objetos inventariables
     */
    public int contarObjetosInventariablesHabitacion(){
        int contador = 0;
        for (int i = 0; i < objetos.length; i++) {
            // Solo cuenta objetos no nulos que implementen la interfaz Inventariable
            if (objetos[i] != null && objetos[i] instanceof Inventariable){
                contador++;
            }
        }
        return contador;
    }

    /**
     * Cuenta el número de contenedores presentes en la habitación.
     *
     * @return número de objetos que son instancias de Contenedor
     */
    public int contarContenedoresHabitacion(){
        int contador = 0;
        for (int i = 0; i < objetos.length; i++) {
            // Solo cuenta objetos no nulos que sean de la clase Contenedor
            if (objetos[i] != null && objetos[i] instanceof Contenedor){
                contador++;
            }
        }
        return contador;
    }
}
