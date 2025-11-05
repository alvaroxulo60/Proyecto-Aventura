package aventura.app;

import java.util.Scanner;

/**
 * Clase principal del juego "Tu Propia Aventura".
 * Esqueleto para la Misión 1 (UD1-UD3).
 * VUESTRO TRABAJO es rellenar todos los TODO
 */
public class Juego {

    // --- NÚCLEO: Definición de Datos (FASE 1) ---
    // Esta parte os la damos HECHA. Es el "contrato" del núcleo.

    private static String descripcionJuego = "Empiezas en una aldea tranquila, Kael el Comerciante, el cual conoces muy bien, muy amable te ofrece sin coste una poción misteriosa, según él esa poción te volverá el mejor alumno de la academia de la luz y la sombra, te llevas la poción con gusto y mientras das un paseo te la tomas para volverte el mejor de todos.\n" +
            "A los segundos después de tomarla te empiezas a sentir mareado y se te nubla la vista hasta que finalmente te desplomas en el suelo.\n" +
            "Te despiertas en un lugar familiar, no sabes como llegaste a ahí, ni cuánto tiempo llevas ahí.\n";

    // El mapa de habitaciones.
    // TODO: (Skin) ¡Rellenad esto con vuestras descripciones!
    private static String[] habitaciones = {
            "Miras alrededor y te das cuenta de que es tu habitación pero a la vez que vas mirando más a detalle te das cuenta que hay cosas que no deberían estar ahí como un libro algo desgastado en tu estantería que no recuerdas haber comprado. \n" +
                    "Hay una puerta a la derecha.\n",  // Cuarto
            "Tambíen te resulta familiar, es el aula donde los alumnos de mayor grado dan sus clases de hechizos, pero lo extraño es que el aula esta del reves.\n" +
                    "En una de las estanterías que llega a tu altura notas una figurilla de Kitsune bastante llamativa. En esta sala hay una puerta a la derecha y otra a la izquierda.\n", // Aula B1
            "Llegas a un sótano donde apenas hay luz, no te suena de nada este sitio.\n" +
                    "Al mirar alrededor no ves nada de importancia pero a tu derecha ves unas escaleras que supones que es la salida de esta sala. Hay una puerta a la izquierda y las escaleras a la derecha\n", // Sótano
            // Borra las habitaciones y escribe las tuyas
    };

    // Los objetos que hay en cada habitación.
    // TODO: (Skin) Rellenad esto con vuestros objetos
    private static String[][] objetosMapa = {
            {"El tomo de las sombras", null},           // Objetos en Cuarto
            {"Llave kitsune", null},           // Objetos en Aula B1
            {null, null},      // Objetos en Sótano
    };

    // El inventario del jugador. Tamaño fijo.
    private static String[] inventario = new String[5];

    // Variable que guarda la posición actual del jugador
    private static int habitacionActual = 0; // Empezamos en la primera habitación

    // --- FIN DE LA DEFINICIÓN DE DATOS ---


    public static void main(String[] args) {
        // Puedes utilizar la clase MiEntradaSalida, que viviría en el paquete io
        Scanner scanner = new Scanner(System.in);
        boolean jugando = true;

        System.out.println("¡Bienvenido a 'TU PROPIA AVENTURA'!");
        System.out.println("------------------------------------------");

        // TODO 1a: Muestra la descripción general del juego
        System.out.println(descripcionJuego);
        // TODO 1b: Muestra la descripción de la primera habitación
        System.out.println(habitaciones[habitacionActual]);


        // TODO 2: Iniciar el bucle principal del juego (game loop)
        while (jugando) {

            // TODO 3: Leer el comando del usuario por teclado
            System.out.print("\n> ");
            String comando = MiEntradaSalida.leerTexto("¿Qué quieres hacer a continuación?");

            /*
            TODO 4: Crear un 'switch' o una estructura 'if-else if'
             para procesar el 'comando' del usuario.
             Debe gestionar como mínimo: "ayuda", "mirar", "inventario",
             "ir derecha", "ir izquierda", "coger [objeto]" y "salir".
             */

            switch (comando.toLowerCase()){
                case "ir derecha":
                    irDerecha();
                case "ir izquierda":
                    irIzquierda();
                case "inventario":
                    verInventario();
                case "coger objeto":
                    ;
                case "ayuda":
                    ;
                case "mirar":
                    ;
                case "salir":
                    jugando=false;
                case "":
                    ;
            }

        }

        System.out.println("¡Gracias por jugar!");

    }

    /*
    (Opcional - Buenas Prácticas)
    Si el 'switch' se vuelve muy grande, podéis crear métodos privados
    para organizar el código, por ejemplo:
    private static void procesarComandoCoger(String comando) { ... }
    private static void mostrarInfoHabitacion() { ... }
    */
    private static void irDerecha(){
        if (habitacionActual+1 > habitaciones.length){
            System.out.println("No es posible ir a la derecha");
        }
        else
            habitacionActual+=1;
        System.out.println(habitaciones[habitacionActual]);
    }

    private static void irIzquierda(){
        if (habitacionActual-1 < habitaciones.length){
            System.out.println("No es posible ir a la izquierda");
        }
        else
            habitacionActual-=1;
        System.out.println(habitaciones[habitacionActual]);
    }
    private static void verInventario(){
        for (String s : inventario) {
            if (s != null) {
                System.out.println(s + ",");
            }
        }
    }
}