package aventura.app.interfaces;
import aventura.app.models.Objeto;

public interface Combinable {
    //Devuelve el nuevo objeto que se ha creado con el resultado de dos objetos combinados que tenías en tú invetario

    Objeto combinar(Objeto  otro);
}
