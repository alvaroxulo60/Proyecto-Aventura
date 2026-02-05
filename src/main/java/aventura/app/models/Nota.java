package aventura.app.models;

import aventura.app.interfaces.Leible;

public class Nota extends Item implements Leible {

    private String contenido;

    public Nota(String nombre, String descripcion, boolean visible, String contenido) {
        super(nombre, descripcion, visible);
        this.contenido = contenido;
    }

    @Override
    public String leer() {
        return contenido;
    }
}
