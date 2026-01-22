package aventura.app.models;

public class Habitacion {

    private final String DESCRIPCION;
    private final static int TAMAÑO_ARRAY_OBJETOS = 8;
    private Objeto[] objetos;

    public Habitacion(String DESCRIPCION) {
        this.DESCRIPCION = DESCRIPCION;
        this.objetos = new Objeto[TAMAÑO_ARRAY_OBJETOS];
    }

    public String getDESCRIPCION() {
        return DESCRIPCION;
    }

    public Objeto[] getObjetos() {
        return objetos;
    }

    public void añadirObjetosHabitacion(Objeto objeto){
        for (int i = 0; i < objetos.length; i++) {
            if (objetos[i]==null){
                objetos[i]=objeto;
            }
        }
    }

    public Objeto buscarObjetoHabitacion(String o){
        for (int i = 0; i < objetos.length; i++) {
            if (objetos[i]!= null){
                if (objetos[i].getNombre().equalsIgnoreCase(o)){
                    return objetos[i];
                }
            }
        }
        return null;
    }

    public void quitarObjetoHabitacion(Objeto o){
        for (int i = 0; i < objetos.length; i++) {
            if (objetos[i].equals(o)){
                objetos[i]= null;
                return;
            }
        }
    }
}
