package aventura.app.models;

/**
 * Clase abstracta Entidad que representa cualquier objeto con un nombre
 * y una descripción dentro del juego o la aplicación.
 * Sirve como clase base para otros objetos más específicos.
 */
public abstract class Entidad {

    // Nombre de la entidad
    private String nombre;

    // Descripción de la entidad
    private String descripcion;

    /**
     * Constructor de la clase Entidad.
     *
     * @param nombre Nombre de la entidad
     * @param descripcion Descripción de la entidad
     */
    public Entidad(String nombre, String descripcion) {
        this.nombre = nombre; // Inicializa el nombre
        this.descripcion = descripcion; // Inicializa la descripción
    }

    /**
     * Devuelve la descripción de la entidad.
     *
     * @return descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Devuelve el nombre de la entidad.
     *
     * @return nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Devuelve una representación en String de la entidad.
     * Aquí simplemente se devuelve el nombre.
     *
     * @return nombre de la entidad
     */
    @Override
    public String toString() {
        return nombre;
    }

    /**
     * Compara esta entidad con otro objeto.
     * Dos entidades se consideran iguales si son de la misma clase
     * y tienen el mismo nombre.
     *
     * @param o objeto a comparar
     * @return true si son iguales, false en caso contrario
     */
    @Override
    public boolean equals(Object o) {
        // Si el objeto es null o no es de la misma clase, no son iguales
        if (o == null || getClass() != o.getClass()) return false;

        Entidad entidad = (Entidad) o;
        return nombre.equals(entidad.nombre); // Comparación por nombre
    }

    /**
     * Genera un código hash basado en el nombre de la entidad.
     * Es importante que sea coherente con equals.
     *
     * @return hash code de la entidad
     */
    @Override
    public int hashCode() {
        return nombre.hashCode();
    }

}
