package View;

import Exceptions.UsuarioNoExiste;
import Model.*;
import Utils.Sesion;
import Utils.Utilidades;

import java.time.LocalDate;
import java.util.ArrayList;


public class ViewActividades {

    // Códigos ANSI para los colores
    public static final String ANSI_RESET = "\u001B[0m";   // Resetea el color
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_MAGENTA = "\u001B[35m";


    public static Actividad pedirDatosActividad() {
        String nombre = Utilidades.pideString("Nombre de la Actividad:");
        String descripcion = Utilidades.pideString("Descripción:");
        LocalDate fechaInicio;
        LocalDate fechaFin;
        boolean fechaValidada;
        do {
             fechaInicio = Utilidades.pideFecha("Introduce la fecha de inicio.");
             fechaFin = Utilidades.pideFecha("Introduce la final de fin.");
             if (!Utilidades.validarFechaInicioFin(fechaInicio,fechaFin)){
                 UsuariosView.mostrarMensaje("Las fecha de inicio no puede ser posterior a la final.");
                 fechaValidada = false;
            }else {
                 fechaValidada = true;
            }
        }while (!fechaValidada);

        Voluntario encargado;
        encargado = asignarEncargado();
        String comentario = Utilidades.pideString("Introduce un comentario");
        Actividad actividad = new Actividad(nombre, descripcion, fechaInicio, fechaFin, encargado.getNombre(), comentario);
        encargado.getActividadesAsignadas().add(actividad); //le asignamos esta tarea al encargado.
        actividad.getVoluntariosAsignados().add(encargado); //Asignamos al encargado como voluntario en la actividad.
        return actividad;
    }

    /**
     * Muestra la lista de voluntarios para seleccionar un encargado.
     * @return
     * @throws UsuarioNoExiste
     */
    public static Voluntario asignarEncargado()throws UsuarioNoExiste{
        boolean encontrado = false;
        Voluntario encargado;
        do {
            UsuariosView.mostrarTodosLosVoluntarios(); //muestra lista de voluntarios
            String correoVoluntario = Utilidades.pideString("Introduce el correo de un voluntario para asignarlo como encargado.");
            encargado = ListaUsuarios.getInstance().encontrarVoluntario(correoVoluntario);
            if (encargado!=null){
                encontrado = true;
            }else {
                View.UsuariosView.mostrarMensaje("El usuario seleccionado no es válido.");
            }
        }while (!encontrado);
        return encargado;
    }

    public static void mostrarOpcionesEstado() {
    // Encabezado del menú con color y estilo
        UsuariosView.mostrarMensaje(ANSI_BLUE + "\n===========================" + ANSI_RESET);
        UsuariosView.mostrarMensaje(ANSI_YELLOW + "  *** Cambiar Estado de la Actividad ***" + ANSI_RESET);
        UsuariosView.mostrarMensaje(ANSI_BLUE + "===========================" + ANSI_RESET);

        // Opciones con iconos y colores
        UsuariosView.mostrarMensaje(ANSI_GREEN + "1. " + "⏳ Pendiente" + ANSI_RESET);
        UsuariosView.mostrarMensaje(ANSI_CYAN + "2. " + "🔄 En Progreso" + ANSI_RESET);
        UsuariosView.mostrarMensaje(ANSI_MAGENTA + "3. " + "✅ Finalizada" + ANSI_RESET);
        UsuariosView.mostrarMensaje(ANSI_RED + "4. " + "❌ Cancelada" + ANSI_RESET);

    // Separador visual con color
        UsuariosView.mostrarMensaje(ANSI_BLUE + "===========================" + ANSI_RESET);
    }

    public static void mostrarActividadesDisponibles(ArrayList<Actividad> actividadesDisponibles) {
        System.out.println("\n--- Actividades Disponibles ---");
        for (int i = 0; i < actividadesDisponibles.size(); i++) {
            System.out.println((i + 1) + ". " + actividadesDisponibles.get(i));
        }
    }
}

