package aventura.app.models;

public class Objeto extends Entidad{
    private boolean visible;

    public Objeto(String nombre, String descripcion, boolean visible) {
        super(nombre, descripcion);
        this.visible = visible;
    }


}
