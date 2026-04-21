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

/**
 * Clase encargada de la gestión de persistencia de las partidas.
 * Permite guardar, cargar y borrar el estado del juego en formato JSON.
 */
public class SavedGames {

    // Instancia única de Gson para la serialización/deserialización
    private static Gson gson;

    /**
     * Guarda el estado actual de la aventura en un archivo JSON.
     * * @param s Nombre del archivo (con o sin extensión).
     *
     * @param av Objeto de configuración que contiene el estado del juego.
     * @throws SaveException Si ocurre un error al escribir o configurar la ruta.
     */
    public static void guardarPartida(String s, AventuraConfig av) throws SaveException {
        getGson(); // Inicializa el serializador si no existe
        try {
            // Obtenemos la ruta del directorio de guardado desde las propiedades
            Path saves = rutaADirectorioDePartidasGuardadas();

            // Crea los directorios si no existen físicamente
            if (!Files.exists(saves)) {
                Files.createDirectories(saves);
            }

            // Asegura que el nombre del archivo termine en .json
            if (!s.endsWith(".json")) {
                s = s.concat(".json");
            }

            // Resuelve la ruta completa y normaliza para evitar problemas de redundancia y evitar que salga a capas superiores
            Path archivoDestino = saves.resolve(s).normalize();

            // Convierte el objeto de la partida a una cadena JSON
            String json = gson.toJson(av);

            // Escribe el string en el archivo (Crea nuevo o sobrescribe si ya existe)
            Files.writeString(archivoDestino, json, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);

        } catch (CargadorException | IOException e) {
            throw new SaveException("No se ha podido guardar la partida");
        }
    }

    /**
     * Muestra las partidas disponibles y permite al usuario seleccionar una para cargar.
     * * @return El objeto AventuraConfig con los datos cargados.
     *
     * @throws CargadorException Si el directorio no existe o hay problemas de configuración.
     * @throws SaveException     Si no hay archivos o el archivo seleccionado es inválido.
     */
    public static AventuraConfig cargarPartida() throws CargadorException, SaveException {
        Path saves = SavedGames.rutaADirectorioDePartidasGuardadas();

        if (!Files.exists(saves)) {
            throw new CargadorException("El directorio de guardado no existe.");
        }

        // Abre un Stream para listar los archivos del directorio (autocerrable con try-with-resources)
        try (Stream<Path> stream = Files.list(saves)) {
            List<Path> partidas = stream.filter(Files::isRegularFile).toList();

            if (partidas.isEmpty()) {
                throw new SaveException("No hay ninguna partida guardada");
            }

            // Listado visual para el usuario
            System.out.println("Partidas encontradas:");
            partidas.forEach(p -> System.out.println(nombreDeArchivosSinExtension(p.getFileName().toString())));

            // Interacción por consola para obtener el nombre del archivo
            String nombreArchivo = MiEntradaSalida.leerLinea("Introduce el nombre del archivo: \n");
            Path archivoSeleccionado = saves.resolve(nombreArchivo.concat(".json"));

            if (!Files.exists(archivoSeleccionado)) {
                throw new SaveException("El archivo especificado no existe.");
            }

            // Delegamos la lógica de carga pesada a la clase CargadorAventura
            CargadorAventura ca = new CargadorAventura();
            return ca.CargarPartidaGuardada(archivoSeleccionado);

        } catch (IOException | CargadorException e) {
            throw new SaveException("Error al procesar la carga: " + e.getMessage());
        }
    }

    /**
     * Lista las partidas y elimina el archivo seleccionado por el usuario.
     * * @return true si el borrado fue exitoso, false en caso contrario.
     */
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

            System.out.println("Partidas encontradas:");
            // Muestra nombres sin la extensión .json para mayor limpieza visual
            partidas.forEach(p -> System.out.println(nombreDeArchivosSinExtension(p.getFileName().toString())));

            String nombreArchivo = MiEntradaSalida.leerLinea("Introduce el nombre del archivo que quieras borrar: \n");
            Path archivoSeleccionado = saves.resolve(nombreArchivo.concat(".json"));

            // Elimina el archivo si existe y devuelve el resultado
            return Files.deleteIfExists(archivoSeleccionado);

        } catch (IOException e) {
            throw new SaveException("Error: " + e.getMessage());
        }
    }

    /**
     * Obtiene la ruta del directorio de guardado basándose en el archivo de propiedades.
     * * @return Path que representa la carpeta de partidas guardadas.
     */
    private static Path rutaADirectorioDePartidasGuardadas() throws CargadorException {
        CargarProperties cp = CargarProperties.getInstance();

        // Recupera rutas base y específicas de la configuración
        String base = cp.get("directorio.base.juego");
        String subDir = cp.get("juego.saves.games.dir");

        if (base == null || subDir == null) {
            throw new CargadorException("Faltan claves de configuración para las rutas.");
        }

        // Combina ambas rutas (ej: "C:/Juego" + "saves")
        return Path.of(base).resolve(subDir);
    }

    /**
     * Patrón +Singleton para el objeto Gson.
     * Configura el formateo visual (Pretty Printing) y adaptadores personalizados.
     */
    private static Gson getGson() {
        if (gson == null) {
            gson = new GsonBuilder()
                    .setPrettyPrinting() // Hace que el JSON sea vea bonito
                    .registerTypeAdapter(Objeto.class, new ObjetoAdapter()) // Adaptador para polimorfismo de Objetos
                    .create();
        }
        return gson;
    }

    /**
     * Metodo para separar el nombre de los archivos y mostrarlos sin extensión
     *
     * @param nombre nombre del archivo con extensión
     * @return nombre sin extensión
     */
    private static String nombreDeArchivosSinExtension(String nombre) {
        return "- " + nombre.split("\\.")[0];
    }
}