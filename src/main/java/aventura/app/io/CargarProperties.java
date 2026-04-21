package aventura.app.io;

import aventura.app.exceptions.CargadorException;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.Properties;

public class CargarProperties {
    private static CargarProperties entidad;
    private Properties pop;
    private String nombreArchivo;

    /**
     * Iniciar la clase Properties para poder utilizarla
     * @throws CargadorException
     */
    private CargarProperties() throws CargadorException {
        pop = new Properties();
        nombreArchivo = "config.properties";

        try (InputStream in = new FileInputStream(nombreArchivo)) {

            pop.load(in);
        } catch (IOException e) {
            throw new CargadorException("No se puede cargar");
        }
    }

    /**
     * Conseguir una propiedad del archivo config
     * @param propiedad el nombre de la propiedad
     * @return la propiedad
     */
    public String get(String propiedad) {
        return pop.getProperty(propiedad);
    }

    /**
     * Metodo cargador de patrón singleton
     * @return Un cargarProperties si esta cargado en memoria, si no crea uno nuevo y lo devuelve
     * @throws CargadorException
     */
    public static CargarProperties getInstance() throws CargadorException {
        if (CargarProperties.entidad == null) {
            try {
                entidad = new CargarProperties();
            } catch (CargadorException e) {
                throw new CargadorException("No se ha podido crear el objeto");
            }
        }
        return entidad;
    }
}
