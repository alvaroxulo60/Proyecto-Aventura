package aventura.app.models;

import aventura.app.interfaces.Abrible;
import aventura.app.records.RespuestaAccion;


public class Contenedor extends Objeto implements Abrible {

    private Objeto elemento;
    private final String CODIGO_SECRETO;
    private boolean estaAbierto;

    public Contenedor(String nombre, String descripcion, boolean visible, String CODIGO_SECRETO, Objeto elemento, boolean estaAbierto) {
        super(nombre, descripcion, visible);
        this.CODIGO_SECRETO = CODIGO_SECRETO;
        this.elemento = elemento;
        this.estaAbierto = estaAbierto;
    }

    @Override
    public RespuestaAccion abrir(Llave llave) {
        if(estaAbierto){
            return new RespuestaAccion(false, "Ya está abierto");
        }
        if (CODIGO_SECRETO == null){
            estaAbierto = true;
            return new RespuestaAccion(true,"Se ha abierto");
        }

        if(llave != null && llave.getCODIGO_SEGURIDAD().equals(CODIGO_SECRETO)){
            estaAbierto = true;
            return new RespuestaAccion(true,"Click, se ha abierto") ;
        }
        else {
            return new RespuestaAccion(false, "Necesitas una llave para abrir esto");
        }
    }

    @Override
    public boolean estaAbierto() {
        return estaAbierto;
    }

    public Objeto getElemento() {
        return elemento;
    }
}


