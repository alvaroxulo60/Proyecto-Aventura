package aventura.app.main;

import aventura.app.exceptions.AventuraException;
import aventura.app.exceptions.CargadorException;
import aventura.app.exceptions.CombinarException;
import aventura.app.exceptions.MigradorException;
import aventura.app.interfaces.Combinable;
import aventura.app.interfaces.Inventariable;
import aventura.app.interfaces.Leible;
import aventura.app.io.*;
import aventura.app.models.*;
import aventura.app.records.RespuestaAccion;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * Clase principal del juego "Tu Propia Aventura".
 * Esqueleto para la Misión 1 (UD1-UD3).
 * VUESTRO TRABAJO es rellenar todos los TODO
 */
public class Juego {

    private final static int NUM_HABITACIONES = 6;

    private Map<String,Habitacion> habitaciones;
    private Jugador jugador;
    private String descripcionJuego;

    public Juego() {
        jugador = new Jugador();
        preparacionJuego();
//        Migrador m = new Migrador();
//        try {
//            m.migrarContenido(this.descripcionJuego, habitaciones);
//        } catch (MigradorException e) {
//            System.out.println(e.getMessage());
//        }
    }

    public String getDescripcionJuego() {
        return descripcionJuego;
    }

    /**
     * Inicializa y prepara todas las habitaciones del juego junto con
     * sus muebles, contenedores y objetos interactivos.
     * Este metodo se ejecuta al inicio de la partida.
     */
    private void preparacionJuego() {

        CargadorAventura infoAventura;
        try {
            infoAventura = new CargadorAventura();

            AventuraConfig aventura = infoAventura.CargarMundoBase();

            this.descripcionJuego = aventura.getDescripcionDelJuego();
            this.habitaciones = aventura.getHabitaciones();
            String a;

        } catch (CargadorException e) {
            System.out.println(e.getMessage());
        }
    }

    // --- NÚCLEO: Definición de Datos (FASE 1) ---
    // Esta parte os la damos HECHA. Es el "contrato" del núcleo.

    // --- FIN DE LA DEFINICIÓN DE DATOS ---

    public void mostrarInfo() {
        mirar();
    }

    public void mirar() {
        if (getHabitacionActual().contarObjetosHabitacion() == 0){
            System.out.println("En esta habitación no hay nada... ");
            return;
        }
        System.out.println(getHabitacionActual().getDESCRIPCION());
        System.out.println("En esta Habitación encuentras los siguientes objetos");
        for (Objeto o : getHabitacionActual().getObjetos()) {
            System.out.println(o.getNombre());
        }
    }

    /**
     * Examinar algún objeto tanto de la habitacion como de tu inventario
     */
    public void examinar() {
        if (getHabitacionActual().contarObjetosHabitacion() == 0 && jugador.contarObjetosInventario() == 0){
            System.out.println("No hay nada que examinar... ");
            return;
        }
        mostrarObjetos();
        String objeto = MiEntradaSalida.leerLinea("Introduce el nombre del objeto que quieras examinar:  \n");
        Objeto aux = buscar(objeto);
        if (aux == null) {
            System.out.println("Ese objeto no se encuentra en el inventario o habitación");
        } else {
            System.out.println("Descripción:");
            System.out.println(aux.getDescripcion());
            if (aux instanceof Leible l) {
                System.out.println("Contenido: \n");
                System.out.println(l.leer());
            }
        }
    }

    /**
     * Buscar los objetos en la habitación o en el inventario llamando a sus respectivos metodos
     *
     * @param o Nombre del objeto a buscar
     * @return devuelve el objeto si lo ha encontrado o en su contrario nulo
     */
    public Objeto buscar(String o) {
        //Buscamos primero si está el objeto en la habitación
        Objeto aux = getHabitacionActual().buscarObjetoHabitacion(o);
        if (aux != null) {
            return aux;
        }
        //Si no lo está buscamos en el inventario
        return jugador.buscarObjetoInventario(o);
        //cambio
    }

    /**
     * Coger la habitación actual del jugador
     *
     * @return la habitación actual
     */
    public Habitacion getHabitacionActual() {
        return habitaciones.get(jugador.getPosicionJugador());
    }

    /**
     * Mostrar los objetos tanto en tu inventario como en la habitación
     */
    public void mostrarObjetos() {
        if (getHabitacionActual().contarObjetosHabitacion() != 0) {
            System.out.println("Objetos en la habitación: ");
            System.out.println(getHabitacionActual().mostrarObjetosHabitacion());
        }
        if (jugador.contarObjetosInventario() != 0) {
            System.out.println("Objetos en tu inventario: ");
            System.out.println(jugador.mostrarObjetosInventario());
        }
    }

    public void iniciarJuego() {
        boolean jugando = true;

        System.out.println("¡Bienvenido a 'TU PROPIA AVENTURA'!");
        System.out.println("------------------------------------------");

        //Muestra la descripción general del juego
        System.out.println(getDescripcionJuego());
        mirar();


        //Iniciar el bucle principal del juego (game loop)
        while (jugando) {
            //Leer el comando del usuario por teclado
            System.out.print("\n> ");
            String comando = MiEntradaSalida.leerLinea("¿Qué quieres hacer a continuación? (Escribe 'ayuda' para ver los comandos posibles): \n");

            /*
            Crear un 'switch' o una estructura 'if-else if'
             para procesar el 'comando' del usuario.
             Debe gestionar como mínimo: "ayuda", "mirar", "inventario",
             "ir derecha", "ir izquierda", "coger [objeto]" y "salir".
             */

            switch (comando.toLowerCase().trim()) {
                case "ir":
                    try {
                        ir();
                    } catch (AventuraException e) {
                        System.out.println(e.getMessage());
                    }
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
                case "abrir":
                    abrirContenedor();
                    break;
                case "combinar":
                    combinar();
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
    private void verInventario() {
        String inventario = jugador.mostrarObjetosInventario();
        if (inventario.isBlank()) {
            System.err.println("No tienes nada en el inventario");
        } else {
            System.out.println(inventario);
        }

    }

    /**
     * Metodo para recoger objetos de la habitación en la que estas.
     */
    private void cogerObjeto() {
        if (getHabitacionActual().contarObjetosInventariablesHabitacion() > 0) {
            mostrarObjetosInventariables();
            String objeto = MiEntradaSalida.leerLinea("¿Que objeto quieres guardar? ");
            guardarEnInventario(objeto);
        } else System.err.println("No queda ningún objeto en la sala de importancia.\n");
    }

    /**
     * Metodo para mostrar los objetos inventariables
     */
    public void mostrarObjetosInventariables() {
        System.out.println(getHabitacionActual().mostrarObjetosInventariables());
    }

    /**
     * Busca el objeto y llama al metodo del jugador para guardarlo en su inventario
     *
     * @param o Un String con el nombre del objeto a buscar
     */
    private void guardarEnInventario(String o) {
        Objeto aux = getHabitacionActual().buscarObjetoHabitacion(o);
        if (aux != null) {
            if (aux instanceof Inventariable inv) {
                if (jugador.guardarInventario((Objeto) inv)) {
                    getHabitacionActual().quitarObjetoHabitacion((Objeto) inv);
                    System.out.println("¡Objeto guardado con éxito!");
                } else {
                    System.err.println("No ha sido posible guardar el objeto...El inventario esta lleno");
                }
            } else {
                System.err.println("No ha sido posible guardar el objeto...");
            }
        } else {
            System.err.println("No se ha encontrado el objeto");
        }
    }


    /**
     * Mostrar los posibles comandos mientras el juego está en uso
     */
    private void ayuda() {
        System.out.print("====================AYUDA====================\n ");
        System.out.print(">ir \n ");
        System.out.print(">examinar \n ");
        System.out.print(">coger objeto \n ");
        System.out.print(">inventario \n ");
        System.out.print(">abrir \n ");
        System.out.print(">combinar \n ");
        System.out.print(">salir \n ");
        System.out.print("=============================================\n");
    }

    /**
     * Metodo para abrir los contenedores
     */
    public void abrirContenedor() {
        if (getHabitacionActual().contarContenedoresHabitacion() > 0) {

            mostrarContenedores(); // Mostramos los contenedores disponibles en la habitación

            String contenedor = MiEntradaSalida.leerLinea("¿Que contenedor quieres abrir? \n");
            Objeto aux = getHabitacionActual().buscarObjetoHabitacion(contenedor);
            if (aux instanceof Contenedor c) {
                // Si el objeto es una instancia de contenedor busca en el inventario del jugador la llave con el mismo código
                Llave l = jugador.buscarLlaveInventario(c.getCODIGO_SECRETO());

                RespuestaAccion respuesta = c.abrir(l); // Guardamos el resultado del RespuestaAccion

                if (respuesta.esExito()) {
                    // Si tiene éxito imprime el mensaje y consume la llave usada
                    System.out.println(respuesta.mensaje());
                    jugador.consumirObjetosInventario(l);

                    // Comprueba si hay un objeto dentro del cofre
                    if (c.getElemento() != null) {
                        System.out.println("Dentro encuentras: " + c.getElemento().getNombre());

                        // intenta guardar el elemento y si no es capaz lo deja en la habitación
                        if (jugador.guardarInventario(c.getElemento())) {
                            c.eliminarObjeto();
                            System.out.println("El objeto se ha guardado en el inventario");
                        } else {
                            System.err.println("No se ha podido guardar en el inventario");
                            getHabitacionActual().añadirObjetosHabitacion(c.getElemento());
                            c.eliminarObjeto();
                        }
                    } else
                        System.out.println("No encuentras nada");
                } else {
                    System.out.println(respuesta.mensaje());
                }
            } else {
                System.err.println("No se ha encontrado el contenedor");
            }
        } else
            System.err.println("No hay contenedores en la habitación.");
    }

    /**
     * Mostrar los contenedores de la habitación
     */
    public void mostrarContenedores() {
        System.out.println(getHabitacionActual().mostrarContenedores());
    }

    /**
     * Metodo para combinar objetos, primero pide los dos objetos a combinar y luego intenta combinarlos
     */
    public void combinar() {
        mostrarObjetos();

        //Pedimos los dos objetos para combinarlos
        String objeto1 = MiEntradaSalida.leerLinea("¿Qué objeto quieres combinar?\n");
        Objeto aux1 = buscar(objeto1);
        String objeto2 = MiEntradaSalida.leerLinea("¿Con qué objeto lo quieres combinar?\n");
        Objeto aux2 = buscar(objeto2);
        if (aux1 != null && aux2 != null) {
            if (!aux1.equals(aux2)) {

                //Comprobamos si el primer objeto es combinable
                if (aux1 instanceof Combinable c1) {
                    //Si podemos combinar los objetos sin ningún problema borramos los otros dos del inventario
                    //o habitación y guardamos el resultante en el inventario
                    try {
                        Objeto resultante = c1.combinar(aux2);
                        borrarObjetos(aux1);
                        borrarObjetos(aux2);
                        jugador.guardarInventario(resultante);
                        System.out.println("Los objetos se han combinado y guardado en el inventario");
                    } catch (CombinarException e) {
                        System.out.println(e.getMessage());
                    }
                } else {
                    System.err.println("No se puede combinar el objeto.");
                }
            } else {
                System.err.println("No se puede combinar el objeto consigo mismo.");
            }
        } else {
            System.err.println("Uno de los dos objetos no se ha encontrado.");
        }
    }

    /**
     * Metodo para borrar objetos del inventario o la habitación
     *
     * @param aux objeto a borrar
     */
    public void borrarObjetos(Objeto aux) {
        jugador.consumirObjetosInventario(aux);
        getHabitacionActual().quitarObjetoHabitacion(aux);
    }

    public void ir()throws AventuraException{
        System.out.println("Salidas disponibles: ");
        Habitacion actual = getHabitacionActual();
        System.out.println(actual.getMapa().keySet());
        String direccion = MiEntradaSalida.leerLinea("¿Dónde quieres ir? \n").toLowerCase();
        if (!actual.getMapa().containsKey(direccion)){
            throw new AventuraException("La dirección no es válida. \n");
        }
        jugador.setPosicionJugador(actual.getMapa().get(direccion));
        System.out.println("Cruzas la " + direccion);
        mirar();
    }

    public static void main(String[] args) {

        Juego j = new Juego();
        j.preparacionJuego();
        j.iniciarJuego();
        System.out.println("¡Gracias por jugar!");

    }
}