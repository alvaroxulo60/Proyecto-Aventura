package aventura.app.io;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.nio.file.Path;

public class CargadorAventura {
    private Gson gson;

    private Path ubicacionJuego;

    private Path informacionJuego;

    public CargadorAventura() {
        gson = new GsonBuilder().setPrettyPrinting().create();
    }
}
