package View;

import Model.*;
import Utils.Utilidades;

import java.util.HashSet;


public class UsuariosView {
    // Códigos de colores ANSI
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    /**
     * Muestra un menú inicial al usuario para que elija una opción.
     *
     * @return devuelve un entero con la opción seleccionada.
     */
    public static int mostrarMenuInicial() {
        int opcion;
        do {
            mostrarMensaje("\n" + ANSI_CYAN + "*************************************************" + ANSI_RESET);
            mostrarMensaje(ANSI_YELLOW + "                *** VOLUNTAPP ***                " + ANSI_RESET);
            mostrarMensaje(ANSI_CYAN + "*************************************************" + ANSI_RESET);
            mostrarMensaje(ANSI_GREEN + "1. \uD83D\uDD11 Iniciar sesión como creador" + ANSI_RESET);
            mostrarMensaje(ANSI_GREEN + "2. \uD83D\uDD11 Iniciar sesión como voluntario" + ANSI_RESET);
            mostrarMensaje(ANSI_GREEN + "3. \uD83D\uDCDD Registrar usuario" + ANSI_RESET);
            mostrarMensaje(ANSI_RED + "4. ❌ Salir" + ANSI_RESET);
            mostrarMensaje(ANSI_CYAN + "*************************************************" + ANSI_RESET);

            opcion = Utilidades.pideEntero("Seleccione una opción: ");
            if (opcion < 1 || opcion > 4) {
                mostrarMensaje(ANSI_RED + "❌ Opción no válida. Inténtelo de nuevo." + ANSI_RESET);
            }
        } while (opcion < 1 || opcion > 4);
        return opcion;
    }

    public static int mostrarMenuAjustesUsuario() {
        int opcion;
        do {
            mostrarMensaje("\n" + ANSI_BLUE + "**********************************************" + ANSI_RESET);
            mostrarMensaje(ANSI_PURPLE + "              *** Ajustes usuario ***        " + ANSI_RESET);
            mostrarMensaje(ANSI_BLUE + "**********************************************" + ANSI_RESET);
            mostrarMensaje(ANSI_GREEN + "1. \uD83D\uDD11 Cambiar credenciales" + ANSI_RESET);
            mostrarMensaje(ANSI_GREEN + "2. ❌ Eliminar usuario" + ANSI_RESET);
            mostrarMensaje(ANSI_YELLOW + "3. \uD83D\uDD19 Volver al menú principal" + ANSI_RESET);
            mostrarMensaje(ANSI_BLUE + "**********************************************" + ANSI_RESET);

            opcion = Utilidades.pideEntero("Seleccione una opción: ");
            if (opcion < 1 || opcion > 3) {
                mostrarMensaje(ANSI_RED + "❌ Opción no válida. Inténtelo de nuevo." + ANSI_RESET);
            }
        } while (opcion < 1 || opcion > 3);
        return opcion;
    }

    /**
     * Muestra un menú al Voluntario para que elija una opción.
     *
     * @return devuelve un entero con la opción seleccionada.
     */
    public static int mostrarMenuVoluntario() {
        int opcion;

        do {
            System.out.println(ANSI_BLUE + "\n=================================================" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "                 *** MENÚ VOLUNTARIO ***          " + ANSI_RESET);
            System.out.println(ANSI_BLUE + "=================================================" + ANSI_RESET);
            System.out.println(ANSI_GREEN + "1. \uD83D\uDC64 Ver mi perfil" + ANSI_RESET);
            System.out.println(ANSI_GREEN + "2. \uD83D\uDCC5 Ver mis actividades" + ANSI_RESET);
            System.out.println(ANSI_GREEN + "3. \uD83D\uDD04 Cambiar estado actividad" + ANSI_RESET);
            System.out.println(ANSI_GREEN + "4. \uD83D\uDCAC Comentar actividad" + ANSI_RESET);
            System.out.println(ANSI_GREEN + "5. ➕ Unirse a una actividad" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "6. \uD83D\uDED2 Tienda de puntos (próximamente)" + ANSI_RESET);
            System.out.println(ANSI_GREEN + "7. ⚙\uFE0F Ajustes de usuario" + ANSI_RESET);
            System.out.println(ANSI_RED + "8. \uD83D\uDEAA Cerrar sesión" + ANSI_RESET);
            System.out.println(ANSI_BLUE + "=================================================" + ANSI_RESET);

            opcion = Utilidades.pideEntero("Seleccione una opción (1-8): ");
            if (opcion < 1 || opcion > 8) {
                System.out.println(ANSI_RED + "❌ Error: Ingrese un número entre 1 y 8." + ANSI_RESET);
            }
        } while (opcion < 1 || opcion > 8);

        return opcion;
    }


    /**
     * Muestra un menú exclusivo para el creador.
     *
     * @return devuelve un entero con la opción seleccionada.
     */
    public static int mostrarMenuCreador() {
        int opcion;
        do {
            mostrarMensaje("\n" + ANSI_BLUE + "**********************************************" + ANSI_RESET);
            mostrarMensaje(ANSI_YELLOW + "                *** MENÚ CREADOR ***           " + ANSI_RESET);
            mostrarMensaje(ANSI_BLUE + "**********************************************" + ANSI_RESET);
            mostrarMensaje(ANSI_GREEN + "1. \uD83D\uDC64 Ver perfil" + ANSI_RESET);
            mostrarMensaje(ANSI_GREEN + "2. \uD83D\uDCCB Gestión de iniciativas" + ANSI_RESET);
            mostrarMensaje(ANSI_GREEN + "3. \uD83D\uDEE0 Gestión de actividades" + ANSI_RESET);
            mostrarMensaje(ANSI_GREEN + "4. ⚙\uFE0F Ajustes de usuario" + ANSI_RESET);
            mostrarMensaje(ANSI_RED + "5. \uD83D\uDEAA Salir" + ANSI_RESET);
            mostrarMensaje(ANSI_BLUE + "**********************************************" + ANSI_RESET);

            opcion = Utilidades.pideEntero("Seleccione una opción: ");
        } while (opcion < 1 || opcion > 5);
        return opcion;
    }

    /**
     * Metodo para mostrar un mensaje por pantalla
     *
     * @param msg el mensaje a mostrar
     */
    public static void mostrarMensaje(String msg) {
        System.out.println(msg);
    }

    /**
     * Metodo para crear un nuevo usuario
     *
     * @param tipoUsuario el tipo de usuario a crear
     * @return devuelve un objeto de tipo Usuario
     */
    public static Usuario crearUsuario(int tipoUsuario) {
        String nombre = Utilidades.pideString("Introduce el nombre de usuario");
        String password = pidePassword();
        String correo = pideCorreo();

        Usuario nuevoUsuario = null;

        switch (tipoUsuario) {
            case 1:
                String ong = Utilidades.pideString("Introduce la ONG a la que perteneces.");
                nuevoUsuario = new Creador(nombre, password, correo, ong);
                break;
            case 2:
                nuevoUsuario = new Voluntario(nombre, password, correo);
                break;
        }

        return nuevoUsuario;
    }

    /**
     * Metodo para pedir una contraseña al usuario
     *
     * @return devuelve un String con la contraseña introducida por el usuario
     */
    public static String pidePassword() {
        String password;
        boolean esValido = false;

        do {
            password = Utilidades.pideString("Introduce la contraseña");
            if (password.length() < 8) {
                System.out.println("La contraseña debe tener al menos 8 caracteres");
            } else {
                esValido = true;
            }
        } while (!esValido);

        return password;
    }

    /**
     * Metodo para pedir un correo al usuario
     *
     * @return devuelve un String con el correo introducido por el usuario
     */
    public static String pideCorreo() {
        boolean esValido = false;
        String correo;
        do {
            correo = Utilidades.pideString("Introduce el correo");
            if (!Utilidades.validarCorreo(correo)) {
                mostrarMensaje("Formato de correo no válido.");
            } else {
                esValido = true;
            }
        } while (!esValido);
        return correo;
    }

    /**
     * Metodo para elegir el tipo de usuario puede ser creador o voluntario
     *
     * @return devuelve un entero con la opción seleccionada
     */
    public static int elegirTipoUsuario() {
        int opcion;
        do {
            System.out.println("\n¿Qué tipo de usuario deseas registrar?");
            System.out.println("1. Creador");
            System.out.println("2. Voluntario");
            opcion = Utilidades.pideEntero("Elige una opción");

        } while (opcion < 1 || opcion > 2);

        return opcion;
    }

    /**
     * muestra la lista de todos los voluntarios
     */
    public static void mostrarTodosLosVoluntarios(){
        HashSet<Voluntario> listaVoluntarios = ListaUsuarios.getInstance().getVoluntarios();
        UsuariosView.mostrarMensaje("Voluntarios disponibles:\n");
        for (Voluntario v : listaVoluntarios){
            UsuariosView.mostrarMensaje(v.getNombre() + " , " + v.getCorreo()+ " | ");
        }
    }

    /**
     * Imprime una lista de voluntarios disponibles para añadir a una actividad.
     * No muestra los que ya están asigandos a esta actividad
     * @param actividad recibe la actividad en la que se va a añadir un voluntario
     */
    public static boolean mostrarVoluntariosDisponibles(Actividad actividad) {
        boolean disponible = true;
        StringBuilder voluntariosDisponibles = new StringBuilder();
        // Recorre todos los voluntarios y agrega los que no están asignados a la actividad
        for (Voluntario v : ListaUsuarios.getInstance().getVoluntarios()) {
            if (!actividad.getVoluntariosAsignados().contains(v)) {
                voluntariosDisponibles.append(v.getNombre()).append(", ").append(v.getCorreo()).append(" | ");
            }
        }

        // Si hay voluntarios disponibles, muestra la lista
        if (!voluntariosDisponibles.isEmpty()) {
            mostrarMensaje(voluntariosDisponibles.toString());
        } else {
            // Si no hay voluntarios disponibles, imprime "false"
            disponible = false;
            mostrarMensaje("No hay voluntarios disponibles.");
        }
        return disponible;
    }
}