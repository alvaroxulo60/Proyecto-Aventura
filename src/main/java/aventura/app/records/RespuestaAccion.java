package aventura.app.records;

/**
 * Record RespuestaAccion que representa el resultado de una acción en el juego.
 * Contiene:
 *  - esExito: indica si la acción fue exitosa o no
 *  - mensaje: mensaje descriptivo del resultado
 *
 * Los records en Java son inmutables por defecto, y generan automáticamente
 * los métodos getters, equals, hashCode y toString.
 */
public record RespuestaAccion(boolean esExito, String mensaje) {
    // No se necesitan métodos adicionales, los records generan automáticamente:
    // - esExito() para obtener el valor de esExito
    // - mensaje() para obtener el valor de mensaje
    // - equals(), hashCode() y toString() para facilitar comparaciones y depuración
}
