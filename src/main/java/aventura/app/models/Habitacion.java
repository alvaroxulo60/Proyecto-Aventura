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

    /**
     * Metodo para añadir un objeto al array de objetos en la habitación
     * @param objeto
     */
    public void añadirObjetosHabitacion(Objeto objeto){
        for (int i = 0; i < objetos.length; i++) {
            if (objetos[i]==null){
                objetos[i]=objeto;
                break;
            }
        }
    }

    /**
     * Buscar el objeto en la habitación
     * @param o Nombre del objeto a buscar
     * @return El propio objeto si lo ha encontrado o null si no
     */
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

    /**
     * Quitar el objeto de la habitación una vez guardado
     * @param o objeto a eliminar
     */
    public void quitarObjetoHabitacion(Objeto o){
        for (int i = 0; i < objetos.length; i++) {
            if (objetos[i] != null && objetos[i].equals(o)){
                objetos[i]= null;
                return;
            }
        }
    }

    /**
     * Contar objetos en la habitación
     * @return El número de objetos
     */
    public int contarObjetosHabitacion(){
        int contador = 0;
        for (int i = 0; i < objetos.length; i++) {
            if (objetos[i]!= null){
                contador++;
            }
        }
        return contador;
    }
}
