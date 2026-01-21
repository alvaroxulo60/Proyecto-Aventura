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
}
