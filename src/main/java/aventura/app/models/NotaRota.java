package aventura.app.models;

import aventura.app.exceptions.CombinarException;
import aventura.app.interfaces.Combinable;

public class NotaRota extends Item implements Combinable {

    public NotaRota(String nombre, String descripcion, boolean visible) {
        super(nombre, descripcion, visible);
    }


    @Override
    public Objeto combinar(Objeto otro) throws CombinarException {
        if (otro != null){
            if(otro instanceof NotaRota){
                return new Nota("Nota arreglada", "La nota resultante al juntar las dos notas rotas",true,"Has de continuar con tu travesía para poder aprender el poder latente que hay en tí. Yo, tú hermano, he preparado esta aventura para que comprendas lo difícil que es ser mago, si la superas te convertirás en un mago espléndido, sino morirás en el intento jajajajajaja.");
            }
        }
        throw new CombinarException("No es posible combinar estos objetos");
    }
}
