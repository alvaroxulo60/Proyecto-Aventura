package aventura.app.main;

import aventura.app.io.*;
import aventura.app.models.Habitacion;
import aventura.app.models.Jugador;
import aventura.app.models.Objeto;

/*
 * Clase principal del juego "Tu Propia Aventura".
 * Esqueleto para la Misión 1 (UD1-UD3).
 * VUESTRO TRABAJO es rellenar todos los TODO
 */
public class Juego {

    private final static int NUM_HABITACIONES = 6;

    private Habitacion[] habitaciones;
    Jugador jugador;

    public Juego() {
        habitaciones = new Habitacion[NUM_HABITACIONES];
        jugador = new Jugador();
        preparacionJuego();

    }

    private void preparacionJuego(){


    }

    // --- NÚCLEO: Definición de Datos (FASE 1) ---
    // Esta parte os la damos HECHA. Es el "contrato" del núcleo.

    //Creo un String para darle una introducción al usuario de la historia del juego

    private static String descripcionJuego = "Empiezas en una aldea tranquila, Kael el Comerciante, el cual conoces muy bien, muy amablemente te ofrece sin coste una poción misteriosa, según él esa poción te volverá el mejor alumno de la academia de la luz y la sombra, te llevas la poción con gusto y mientras das un paseo te la tomas para volverte el mejor de todos.\n" +
            "A los segundos después de tomarla te empiezas a sentir mareado y se te nubla la vista hasta que finalmente te desplomas en el suelo.\n" +
            "Te despiertas en un lugar familiar, no sabes como llegaste a ahí, ni cuánto tiempo llevas ahí.\n";


    // --- FIN DE LA DEFINICIÓN DE DATOS ---

    /**
     * Métod0 para ir a la habitación de la derecha
     */
    private void irDerecha() {
        if (jugador.getPosicionJugador() + 1 != habitaciones.length) {
            jugador.setPosicionJugador(jugador.getPosicionJugador() + 1);
            System.out.println("Te has movido a la derecha...\n");
            mostrarInfo();
        } else
            System.out.println("No es posible ir a la derecha");
    }

    /**
     * Métod0 para ir a la habitación de la izquierda
     */
    private void irIzquierda() {
        if (jugador.getPosicionJugador() - 1 > 0) {
            jugador.setPosicionJugador(jugador.getPosicionJugador() + 1);
            System.out.println("Te has movido a la izquierda...\n");
            mostrarInfo();
        } else
            System.out.println("No es posible ir a la izquierda");
    }

    public void mostrarInfo(){
        mirar();
    }

    public void mirar(){
        System.out.println(getHabitacionActual().getDESCRIPCION());
        MiEntradaSalida.mostrarOpcionesSinNulos("En la habitación encuentras los siguientes objetos: ", getHabitacionActual().getObjetos());
    }

    /**
     * Examinar algún objeto tanto de la habitacion como de tu inventario
     */
    public void examinar(){
        mostrarObjetos();
        String objeto = MiEntradaSalida.leerLinea("Introduce el nombre del objeto que quieras examinar:  \n");
        Objeto aux = buscar(objeto);
        if (aux == null){
            System.out.println("Ese objeto no se encuentra en el inventario o habitación");
        }
        else
            System.out.println(aux.getDescripcion());
    }

    /**
     * Buscar los objetos en la habitación o en el inventario llamando a sus respectivos metodos
     * @param o Nombre del objeto a buscar
     * @return devuelve el objeto si lo ha encontrado o en su contrario nulo
     */
    public Objeto buscar(String o){
        //Buscamos primero si está el objeto en la habitación
        Objeto aux = getHabitacionActual().buscarObjetoHabitacion(o);
        if (aux != null){
            return aux;
        }
        //Si no lo está buscamos en el inventario
        return jugador.buscarObjetoInventario(o);
        //cambio
    }

    /**
     * Coger la habitación actual del jugador
     * @return la habitación actual
     */
    public Habitacion getHabitacionActual(){
        return habitaciones[jugador.getPosicionJugador()];
    }

    /**
     * Mostrar los objetos tanto en tu inventario como en la habitación
     */
    public void mostrarObjetos(){
        int contador = 0;
        System.out.println("Objetos en la habitacion: ");
        for (int i = 0; i <getHabitacionActual().getObjetos().length ; i++) {
            if (getHabitacionActual().getObjetos()[i]!=null){
                System.out.println(contador++ +". "+ getHabitacionActual().getObjetos()[i].getNombre());
            }
        }
        contador = 0;
        System.out.println("Objetos en tu inventario: ");
        for (int i = 0; i <jugador.getInventario().length ; i++) {
            if (jugador.getInventario()[i]!= null){
                System.out.println(contador++ +". "+ jugador.getInventario()[i].getNombre());
            }
        }
    }

    public void iniciarJuego(){
        boolean jugando = true;

        System.out.println("¡Bienvenido a 'TU PROPIA AVENTURA'!");
        System.out.println("------------------------------------------");

        //Muestra la descripción general del juego
        System.out.println(descripcionJuego);



        //Iniciar el bucle principal del juego (game loop)
        while (jugando) {
            //Leer el comando del usuario por teclado
            System.out.print("\n> ");
            String comando = MiEntradaSalida.leerLinea("¿Qué quieres hacer a continuación? (Escribe 'ayuda' para ver los comandos posibles): ");

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
                case "examinar":
                    examinar();
                    break;
                case "salir":
                    jugando = false;
                    break;
                default:
                    ayuda();
                    break;
            }

        }
    }



    /*
    (Opcional - Buenas Prácticas)
    Si el 'switch' se vuelve muy grande, podéis crear métodos privados
    para organizar el código, por ejemplo:
    private static void procesarComandoCoger(String comando) { ... }
    private static void mostrarInfoHabitacion() { ... }
    */


    /**
     * Ver tus objetos numerados en el inventario.
     */
    private  void verInventario() {
        String inventario = jugador.verInventario();
        if(inventario.isBlank()){
            System.out.println("No tienes nada en el inventario");
        }else {
            System.out.println(inventario);
        }

    }

    /**
     * Metodo para recoger objetos de la habitación en la que estas.
     */
    private  void cogerObjeto() {
        if (contarObjetosHabitacion() > 0) {
            MiEntradaSalida.mostrarOpcionesSinNulos("Objetos en la sala: ", getHabitacionActual().getObjetos());
            String objeto = MiEntradaSalida.leerLinea("¿Que objeto quieres guardar?");
            guardarEnInventario(objeto);
        } else System.out.println("No queda ningún objeto en la sala de importancia.\n");
    }

    /**
     * Busca el objeto y llama al metodo del jugador para guardarlo en su inventario
     * @param o Un String con el nombre del objeto a buscar
     */
    private  void guardarEnInventario(String o) {
        Objeto aux = getHabitacionActual().buscarObjetoHabitacion(o);
        if (aux != null){
            if (jugador.guardarInventario(aux)){
                getHabitacionActual().quitarObjetoHabitacion(aux);
                System.out.println("¡Objeto guardado con éxito! \n");
            }else {
                System.out.println("No ha sido posible guardar el objeto...El inventario esta lleno");
            }
        }
        else{
            System.out.println("No se ha encontrado el objeto");
        }
    }


    /**
     * Mostrar los posibles comandos mientras el juego está en uso
     */
    private void ayuda() {
        System.out.print("====================AYUDA====================\n ");
        System.out.print(">ir derecha \n ");
        System.out.print(">ir izquierda \n ");
        System.out.print(">examinar \n ");
        System.out.print(">coger objeto \n ");
        System.out.print(">inventario \n ");
        System.out.print(">salir \n ");
        System.out.print("=============================================\n");
    }

    /**
     * Contar objetos de la habitacion
     * @return objetos en la habitación
     */
    private int contarObjetosHabitacion() {
        int contador = 0;
        for (int i = 0; i < getHabitacionActual().getObjetos().length; i++) {
            if (getHabitacionActual().getObjetos()[i] != null) {
                contador++;
            }
        }
        return contador;
    }

    public static void main(String[] args) {

        Juego j = new Juego();
        j.preparacionJuego();
        j.iniciarJuego();
        System.out.println("¡Gracias por jugar!");

    }
}