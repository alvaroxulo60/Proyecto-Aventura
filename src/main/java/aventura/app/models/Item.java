package aventura.app.models;

import aventura.app.interfaces.Inventariable;

public class Item extends Objeto implements Inventariable {
    public Item(String nombre, String descripcion, boolean visible) {
        super(nombre, descripcion, visible);
    }
}
