package aventura.app.models;

public class Habitacion {

    private final String DESCRIPCION;
    private final static int TAMAÑO_ARRAY_OBJETOS = 8;
    private Objeto[] objetos;

    public Habitacion(String DESCRIPCION, Objeto[] objetos) {
        this.DESCRIPCION = DESCRIPCION;
        this.objetos = objetos;
    }

}
