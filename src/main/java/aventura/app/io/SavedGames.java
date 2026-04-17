package aventura.app.io;

import aventura.app.exceptions.CargadorException;
import aventura.app.exceptions.SaveException;
import aventura.app.models.AventuraConfig;
import aventura.app.models.Objeto;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.stream.Stream;

public class SavedGames {

    private static Gson gson;


    public static void guardarPartida(String s, AventuraConfig av) throws SaveException {
        getGson();
        try {
            CargarProperties cp = CargarProperties.getInstance();


            Path saves = rutaADirectorioDePartidasGuardadas();

            if (!Files.exists(saves)) {
                Files.createDirectories(saves);
            }


            if (!s.endsWith(".json")) {
                s = s.concat(".json");
            }

            Path archivoDestino = saves.resolve(s).normalize();

            String json = gson.toJson(av);

            Files.writeString(archivoDestino, json, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
        } catch (CargadorException | IOException e) {
            throw new SaveException("No se ha podido guardar la partida");
        }

    }

    public static AventuraConfig cargarPartida() throws CargadorException, SaveException {
        Path saves = SavedGames.rutaADirectorioDePartidasGuardadas();

        if (!Files.exists(saves)) {
            throw new CargadorException("El directorio de guardado no existe.");
        }

        try (Stream<Path> stream = Files.list(saves)) {
            List<Path> partidas = stream.filter(Files::isRegularFile).toList();

            if (partidas.isEmpty()) {
                throw new SaveException("No hay ninguna partida guardada");
            }

            // Mostrar partidas disponibles
            System.out.println("Partidas encontradas:");
            partidas.forEach(p -> System.out.println("- " + p.getFileName()));

            String nombreArchivo = MiEntradaSalida.leerLinea("Introduce el nombre del archivo: \n");
            Path archivoSeleccionado = saves.resolve(nombreArchivo);

            if (!Files.exists(archivoSeleccionado)) {
                throw new SaveException("El archivo especificado no existe.");
            }

            // Carga de datos
            CargadorAventura ca = new CargadorAventura();

            return ca.CargarPartidaGuardada(archivoSeleccionado);

        } catch (IOException | CargadorException e) {
            throw new SaveException("Error al procesar la carga: " + e.getMessage());
        }
    }

    public static boolean borrarPartida() throws SaveException, CargadorException, IOException {
        Path saves = rutaADirectorioDePartidasGuardadas();

        if (!Files.exists(saves)) {
            Files.createDirectories(saves);
        }

        try (Stream<Path> stream = Files.list(saves)) {
            List<Path> partidas = stream.filter(Files::isRegularFile).toList();

            if (partidas.isEmpty()) {
                throw new SaveException("No hay partidas guardadas");
            }

            // Mostrar partidas disponibles
            System.out.println("Partidas encontradas:");
            partidas.forEach(p -> System.out.println("- " + p.getFileName().toString().split("\\.")[0]));

            String nombreArchivo = MiEntradaSalida.leerLinea("Introduce el nombre del archivo que quieras borrar: \n");
            Path archivoSeleccionado = saves.resolve(nombreArchivo.concat(".json"));

            if (Files.deleteIfExists(archivoSeleccionado)) {
                return true;
            }

        } catch (IOException e) {
            throw new SaveException("Error: " + e.getMessage());
        }
        return false;
    }

    private static Path rutaADirectorioDePartidasGuardadas() throws CargadorException {
        CargarProperties cp = CargarProperties.getInstance();

        String base = cp.get("directorio.base.juego");
        String subDir = cp.get("juego.saves.games.dir");

        if (base == null || subDir == null) {
            throw new CargadorException("Faltan claves de configuración para las rutas.");
        }

        return Path.of(base).resolve(subDir);
    }

    private static Gson getGson() {
        if (gson == null) {
            gson = new GsonBuilder().setPrettyPrinting().registerTypeAdapter(Objeto.class, new ObjetoAdapter()).create();
        }
        return gson;
    }
}
