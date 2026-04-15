package aventura.app.io;

import aventura.app.exceptions.MigradorException;
import aventura.app.models.AventuraConfig;
import aventura.app.models.Habitacion;
import aventura.app.models.Objeto;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.Properties;

public class Migrador {

    public void migrarContenido(String descripcion, List<Habitacion> habitaciones) throws MigradorException {

        AventuraConfig av = new AventuraConfig();

        av.setHabitaciones(habitaciones);
        av.setDescripcionDelJuego(descripcion);

        Gson gson = new GsonBuilder().setPrettyPrinting().registerTypeAdapter(Objeto.class, new ObjetoAdapter()).create();

        String json = gson.toJson(av);

        Properties pop = new Properties();

        try(InputStream in = new FileInputStream("Config.properties")){

            pop.load(in);

            Path ruta = Path.of(pop.getProperty("juego.archivo.base"));

            Files.writeString(ruta,json, StandardOpenOption.CREATE);

        }catch (IOException e){
            throw new MigradorException("No se ha podido migrar correctamente");
        }
    }
}
