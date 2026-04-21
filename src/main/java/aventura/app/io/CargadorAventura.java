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
    }

    /**
     * Conseguir las rutas para la configuración default del juego
     * @throws CargadorException
     */
    public void conseguirRutas() throws CargadorException {


        try {
            // Utilizamos la clase para poder coger las properties
            CargarProperties cp = CargarProperties.getInstance();

            this.directorioBase = Path.of(cp.get("directorio.base.juego"));
            this.mundoInicial = directorioBase.resolve(Path.of(cp.get("juego.app.data"))).resolve(Path.of(cp.get("juego.archivo.base")));//Cogemos la ruta hacia el archivo de configuración del juego

            //Comprobamos que este todo correcto con las ruta al archivo
            if (!Files.exists(directorioBase) || !Files.isDirectory(directorioBase)) {
                Files.createDirectories(directorioBase);
            }
            //Comprobamos lo mismo con el fichero
            if (!Files.exists(mundoInicial) || !Files.isRegularFile(mundoInicial)) {
                throw new CargadorException("El archivo de configuración del juego no existe: " + mundoInicial);
            }

        } catch (IOException e) {
            throw new CargadorException("No se ha podido completar el proceso de cargado");
        }
    }

    /**
     * Metodo para devolver la configuración default de la aventura
     * @return Un AventuraConfig con toda la información del juego
     * @throws CargadorException
     */
    public AventuraConfig CargarMundoBase() throws CargadorException {
        conseguirRutas();
        try {
            return gson.fromJson(Files.newBufferedReader(mundoInicial), AventuraConfig.class);//Convertimos el Json a Aventura config y lo devolvemos
        } catch (IOException e) {
            throw new CargadorException("No ha sido posible cargar el juego");
        }
    }

    /**
     * Cargar la aventura desde un archivo de guardado
     * @param partida ruta hacia ese archivo de guardado
     * @return toda la información de esa partida guardada en un AventuraConfig
     * @throws CargadorException
     */
    public AventuraConfig CargarPartidaGuardada(Path partida) throws CargadorException {
        try {
            return gson.fromJson(Files.newBufferedReader(partida), AventuraConfig.class);
        } catch (IOException e) {
            throw new CargadorException("No ha sido posible cargar el juego");
        }
    }


}
