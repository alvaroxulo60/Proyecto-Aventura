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

    private CargarProperties() throws CargadorException {
        pop = new Properties();
        nombreArchivo = "config.properties";

        try (InputStream in = new FileInputStream(nombreArchivo)) {

            pop.load(in);
        } catch (IOException e) {
            throw new CargadorException("No se puede cargar");
        }
    }


    public String get(String propiedad) {
        return entidad.get(propiedad);
    }

    public static CargarProperties getInstance() throws CargadorException {
        if (CargarProperties.entidad == null){
            try {
                entidad = new CargarProperties();
            } catch (CargadorException e) {
                throw new CargadorException("No se ha podido crear el objeto");
            }
        }
        return entidad;
    }
}
