package aventura.app.io;

import aventura.app.exceptions.CargadorException;
import aventura.app.models.AventuraConfig;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;
import java.util.PropertyPermission;

public class CargadorAventura {
    private Gson gson;

    private Path ubicacionJuego;

    private Path informacionJuego;

    public CargadorAventura() throws CargadorException {
        gson = new GsonBuilder().setPrettyPrinting().registerTypeAdapter(Object.class, new ObjetoAdapter()).create();
        conseguirRutas();

    }

    public void conseguirRutas() throws CargadorException {
        Properties pop = new Properties();

        try(InputStream in = new FileInputStream("config.properties")){

            pop.load(in);

            this.ubicacionJuego = Path.of(pop.getProperty("directorio.base.juego"));
            this.informacionJuego = Path.of(pop.getProperty("juego.archivo.base"));

            if (!Files.exists(ubicacionJuego) || !Files.isDirectory(ubicacionJuego)) {
                Files.createDirectories(ubicacionJuego);
            }

            if (!Files.exists(informacionJuego) || !Files.isRegularFile(informacionJuego)) {
                throw new CargadorException("El archivo de configuración del juego no existe: " + informacionJuego);
            }

        }catch (IOException e){
            throw new CargadorException("No se ha podido completar el proceso de cargado");
        }
    }

    public AventuraConfig CargarMundoBase() throws CargadorException {
        try {
            return gson.fromJson(Files.newBufferedReader(informacionJuego),AventuraConfig.class);
        } catch (IOException e) {
            throw new CargadorException("No ha sido posible cargar el juego");
        }
    }
}
