package aventura.app.io;

import aventura.app.exceptions.CargadorException;
import aventura.app.models.AventuraConfig;
import aventura.app.models.Objeto;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;

public class CargadorAventura {
    private Gson gson;

    private Path directorioBase;

    private Path mundoInicial;

    public CargadorAventura() throws CargadorException {
        gson = new GsonBuilder().setPrettyPrinting().registerTypeAdapter(Objeto.class, new ObjetoAdapter()).create();
        conseguirRutas();
    }

    public void conseguirRutas() throws CargadorException {


        try{

            CargarProperties cp = CargarProperties.getInstance();

            this.directorioBase = Path.of(cp.get("directorio.base.juego"));
            this.mundoInicial = directorioBase.resolve(Path.of(cp.get("juego.archivo.base")));

            if (!Files.exists(directorioBase) || !Files.isDirectory(directorioBase)) {
                Files.createDirectories(directorioBase);
            }

            if (!Files.exists(mundoInicial) || !Files.isRegularFile(mundoInicial)) {
                throw new CargadorException("El archivo de configuración del juego no existe: " + mundoInicial);
            }

        }catch (IOException e){
            throw new CargadorException("No se ha podido completar el proceso de cargado");
        }
    }

    public AventuraConfig CargarMundoBase() throws CargadorException {
        try {
            return gson.fromJson(Files.newBufferedReader(mundoInicial),AventuraConfig.class);
        } catch (IOException e) {
            throw new CargadorException("No ha sido posible cargar el juego");
        }
    }


}
