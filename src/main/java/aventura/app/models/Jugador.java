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

    public Objeto buscarObjetoInventario(String  objeto){
        for (int i = 0; i < inventario.length; i++) {
            if (inventario[i] != null){
                if (inventario[i].getNombre().equalsIgnoreCase(objeto)){
                    return inventario[i];
                }
            }
        }
        return null;
    }

    public boolean guardarInventario(Objeto o){
        for (int i = 0; i < inventario.length; i++) {
            if (inventario[i]== null){
                inventario[i] = o;
                return true;
            }
        }
        return false;
    }
}
