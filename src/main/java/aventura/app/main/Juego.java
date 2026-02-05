package aventura.app.main;

import aventura.app.exceptions.CombinarException;
import aventura.app.interfaces.Combinable;
import aventura.app.interfaces.Inventariable;
import aventura.app.interfaces.Leible;
import aventura.app.io.*;
import aventura.app.models.*;
import aventura.app.records.RespuestaAccion;

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
        jugador = new Jugador();
        preparacionJuego();

    }

    private void preparacionJuego(){
        //* primera habitación
        Habitacion tuHabitacion = new Habitacion("Miras alrededor y te das cuenta de que es tu habitación pero a la vez que vas mirando más a detalle te das cuenta que hay cosas que no deberían estar ahí como un libro, algo desgastado en la estantería, tiene cerradura muy extraña, como  si fuera para introducir una llave… ¿Con forma de zorro? no recuerdas haber comprado algo así antes. Hay una puerta a la derecha.");
        Mueble m1 = new Mueble("Cama","Es tú cama, no parece que tenga algo importante", true);
        Mueble m2 = new Mueble("Escritorio","Aquí es donde sueles leer y estudiar", true);
        Mueble m3 = new Mueble("Estantería", "Una estantería llena de libros, pero te llama la atención uno en concreto", true);
        Contenedor c1 = new Contenedor("Mesa de noche", "Es tu mesita de noche", true, null, null,false);
        LibroHechizos libroHechizos = new LibroHechizos("Libro de hechizos", "Parece importante, pero esta desgastado... Además parece que para abrirlo se necesita una llave", true);
        tuHabitacion.añadirObjetosHabitacion(libroHechizos);
        tuHabitacion.añadirObjetosHabitacion(m1);
        tuHabitacion.añadirObjetosHabitacion(m2);
        tuHabitacion.añadirObjetosHabitacion(m3);
        tuHabitacion.añadirObjetosHabitacion(c1);

        //*segunda habitación
        Habitacion aula1ºB = new Habitacion("También te resulta familiar, es el aula donde los alumnos de mayor grado dan sus clases de hechizos, pero lo extraño es que el aula esta del revés. En la mesa del profesor hay un cajón. En esta sala hay una puerta a la derecha y otra a la izquierda.\n");
        Mueble m4 = new Mueble("Mesa de estudiantes", "Son las mesas que usan los alumnos de mayor grado", true);
        Mueble m5 = new Mueble("Mesa del profesor", "Es la mesa del profesor, ves que el cajón esta abierto", true);
        NotaRota notaRota1 = new NotaRota("Nota rota 1", "Es una nota a la que le falta una mitad...", true);
        Contenedor c2 = new Contenedor("Cajón del profesor", "Es el cajón donde los maestros suelen guardar el borrador, papeles, etc...", true,null, notaRota1,false);
        aula1ºB.añadirObjetosHabitacion(m4);
        aula1ºB.añadirObjetosHabitacion(m5);
        aula1ºB.añadirObjetosHabitacion(c2);

        //*tercera habitación
        Habitacion centroMedico = new Habitacion("Al cruzar la puerta apareces en una zona médica, entonces caes en que estás en el centró médico de tu aldea. Alrededor tuya ves muchos muebles con muchos frascos y varios muebles cerrados, en particular te fijas en un cajón de una mesa que tiene una cerradura con forma de estrella. No hay más puertas, solo por la que viniste.");
        Mueble m6 = new Mueble("Muebles", "Ves varios muebles por toda la zona, todos están en mal estado y no parecen tener nada", true);
        Mueble m7 = new Mueble("Camilla", "Es una camilla donde parecen que tuvieron que llevar alguine muy herido...", true);
        Nota nota = new Nota("Nota", "Es una nota doblada", true,"el cielo nocturno lleno de estrellas está, pero una sola desbloqueara la magia” busca en la habitación…" );
        LlaveEspecial llaveKitsune = new LlaveEspecial("Llave Kitsune", "Es una llave que tiene forma de zorro de 9 colas", true);
        Contenedor c3 = new Contenedor("Cajón", "Es una cajón que tiene una cerradura con forma de estrella", true, "Llave Estrella", llaveKitsune, false);
        centroMedico.añadirObjetosHabitacion(nota);
        centroMedico.añadirObjetosHabitacion(m6);
        centroMedico.añadirObjetosHabitacion(m7);
        centroMedico.añadirObjetosHabitacion(c3);

        //*cuarta habitación
        Habitacion sotano = new Habitacion("Llegas a un sótano donde apenas hay luz, no te suena de nada este sitio. Al mirar alrededor no ves nada de importancia pero a tu derecha ves unas escaleras que supones que es la salida de esta sala. Hay una puerta a la izquierda y las escaleras a la derecha.\n");
        Mueble m8 = new Mueble("Caldera", "Es la caldera que permite que salga agua caliente en casa, etc...",true);
        Mueble m9 = new Mueble("Armario","Es un armario de madera antiguo que esta vacío", true);
        Llave llaveEstrella = new Llave("Llave Estrella", "Es una llave que tiene forma de estrella", true, "Llave Estrella");
        Contenedor c4 = new Contenedor("Caja de Herramientas", "Es una caja de herramientas que contiene algo en su interior", true, null,llaveEstrella,false);
        sotano.añadirObjetosHabitacion(m8);
        sotano.añadirObjetosHabitacion(m9);
        sotano.añadirObjetosHabitacion(c4);

        //*quinta habitación
        Habitacion biblioteca = new Habitacion("Al subir las escaleras ahora apareces en la biblioteca de la aldea, todas las escaleras están vacías, pero investigando en la biblioteca te fijas que hay una escalera que te podría servir.");
        Mueble m10 = new Mueble("Estantería", "Estanterías llenas y llenas de libros de brujería", true);
        Mueble m11 = new Mueble("Carrito de libros", "Un carrito con libros devueltos esperando a ser puestos en las estanterías", true);
        Mueble m12 = new Mueble("Mesas y sillas", "Varias mesas y sillas para que puedas sentarte a leer tranquilo", true);
        Mueble m13 = new Mueble("Mesa de la recepción", "Es la mesa donde vas a que te den el sello y la fecha de devolución de libros", true);
        Contenedor c5 = new Contenedor("Cajón", "El cajón de la mesa de la recepción que parece estar abierto", true, null, null, false);
        biblioteca.añadirObjetosHabitacion(m10);
        biblioteca.añadirObjetosHabitacion(m11);
        biblioteca.añadirObjetosHabitacion(m12);
        biblioteca.añadirObjetosHabitacion(m13);
        biblioteca.añadirObjetosHabitacion(c5);

        //*sexta y última habitación
        Habitacion mercado = new Habitacion("Al cruzar la puerta te das cuenta de un detalle importante, todo este tiempo lo que has ido viendo han sido ilusiones creadas por un hechizo, pero quién podría hacerte esto a tí… Bueno, al mirar alrededor ves que estás en una calle y es la del mercado, al final de calle ves una gran puerta que parece que necesita un conjuro para abrirse.");
        Mueble m14 = new Mueble("Puesto", "Varios puestos del mercado que venden frutas, verduras, escobas voladoras, etc...", true);
        Mueble m15 = new Mueble("Mesa", "Una mesa que contiene una nota", true);
        NotaRota notaRota2 = new NotaRota("Nota rota 2", "Es una nota a la que le falta una mitad...",true);
        mercado.añadirObjetosHabitacion(notaRota2);
        mercado.añadirObjetosHabitacion(m14);
        mercado.añadirObjetosHabitacion(m15);
        //Añadimos las habitaciones al array
        habitaciones = new Habitacion[]{tuHabitacion, aula1ºB, centroMedico, sotano, biblioteca, mercado};
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
        if (jugador.getPosicionJugador() - 1 >= 0) {
            jugador.setPosicionJugador(jugador.getPosicionJugador() - 1);
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
        else {
            System.out.println("Descripción:");
            System.out.println(aux.getDescripcion());
            if (aux instanceof Leible l) {
                System.out.println("Contenido: \n");
                l.leer();
            }
        }
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
        int contador = 1;
        if (getHabitacionActual().contarObjetosHabitacion()!= 0) {
            System.out.println("Objetos en la habitación: ");
            for (int i = 0; i < getHabitacionActual().getObjetos().length; i++) {
                if (getHabitacionActual().contarObjetosHabitacion() == contador - 1) {
                    break;
                }
                if (getHabitacionActual().getObjetos()[i] != null) {
                    System.out.println(contador++ + ". " + getHabitacionActual().getObjetos()[i].getNombre());
                }
            }
        }
        if (jugador.contarObjetosInventario() != 0){
        contador = 1;
        System.out.println("Objetos en tu inventario: ");
        for (int i = 0; i <jugador.getInventario().length ; i++) {
            if (jugador.contarObjetosInventario()==contador-1){
                break;
            }
            if (jugador.getInventario()[i]!= null){
                System.out.println(contador++ +". "+ jugador.getInventario()[i].getNombre());
            }
        }
        }
    }

    public void iniciarJuego(){
        boolean jugando = true;

        System.out.println("¡Bienvenido a 'TU PROPIA AVENTURA'!");
        System.out.println("------------------------------------------");

        //Muestra la descripción general del juego
        System.out.println(descripcionJuego);
        mirar();


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

            switch (comando.toLowerCase().trim()) {
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
    private  void verInventario() {
        String inventario = jugador.verInventario();
        if(inventario.isBlank()){
            System.err.println("No tienes nada en el inventario");
        }else {
            System.out.println(inventario);
        }

    }

    /**
     * Metodo para recoger objetos de la habitación en la que estas.
     */
    private  void cogerObjeto() {
        if (getHabitacionActual().contarObjetosInventariablesHabitacion() > 0) {
            mostrarObjetosInventariables();
            String objeto = MiEntradaSalida.leerLinea("¿Que objeto quieres guardar? ");
            guardarEnInventario(objeto);
        } else System.err.println("No queda ningún objeto en la sala de importancia.\n");
    }

    /**
     *Metodo para mostrar los objetos inventariables
     */
    public void mostrarObjetosInventariables(){
        int contador = 1;
        System.out.println("Objetos en la habitación: ");
        for (int i = 0; i <getHabitacionActual().getObjetos().length ; i++) {
            if (getHabitacionActual().contarObjetosHabitacion()==contador-1){
                break;
            }
            if (getHabitacionActual().getObjetos()[i]!=null && getHabitacionActual().getObjetos()[i] instanceof Inventariable){
                System.out.println(contador++ +". "+ getHabitacionActual().getObjetos()[i].getNombre());
            }
        }
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
                System.out.println("¡Objeto guardado con éxito!");
            }else {
                System.err.println("No ha sido posible guardar el objeto...El inventario esta lleno");
            }
        }
        else{
            System.err.println("No se ha encontrado el objeto");
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
        System.out.print(">abrir \n ");
        System.out.print(">combinar \n ");
        System.out.print(">salir \n ");
        System.out.print("=============================================\n");
    }

    /**
     * Metodo para abrir los contenedores
     */
    public void abrirContenedor(){
        if (getHabitacionActual().contarContenedoresHabitacion() > 0) {
            mostrarContenedores();
            String contenedor = MiEntradaSalida.leerLinea("¿Que contenedor quieres abrir? \n");
            Objeto aux = getHabitacionActual().buscarObjetoHabitacion(contenedor);
            if (aux instanceof Contenedor c) {
                Llave l = jugador.buscarLlaveInventario(c.getCODIGO_SECRETO());
                RespuestaAccion respuesta = c.abrir(l);
                if (respuesta.esExito()) {
                    System.out.println(respuesta.mensaje());
                    jugador.consumirObjetosInventario(l);
                    if (c.getElemento() != null) {
                        System.out.println("Dentro encuentras: " + c.getElemento().getNombre());
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
        }else
            System.err.println("No hay contenedores en la habitación.");
    }

    /**
     * Mostrar los contenedores de la habitación
     */
    public void mostrarContenedores(){
        int contador = 1;
        System.out.println("Objetos en la habitación: ");
        for (int i = 0; i <getHabitacionActual().getObjetos().length ; i++) {
            if (getHabitacionActual().contarObjetosHabitacion()==contador-1){
                break;
            }
            if (getHabitacionActual().getObjetos()[i]!=null && getHabitacionActual().getObjetos()[i] instanceof Contenedor){
                System.out.println(contador++ +". "+ getHabitacionActual().getObjetos()[i].getNombre());
            }
        }
    }

    /**
     * Metodo para combinar objetos, primero pide los dos objetos a combinar y luego intenta combinarlos
     */
    public void combinar(){
        mostrarObjetos();
        String objeto1 = MiEntradaSalida.leerLinea("¿Qué objeto quieres combinar?\n");
        Objeto aux1 = buscar(objeto1);
        String objeto2 = MiEntradaSalida.leerLinea("¿Con qué objeto lo quieres combinar?\n");
        Objeto aux2 = buscar(objeto2);
        if (aux1!=null && aux2!=null){
            if(!aux1.equals(aux2)){
                if(aux1 instanceof Combinable c1){
                    try {
                        Objeto resultante = c1.combinar(aux2);
                        borrarObjetos(aux1);
                        borrarObjetos(aux2);
                        jugador.guardarInventario(resultante);
                        System.out.println("Los objetos se han combinado y guardado en el inventario");
                    }catch (CombinarException e){
                        System.out.println(e.getMessage());
                    }
                }else  {
                    System.err.println("No se puede combinar el objeto.");
                }
            }else {
                System.err.println("No se puede combinar el objeto consigo mismo.");
            }
        }else  {
            System.err.println("Uno de los dos objetos no se ha encontrado.");
        }
    }

    /**
     * Metodo para borrar objetos del inventario o la habitación
     * @param aux objeto a borrar
     */
    public void borrarObjetos(Objeto aux){
        jugador.consumirObjetosInventario(aux);
        getHabitacionActual().quitarObjetoHabitacion(aux);
    }

    public static void main(String[] args) {

        Juego j = new Juego();
        j.preparacionJuego();
        j.iniciarJuego();
        System.out.println("¡Gracias por jugar!");

    }
}