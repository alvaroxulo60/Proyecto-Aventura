package aventura.app.models;

public abstract class Entidad {

    private String nombre;
    private String descripcion;

    public Entidad(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        Entidad entidad = (Entidad) o;
        return nombre.equals(entidad.nombre);
    }

    @Override
    public int hashCode() {
        return nombre.hashCode();
    }
}
