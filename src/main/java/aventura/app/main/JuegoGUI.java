package aventura.app.main;

import aventura.app.interfaces.Inventariable;
import aventura.app.models.Contenedor;
import aventura.app.models.Jugador;
import aventura.app.models.Objeto;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class JuegoGUI extends JFrame {
    private JTextPane consola;
    private JTextField inputTexto;
    private JPanel panelBotones; // Panel que cambiaremos dinámicamente
    private PipedOutputStream outPipe;
    private Juego juegoMotor;

    // Estado para saber qué botones mostrar
    private boolean partidaIniciada = false;

    public JuegoGUI() {
        juegoMotor = new Juego();

        setTitle("Maestría Arcana - Tu Propia Aventura");
        setSize(950, 750);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);

        // --- 1. ÁREA DE PANTALLA ---
        consola = new JTextPane();
        consola.setEditable(false);
        consola.setBackground(new Color(20, 24, 32));
        consola.setMargin(new Insets(20, 25, 20, 25));

        JScrollPane scrollPane = new JScrollPane(consola);
        scrollPane.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(100, 100, 120)));
        add(scrollPane, BorderLayout.CENTER);

        // --- 2. PANEL INFERIOR ---
        JPanel panelInferior = new JPanel(new BorderLayout());
        panelInferior.setBackground(new Color(40, 44, 52));

        // Entrada manual
        JPanel panelManual = new JPanel(new BorderLayout(10, 0));
        panelManual.setOpaque(false);
        panelManual.setBorder(new EmptyBorder(10, 15, 5, 15));

        inputTexto = new JTextField();
        inputTexto.setFont(new Font("SansSerif", Font.PLAIN, 15));

        JButton btnEnviar = new JButton("Enviar");
        btnEnviar.addActionListener(e -> enviarManual());

        panelManual.add(new JLabel("⌨") {{ setForeground(Color.WHITE); }}, BorderLayout.WEST);
        panelManual.add(inputTexto, BorderLayout.CENTER);
        panelManual.add(btnEnviar, BorderLayout.EAST);
        panelInferior.add(panelManual, BorderLayout.NORTH);

        // Panel de Botones Dinámico
        panelBotones = new JPanel();
        panelBotones.setOpaque(false);
        panelBotones.setBorder(new EmptyBorder(10, 15, 15, 15));
        panelInferior.add(panelBotones, BorderLayout.CENTER);

        add(panelInferior, BorderLayout.SOUTH);

        // Inicializar la botonera en estado "Menú"
        actualizarInterfaz();

        inputTexto.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) enviarManual();
            }
        });

        redirigirStreams();
    }

    /**
     * Limpia y reconstruye la botonera según el estado del juego.
     */
    private void actualizarInterfaz() {
        panelBotones.removeAll(); // Borra todos los botones actuales

        if (!partidaIniciada) {
            // --- BOTONES MODO MENÚ ---
            panelBotones.setLayout(new GridLayout(1, 4, 10, 10)); // Una sola fila para el menú

            panelBotones.add(crearBotonMenu("Nueva Partida", "nueva partida"));
            panelBotones.add(crearBotonMenu("Cargar Partida", "cargar partida"));
            panelBotones.add(crearBotonSimple("Borrar Partida", "borrar partida"));
            panelBotones.add(crearBotonSalir());
        } else {
            // --- BOTONES MODO JUEGO ---
            panelBotones.setLayout(new GridLayout(3, 3, 8, 8)); // Rejilla clásica de juego

            panelBotones.add(crearBotonDinamico("Ir hacia...", "ir", this::obtenerSalidas, "¿A dónde vas?"));
            panelBotones.add(crearBotonDinamico("Examinar", "examinar", this::obtenerTodosLosObjetos, "¿Qué examinas?"));
            panelBotones.add(crearBotonDinamico("Coger Objeto", "coger objeto", this::obtenerObjetosInventariables, "¿Qué recoges?"));

            panelBotones.add(crearBotonSimple("Inventario", "inventario"));
            panelBotones.add(crearBotonDinamico("Abrir", "abrir", this::obtenerContenedores, "¿Qué abres?"));
            panelBotones.add(crearBotonCombinar());

            panelBotones.add(crearBotonSimple("Guardar", "guardar partida"));
            panelBotones.add(crearBotonSimple("Ayuda", "ayuda"));
            panelBotones.add(crearBotonSalir());
        }

        panelBotones.revalidate(); // Refresca el diseño
        panelBotones.repaint();    // Vuelve a pintar
    }

    // --- GENERADORES DE BOTONES ---

    private JButton crearBotonMenu(String texto, String comando) {
        JButton btn = new JButton(texto);
        darEstiloBoton(btn);
        btn.addActionListener(e -> {
            partidaIniciada = true; // Al dar a Nueva o Cargar, cambiamos el estado
            enviarComando(comando);
            actualizarInterfaz();   // Cambiamos los botones
        });
        return btn;
    }

    private JButton crearBotonSimple(String texto, String comando) {
        JButton btn = new JButton(texto);
        darEstiloBoton(btn);
        btn.addActionListener(e -> {
            if (comando.equals("guardar partida")) {
                String nombre = JOptionPane.showInputDialog(this, "Nombre del archivo:");
                if (nombre != null) { enviarComando(comando); enviarComando(nombre); }
            } else {
                enviarComando(comando);
            }
        });
        return btn;
    }

    private JButton crearBotonDinamico(String texto, String comandoBase, ProveedorOpciones proveedor, String pregunta) {
        JButton btn = new JButton(texto);
        darEstiloBoton(btn);
        btn.addActionListener(e -> {
            enviarComando(comandoBase);
            Timer timer = new Timer(150, evt -> {
                String respuesta = mostrarDialogoBotones(texto, pregunta, proveedor.obtener());
                enviarComando(respuesta != null ? respuesta : " ");
            });
            timer.setRepeats(false);
            timer.start();
        });
        return btn;
    }

    private JButton crearBotonCombinar() {
        JButton btn = new JButton("Combinar");
        darEstiloBoton(btn);
        btn.addActionListener(e -> {
            enviarComando("combinar");
            Timer timer1 = new Timer(150, evt1 -> {
                List<String> items = obtenerTodosLosObjetos();
                String obj1 = mostrarDialogoBotones("Paso 1", "Objeto base:", items);
                if (obj1 != null) {
                    enviarComando(obj1);
                    Timer timer2 = new Timer(150, evt2 -> {
                        String obj2 = mostrarDialogoBotones("Paso 2", "Objeto con el que combinar:", items);
                        enviarComando(obj2 != null ? obj2 : " ");
                    });
                    timer2.setRepeats(false); timer2.start();
                } else { enviarComando(" "); enviarComando(" "); }
            });
            timer1.setRepeats(false); timer1.start();
        });
        return btn;
    }

    private JButton crearBotonSalir() {
        JButton btn = new JButton("Salir");
        darEstiloBoton(btn);
        btn.setBackground(new Color(120, 40, 40));
        btn.setForeground(Color.WHITE);
        btn.addActionListener(e -> {
            enviarComando("salir");
            Timer timerSalir = new Timer(500, evt -> {
                dispose();
                System.exit(0);
            });
            timerSalir.setRepeats(false);
            timerSalir.start();
        });
        return btn;
    }

    // --- LÓGICA DE APOYO ---

    private void enviarComando(String cmd) {
        try {
            if (!cmd.trim().isEmpty()) {
                añadirTextoConEstilo("\n>> " + cmd + "\n", new Color(255, 215, 0), true, true, 16);
            }
            outPipe.write((cmd + "\n").getBytes(StandardCharsets.UTF_8));
            outPipe.flush();
        } catch (IOException ex) { ex.printStackTrace(); }
    }

    private void añadirTextoConEstilo(String texto, Color color, boolean negrita, boolean cursiva, int tamaño) {
        SwingUtilities.invokeLater(() -> {
            StyledDocument doc = consola.getStyledDocument();
            SimpleAttributeSet estilo = new SimpleAttributeSet();
            StyleConstants.setForeground(estilo, color);
            StyleConstants.setFontFamily(estilo, "Georgia");
            StyleConstants.setFontSize(estilo, tamaño);
            StyleConstants.setBold(estilo, negrita);
            StyleConstants.setItalic(estilo, cursiva);
            try { doc.insertString(doc.getLength(), texto, estilo); consola.setCaretPosition(doc.getLength()); }
            catch (Exception e) { e.printStackTrace(); }
        });
    }

    private void darEstiloBoton(JButton btn) {
        btn.setFont(new Font("SansSerif", Font.BOLD, 13));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    private String mostrarDialogoBotones(String titulo, String mensaje, List<String> opciones) {
        if (opciones.isEmpty()) { JOptionPane.showMessageDialog(this, "Nada disponible."); return null; }
        JDialog dialog = new JDialog(this, titulo, true);
        dialog.setLayout(new BorderLayout());
        JPanel p = new JPanel(new GridLayout(0, 1, 5, 5));
        p.setBorder(new EmptyBorder(10, 20, 10, 20));
        final String[] s = {null};
        for (String op : opciones) {
            JButton b = new JButton(op);
            b.addActionListener(e -> { s[0] = op; dialog.dispose(); });
            p.add(b);
        }
        dialog.add(new JLabel(mensaje, SwingConstants.CENTER), BorderLayout.NORTH);
        dialog.add(new JScrollPane(p), BorderLayout.CENTER);
        JButton c = new JButton("Cancelar"); c.addActionListener(e -> dialog.dispose());
        dialog.add(c, BorderLayout.SOUTH);
        dialog.pack(); dialog.setLocationRelativeTo(this); dialog.setVisible(true);
        return s[0];
    }

    // --- ACCESO A DATOS ---
    private boolean juegoIniciado() { return juegoMotor.getHabitacionActual() != null; }
    private Jugador obtenerJugadorSilencioso() { return juegoMotor.preparacionDeAventuraConfigParaGuardado().getJugador(); }
    private List<String> obtenerSalidas() { return new ArrayList<>(juegoMotor.getHabitacionActual().getMapa().keySet()); }
    private List<String> obtenerTodosLosObjetos() {
        List<String> l = new ArrayList<>();
        juegoMotor.getHabitacionActual().getObjetos().forEach(o -> l.add(o.getNombre()));
        Jugador jug = obtenerJugadorSilencioso();
        if (jug != null) jug.getInventario().forEach(o -> l.add(o.getNombre()));
        return l;
    }
    private List<String> obtenerObjetosInventariables() {
        List<String> l = new ArrayList<>();
        juegoMotor.getHabitacionActual().getObjetos().stream().filter(o -> o instanceof Inventariable).forEach(o -> l.add(o.getNombre()));
        return l;
    }
    private List<String> obtenerContenedores() {
        List<String> l = new ArrayList<>();
        juegoMotor.getHabitacionActual().getObjetos().stream().filter(o -> o instanceof Contenedor).forEach(o -> l.add(o.getNombre()));
        return l;
    }

    private void enviarManual() { String t = inputTexto.getText(); if (!t.isEmpty()) { inputTexto.setText(""); enviarComando(t); } }

    private void redirigirStreams() {
        OutputStream os = new OutputStream() {
            @Override public void write(int b) { añadirTextoConEstilo(new String(new byte[]{(byte)b}, StandardCharsets.UTF_8), new Color(230, 230, 235), false, false, 16); }
            @Override public void write(byte[] b, int o, int l) { añadirTextoConEstilo(new String(b, o, l, StandardCharsets.UTF_8), new Color(230, 230, 235), false, false, 16); }
        };
        System.setOut(new PrintStream(os, true, StandardCharsets.UTF_8));
        System.setErr(new PrintStream(os, true, StandardCharsets.UTF_8));
        try { PipedInputStream in = new PipedInputStream(); outPipe = new PipedOutputStream(in); System.setIn(in); }
        catch (IOException e) { e.printStackTrace(); }
    }

    interface ProveedorOpciones { List<String> obtener(); }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try { UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); } catch (Exception e) {}
            JuegoGUI gui = new JuegoGUI();
            gui.setVisible(true);
            new Thread(() -> {
                try {
                    gui.juegoMotor.menuInicial();
                    gui.juegoMotor.preparacionJuego();
                    gui.juegoMotor.iniciarJuego();
                } catch (Exception e) { e.printStackTrace(); }
            }).start();
        });
    }
}