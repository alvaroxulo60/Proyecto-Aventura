package aventura.app.models;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Clase de configuración de la aventura.
 * Almacena los datos globales, como la descripción general del juego
 * y la colección de todas las habitaciones disponibles.
 */
public class AventuraConfig {

    private static final Logger logger = LoggerFactory.getLogger(AventuraConfig.class);

    //Texto introductorio o descripción general de la partida
    private String descripcionDelJuego;

    //Mapa que almacena las habitaciones del juego.
    //Se registran ordenadamente por su nombre para permitir búsquedas y desplazamientos rápidos.
    private Map<String, Habitacion> habitaciones;

    private Jugador jugador;

    /**
     * Establece la descripción general o introducción del juego.
     *
     * @param descripcionDelJuego Texto descriptivo de la aventura
     */
    public void setDescripcionDelJuego(String descripcionDelJuego) {
        this.descripcionDelJuego = descripcionDelJuego;
        logger.info("Descripción general del juego cargada en la configuración.");
    }

    /**
     * Inicializa y carga el mapa de habitaciones a partir de una lista.
     * Convierte la lista en un HashMap, utilizando el nombre de la habitación
     * como clave (Key) para optimizar el rendimiento al buscar conexiones.
     *
     * @param habitaciones Lista de objetos Habitacion a registrar en el juego
     */
    public void setHabitaciones(List<Habitacion> habitaciones) {
        if (habitaciones == null) {
            logger.error("Error crítico: Se ha intentado cargar una lista de habitaciones nula.");
            this.habitaciones = new HashMap<>();
            return;
        }

        if (habitaciones.isEmpty()) {
            logger.warn("Advertencia: La lista de habitaciones proporcionada está vacía.");
        }

        this.habitaciones = new HashMap<>();
        for (Habitacion h : habitaciones) {
            this.habitaciones.put(h.getNOMBRE_HABITACION(), h);
        }

        logger.info("Mapa de habitaciones inicializado. Total de habitaciones registradas: {}", this.habitaciones.size());
    }

    public void setHabitaciones(Map<String, Habitacion> habitaciones) {
        this.habitaciones = habitaciones;
    }


    /**
     * Devuelve la descripción general del juego.
     *
     * @return Descripción de la aventura
     */
    public String getDescripcionDelJuego() {
        return descripcionDelJuego;
    }

    /**
     * Devuelve la colección de habitaciones mapeadas del juego.
     *
     * @return Mapa de habitaciones (Clave: Nombre de la habitación, Valor: Objeto Habitacion)
     */
    public Map<String, Habitacion> getHabitaciones() {
        return habitaciones;
    }

    public Jugador getJugador() {
        return jugador;
    }

    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }
}