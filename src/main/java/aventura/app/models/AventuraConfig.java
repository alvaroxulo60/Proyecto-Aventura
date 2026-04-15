package aventura.app.models;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AventuraConfig {

    private String descripcionDelJuego;

    private Map<String,Habitacion> habitaciones;

    public void setDescripcionDelJuego(String descripcionDelJuego) {
        this.descripcionDelJuego = descripcionDelJuego;
    }

    public void setHabitaciones(List<Habitacion> habitaciones) {
        this.habitaciones = new HashMap<>();
        for (Habitacion h: habitaciones) {
            this.habitaciones.put(h.getNOMBRE_HABITACION(),h);
        }
    }
}
