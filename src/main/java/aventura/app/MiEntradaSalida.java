package aventura.app; /**
 * Version 1.3
 */

import java.util.Scanner;

public class MiEntradaSalida {
    private static Scanner sc = new Scanner(System.in);

    /**
     * Lee un entero mostrando el mensaje pasado como parámetro
     *
     * @param mensaje El mensaje a mostrar
     * @return el entero leído por teclado
     */
    public static int leerEntero(String mensaje) {
        System.out.print(mensaje);
        return sc.nextInt();
    }

    /**
     * Número entero
     *
     * @param mensaje      El mensaje a mostrar
     * @param seAceptaCero Si el 0 esta incluido
     * @return El entero leido por teclado
     */
    public static int leerEnteroPositivo(String mensaje, boolean seAceptaCero) {
        int a;
        do {
            a = leerEntero(mensaje);
            if (a < 0 || a == 0 && !seAceptaCero) {
                System.out.println("Numero no válido.");
            }

        } while (a < 0 || a == 0 && !seAceptaCero);
        return a;
    }

    /**
     * Leer un decimal
     *
     * @param mensaje El mensaje a introducir
     * @return Lo introducido por el usuario
     */
    public static double leerDecimal(String mensaje) {
        System.out.print(mensaje);
        return sc.nextDouble();
    }

    /**
     * Minimo Común Divisor de 2 números
     *
     * @param a primer numero
     * @param b segundo numero
     * @return el mcd
     */
    public static int mcd(int a, int b) {
        while (b != 0) {
            int temporal = b;
            b = a % b;
            a = temporal;
        }
        return a;
    }

    /**
     * Generar un número aleatorio determinado por un máximo
     *
     * @param max hasta que número se va a generar
     * @return número generado aleatoriamente
     */
    public static int generaAleatorio(int max) {
        return (int) (Math.random() * max + 1);
    }

    /**
     * Generar un número aleatorio entre un máximo y un mínimo
     *
     * @param min              Primer número del intervalo
     * @param max              Segundo número del intervalo
     * @param seAceptaElMaximo ¿El máximo entra en ese intervalo?
     * @return El número genrado aleatoriamente
     */
    public static int generaAleatorioEntre(int min, int max, boolean seAceptaElMaximo) {
        int ventana;

        if (seAceptaElMaximo) {
            ventana = max - min + 1;
        } else {
            ventana = max - min;
        }
        return (int) (Math.random() * ventana) + min;
    }

    /**
     * Leer una cadena de texto
     *
     * @param mensaje Mensaje a mostrar
     * @return String leido
     */
    public static String leerTexto(String mensaje) {
        System.out.print(mensaje);
        return sc.next();
    }

    /**
     * Leer entero en un rango
     *
     * @param mensaje mensaje a mostrar
     * @param min     valor mínimo incluido
     * @param max     valor máximo incluido
     * @return Numero leido por teclado
     */
    public static int leerEnteroRango(String mensaje, int min, int max) {
        if (min > max) {
            //Mostrar error
            System.out.println("El mínimo es mayor que el máximo");
            //TODO: Cambiar esto a ver las excepciones
            return -1;
        }
        int a;
        do {
            a = leerEntero(mensaje);
            if (a < min || a > max) {
                System.out.println("Número no válido, esta fuera del rango.");
            }
        } while (a < min || a > max);
        return a;
    }

    /**
     * Recoger el caracter 'S' o 'N'
     *
     * @param mensaje Mensaje a mostrar
     * @return El caracter obtenido por teclado
     */
    public static char leerSN(String mensaje) {
        char caracter;
        do {
            String texto = leerTexto(mensaje).toUpperCase();
            caracter = texto.charAt(0);
            if (caracter != 'S' && caracter != 'N') {
                System.out.println("Respuesta no válida");
            }
        } while (caracter != 'S' && caracter != 'N');
        return caracter;
    }

    /**
     * Imprimir una Matriz por pantalla
     *
     * @param matriz La matriz a imprimir
     */
    public static void imprimirMatriz(int[][] matriz) {
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                System.out.print("[" + matriz[i][j] + "]" + " ");
            }
            System.out.println();
        }
    }

    /**
     * Leer linea de texto
     * @param mensaje mensaje a mostar
     * @return mensaje introducido
     */
    public static String leerLinea(String mensaje) {
        System.out.print(mensaje);
        return sc.nextLine();
    }
}
