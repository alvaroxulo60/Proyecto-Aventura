package aventura.app.models;

import aventura.app.interfaces.Inventariable;

public class TomoDeLasSombras extends Objeto implements Inventariable {
    public TomoDeLasSombras(String nombre, String descripcion, boolean visible) {
        super(nombre, descripcion, visible);
    }
}
