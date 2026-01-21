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

    public void setPosicionJugador(int posicionJugador) {
        this.posicionJugador = posicionJugador;
    }
}
