package aventura.app.models;

import java.util.Arrays;

public class Jugador extends Personaje{
    private static final int TAM_INV = 10;

    private int posicionJugador;
    private Objeto[] inventario;

    public Jugador() {
        posicionJugador = 0;
        inventario = new Objeto[TAM_INV];
    }

    public int getPosicionJugador() {
        return posicionJugador;
    }

    public Objeto[] getInventario() {
        return inventario;
    }

    public void setPosicionJugador(int posicionJugador) {
        this.posicionJugador = posicionJugador;
    }

    /**
     * Buscar el objeto en el inventario
     * @param objeto String con el nombre del objeto
     * @return el propio objeto si ha sido encontrado o null si no se ha encontrado
     */
    public Objeto buscarObjetoInventario(String objeto){
        for (int i = 0; i < inventario.length; i++) {
            if (inventario[i] != null){
                if (inventario[i].getNombre().equalsIgnoreCase(objeto)){
                    return inventario[i];
                }
            }
        }
        return null;
    }

    /**
     * Guardar un objeto nuevo en el inventario del jugador
     * @param o objeto a guardar
     * @return true o false dependiendo si se ha completado la acción o no
     */
    public boolean guardarInventario(Objeto o){
        for (int i = 0; i < inventario.length; i++) {
            if (inventario[i]== null){
                inventario[i] = o;
                return true;
            }
        }
        return false;
    }

    /**
     * Metodo para mostrar el inventario del jugador
     * @return Un String con toda la información del inventario
     */
    public String verInventario(){
        StringBuilder inv = new StringBuilder();
        int contador = 1;
        for (int i = 0; i < inventario.length; i++) {
            if (inventario[i]!= null){
                inv.append(contador++).append(".").append(inventario[i].getNombre()).append(System.lineSeparator());
            }
        }
        return inv.toString();
    }
}
