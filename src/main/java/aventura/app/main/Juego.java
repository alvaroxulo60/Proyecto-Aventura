package aventura.app.main;

import aventura.app.io.MiEntradaSalida;

import java.util.Scanner;

/*
 * Clase principal del juego "Tu Propia Aventura".
 * Esqueleto para la Misión 1 (UD1-UD3).
 * VUESTRO TRABAJO es rellenar todos los TODO
 */
public class Juego {

    // --- NÚCLEO: Definición de Datos (FASE 1) ---
    // Esta parte os la damos HECHA. Es el "contrato" del núcleo.

    //Creo un String para darle una introducción al usuario de la historia del juego

    private static String descripcionJuego = "Empiezas en una aldea tranquila, Kael el Comerciante, el cual conoces muy bien, muy amablemente te ofrece sin coste una poción misteriosa, según él esa poción te volverá el mejor alumno de la academia de la luz y la sombra, te llevas la poción con gusto y mientras das un paseo te la tomas para volverte el mejor de todos.\n" +
            "A los segundos después de tomarla te empiezas a sentir mareado y se te nubla la vista hasta que finalmente te desplomas en el suelo.\n" +
            "Te despiertas en un lugar familiar, no sabes como llegaste a ahí, ni cuánto tiempo llevas ahí.\n";

    // El mapa de habitaciones.
    // TODO: (Skin) ¡Rellenad esto con vuestras descripciones!

    //Creo un array habitaciones con las descripciones de cada habitación
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

    //Creo una matriz objetosMapa, donde se introducen los objetos que hay que cada habitación
    private static String[][] objetosMapa = {
            {"El tomo de las sombras", null},           // Objetos en Cuarto
            {"Llave kitsune", null},           // Objetos en Aula B1
            {null, null},      // Objetos en Sótano
    };

    //Creo una matriz descricionObjeto, donde se introducen las descripciones de cada objeto
    private static String[][] descripcionObjeto = {{"Un libro algo desgastado, parece importante pero está cerrado. Al examinarlo con cuidado notas una especie de hueco donde parece ir una figura.", null} //Cuarto
            , {"Una figura de un zorro con 9 colas, parece que se puede poner en algún lugar.", null} //Aula B1
            , {null, null}}; //Sótano


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

        //Muestra la descripción general del juego
        System.out.println(descripcionJuego);
        //Muestra la descripción de la primera habitación
        System.out.println(habitaciones[habitacionActual]);


        //Iniciar el bucle principal del juego (game loop)
        while (jugando) {

            //Leer el comando del usuario por teclado
            System.out.print("\n> ");
            String comando = MiEntradaSalida.leerLinea("¿Qué quieres hacer a continuación?: ");

            /*
            Crear un 'switch' o una estructura 'if-else if'
             para procesar el 'comando' del usuario.
             Debe gestionar como mínimo: "ayuda", "mirar", "inventario",
             "ir derecha", "ir izquierda", "coger [objeto]" y "salir".
             */

            switch (comando.toLowerCase()) {
                case "ir derecha":
                    irDerecha();
                    break;
                case "ir izquierda":
                    irIzquierda();
                    break;
                case "inventario":
                    verInventario();
                    break;
                case "coger objeto":
                    cogerObjeto();
                    break;
                case "ayuda":
                    ayuda();
                    break;
                case "mirar":
                    mirarObjeto();
                    break;
                case "salir":
                    jugando = false;
                    break;
                default:
                    ayuda();
                    break;
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

    /**
     * Métod0 para ir a la habitación de la derecha
     */
    private static void irDerecha() {
        if (habitacionActual + 1 != habitaciones.length) {
            habitacionActual += 1;
            System.out.println(habitaciones[habitacionActual]);
        } else
            System.out.println("No es posible ir a la derecha");
    }

    /**
     * Métod0 para ir a la habitación de la izquierda
     */
    private static void irIzquierda() {
        if (habitacionActual - 1 > 0) {
            habitacionActual -= 1;
            System.out.println(habitaciones[habitacionActual]);
        } else
            System.out.println("No es posible ir a la izquierda");
    }

    /**
     * Ver tus objetos numerados en el inventario.
     */
    private static void verInventario() {
        int contador = 0;
        for (int i = 0; i < inventario.length; i++) {
            if (inventario[i] != null) {
                contador++;
            }
        }
        if (contador == 0) {
            System.out.println("No tienes ningún objeto en el inventario.");
        } else {
            for (int i = 0; i < inventario.length; i++) {
                if (inventario[i] != null) {
                    System.out.println(i + 1 + ": " + inventario[i] + "\n");

                }
            }
        }
    }

    /**
     * Metodo para recoger objetos de la habitación en la que estas.
     */
    private static void cogerObjeto() {
        if (contarObjetosHabitacion(habitacionActual) > 0) {
            MiEntradaSalida.mostrarOpcionesSinNulos("Objetos en la sala: ", objetosMapa[habitacionActual]);
            int objeto = MiEntradaSalida.leerEnteroRango("Introduce el número correspodiente: ", 1, contarObjetosHabitacion(habitacionActual));

            int indiceObjeto = indiceObjetoNoNuloNumeroX(habitacionActual, objeto);

            guardarEnInventario(indiceObjeto);
        } else System.out.println("No queda ningún objeto en la sala de importancia.\n");
    }

    /**
     * Metoodo para guardar objetos en el inventario
     *
     * @param indiceObjeto indice del objeto en el array
     */
    private static void guardarEnInventario(int indiceObjeto) {
        int ocupados = 0;
        for (int i = 0; i < inventario.length; i++) {
            if (inventario[i] != null) ocupados++;
        }
        for (int i = 0; i < inventario.length; i++) {
            if (inventario[i] == null) {
                inventario[i] = objetosMapa[habitacionActual][indiceObjeto];
                objetosMapa[habitacionActual][indiceObjeto] = null;
                descripcionObjeto[habitacionActual][indiceObjeto] = null;
                System.out.println("¡Objeto guardado!\n");
                return;
            } else if (inventario[i] != null) {
                if (ocupados == inventario.length) {
                    System.out.println("No tienes espacio en el inventario");
                    return;
                }
                continue;
            }
        }
    }

    /**
     * Coger indice verdadero del objeto en la habitación
     *
     * @param habitacion habitacion a ver
     * @param x          posicion del objeto
     * @return posicion del objeto en el array
     */
    private static int indiceObjetoNoNuloNumeroX(int habitacion, int x) {
        int numObjetosNoNulos = 0;
        for (int i = 0; i < objetosMapa[habitacion].length; i++) {
            if (objetosMapa[habitacion][i] != null) {
                numObjetosNoNulos++;

                if (x == numObjetosNoNulos) {
                    return i;
                }
            }
        }
        return -1;
    }

    /**
     * Mirar objeto que haya en la habitación y mostrar su descripción
     */
    private static void mirarObjeto() {
        int contador = 0;
        for (int i = 0; i < descripcionObjeto[habitacionActual].length; i++) {
            if (descripcionObjeto[habitacionActual][i] != null) {
                contador += 1;
                System.out.println(i + 1 + ": " + descripcionObjeto[habitacionActual][i]);
            }
        }

        if (contador == 0) {
            System.out.println("No hay objetos en esta habitación.");
        }
    }

    /**
     * Mostrar los posibles comandos mientras el juego esta en uso
     */
    private static void ayuda() {
        System.out.print("====================AYUDA====================\n ");
        System.out.print(">ir derecha \n ");
        System.out.print(">ir izquierda \n ");
        System.out.print(">mirar \n ");
        System.out.print(">coger objeto \n ");
        System.out.print(">inventario \n ");
        System.out.print(">salir \n ");
        System.out.print("=============================================\n");
    }

    /**
     * Contar objetos de la habitacion
     *
     * @param habitacion habitacion a mirar
     * @return objetos en la habitación
     */
    private static int contarObjetosHabitacion(int habitacion) {
        int contador = 0;
        for (int i = 0; i < objetosMapa[habitacion].length; i++) {
            if (objetosMapa[habitacion][i] != null) {
                contador++;
            }
        }
        return contador;
    }
}