package aventura.app.models;

import aventura.app.interfaces.Inventariable;

public class Llave extends Item {

    private final String CODIGO_SEGURIDAD;

    public Llave(String nombre, String descripcion, boolean visible, String CODIGO_SEGURIDAD) {
        super(nombre, descripcion, visible);
        this.CODIGO_SEGURIDAD = CODIGO_SEGURIDAD;
    }

    public String getCODIGO_SEGURIDAD() {
        return CODIGO_SEGURIDAD;
    }
}
