package View;

import Model.*;
import Utils.Sesion;
import Utils.Utilidades;

import java.util.HashSet;

public class IniciativaView {
    public static final String ANSI_RESET = "\u001B[0m";   // Resetea el color
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";
    public static final String ANSI_MAGENTA = "\u001B[35m";  // Magenta

    // Devuelve array de iniciativas
    public static Iniciativa pedirDatosIniciativa() {
        String nombre = Utilidades.pideString("Nombre de la iniciativa:");
        String descripcion = Utilidades.pideString("Descripción:");
        Iniciativa iniciativa = new Iniciativa(nombre, descripcion);
        return iniciativa;
    }

    public static int MenuIniciativas() {
        int opcion;

        // Encabezado con color y estilo
        UsuariosView.mostrarMensaje(ANSI_BLUE + "\n=========================" + ANSI_RESET);
        UsuariosView.mostrarMensaje(ANSI_YELLOW + " *** Gestión de Iniciativas ***" + ANSI_RESET);
        UsuariosView.mostrarMensaje(ANSI_BLUE + "=========================" + ANSI_RESET);

        // Opciones con iconos y colores
        UsuariosView.mostrarMensaje(ANSI_GREEN + "1. " + "➕ Crear iniciativa" + ANSI_RESET);
        UsuariosView.mostrarMensaje(ANSI_RED + "2. " + "🗑️ Eliminar iniciativa" + ANSI_RESET);
        UsuariosView.mostrarMensaje(ANSI_PURPLE + "3. " + "✏️ Modificar iniciativa" + ANSI_RESET);
        UsuariosView.mostrarMensaje(ANSI_CYAN + "4. " + "🔍 Buscar iniciativa" + ANSI_RESET);
        UsuariosView.mostrarMensaje(ANSI_BLUE + "5. " + "📋 Ver mis iniciativas" + ANSI_RESET);
        UsuariosView.mostrarMensaje(ANSI_YELLOW + "6. " + "↩️ Volver" + ANSI_RESET);

        // Solicitar opción
        opcion = Utilidades.pideEntero(ANSI_BLUE + "Elige una opción: " + ANSI_RESET);
        return opcion;
    }


    // Menú de gestión de iniciativas
    public static int mostrarMenuActividades() {
        // Encabezado con color y estilo
        UsuariosView.mostrarMensaje(ANSI_BLUE + "\n=========================" + ANSI_RESET);
        UsuariosView.mostrarMensaje(ANSI_YELLOW + " *** Gestión de Actividades ***" + ANSI_RESET);
        UsuariosView.mostrarMensaje(ANSI_BLUE + "=========================" + ANSI_RESET);

        // Opciones con iconos y colores
        UsuariosView.mostrarMensaje(ANSI_GREEN + "1. " + "➕ Crear actividad" + ANSI_RESET);
        UsuariosView.mostrarMensaje(ANSI_RED + "2. " + "🗑️ Eliminar actividad" + ANSI_RESET);
        UsuariosView.mostrarMensaje(ANSI_PURPLE + "3. " + "✏️ Modificar actividad" + ANSI_RESET);
        UsuariosView.mostrarMensaje(ANSI_CYAN + "4. " + "👥 Asignar voluntarios" + ANSI_RESET);
        UsuariosView.mostrarMensaje(ANSI_BLUE + "5. " + "📋 Mostrar iniciativas y actividades" + ANSI_RESET);
        UsuariosView.mostrarMensaje(ANSI_YELLOW + "6. " + "↩️ Volver" + ANSI_RESET);

        // Solicitar opción
        return Utilidades.pideEntero(ANSI_BLUE + "Elige una opción: " + ANSI_RESET);
    }

    public static void imprimirMisIniciativas(){
        HashSet<Iniciativa> misIniciativas = ListaIniciativas.getInstance().obtenerIniciativasPorCreador(Sesion.getInstancia().getUsuarioIniciado().getCorreo());

        UsuariosView.mostrarMensaje("Iniciativas disponibles:\n");
        for (Iniciativa i : misIniciativas){
            View.UsuariosView.mostrarMensaje(i.toString());
            UsuariosView.mostrarMensaje("---------------------");
        }
    }



}