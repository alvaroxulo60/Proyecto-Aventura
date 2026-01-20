package aventura.app.interfaces;

import aventura.app.records.RespuestaAccion;

public interface Abrible {
    //Esta interfaz define el contrato para los objetos o puertas que se abren
    RespuestaAccion abrir(Llave llave);
    boolean estaAbierto();
}
