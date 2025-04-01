package Controller;
import Model.Actividad;
import Model.EstadoActividad;
import Model.ListaUsuarios;
import Model.Voluntario;
import Utils.Sesion;
import Utils.Utilidades;
import View.UsuariosView;
import View.ViewActividades;


public class VoluntarioController {
    private Voluntario voluntario;

    /**
     * Constructor: recibe un voluntario y lo guarda.
     */
    public VoluntarioController() {
        this.voluntario = (Voluntario) Sesion.getInstancia().getUsuarioIniciado();
    }

    /**
     * Muestra el menú y ejecuta la opción elegida por el voluntario.
     */
    public void ejecutarSesion() {
        int opcion;
        do {
            opcion = UsuariosView.mostrarMenuVoluntario(); // Muestra el menú y obtiene la opción
            switch (opcion) {
                case 1: verPerfil(); break;
                case 2: verMisActividades(); break;
                case 3: cambiarEstadoActividad(); break;
                case 4: comentarActividad(); break;
                case 5: //Unirse actividad
                case 6: //Tienda de puntos
                case 7: ajustesUsuario();
                case 8: System.out.println("Cerrando sesión..."); break;
                default: System.out.println("Opción no válida.");
            }
        } while (opcion != 8);
    }


    /**
     * Muestra la información del voluntario.
     */
    private void verPerfil() {
        System.out.println("\n--- Mi Perfil ---");
        ListaUsuarios.getInstance().mostrar(voluntario);
    }

    /**
     * Lista las actividades asignadas al voluntario.
     */
    private void verMisActividades() {
        System.out.println("\n--- Mis Actividades ---");
        for (Actividad actividad : voluntario.getActividadesAsignadas()) {
            int i = 1;
            System.out.println(i + "." + actividad.toString());
            i++; // Aumenta el índice para la próxima actividad
        }
    }

    /**
     * Permite cambiar el estado de una actividad asignada al voluntario.
     * Este metodo muestra las actividades del voluntario, permite seleccionar una
     * actividad y cambiar su estado a pendiente, en progreso o finalizada.
     */
    public void cambiarEstadoActividad() {
        // Mostrar actividades del voluntario
        mostrarActvidadesVoluntario();

        // Seleccionar actividad
        int actividadElegida = eligeActividad();
        if (actividadElegida == 0) return; // Si la opción es inválida, termina el metodo

        Actividad actividad = voluntario.getActividadesAsignadas().get(actividadElegida - 1);

        // Mostrar opciones de estado
        ViewActividades.mostrarOpcionesEstado();

        // Seleccionar nuevo estado
        int numEstado = Utilidades.pideEntero("Elige el número del nuevo estado:");

        // Cambiar estado y sumar puntos si se completa
        if (cambiarEstado(actividad, numEstado) && actividad.getEstado() == EstadoActividad.FINALIZADA) {
            añadirPuntosVoluntario(actividad); // Añadir puntos si la actividad se ha completado
        }
    }

    /**
     * Cambia el estado de la actividad seleccionada según la opción elegida.
     * @param actividad La actividad cuyo estado se va a cambiar.
     * @param opcionElegida El número de la opción elegida para el nuevo estado.
     * @return true si el estado se cambió correctamente, false si la opción no es válida.
     */
    private boolean cambiarEstado(Actividad actividad, int opcionElegida) {
        switch (opcionElegida) {
            case 1:
                actividad.setEstado(EstadoActividad.PENDIENTE); // Cambia el estado a "PENDIENTE"
                break;
            case 2:
                actividad.setEstado(EstadoActividad.EN_PROGRESO); // Cambia el estado a "EN PROGRESO"
                break;
            case 3:
                actividad.setEstado(EstadoActividad.FINALIZADA); // Cambia el estado a "FINALIZADA"
                return true; // Indica que se completó la actividad
            default:
                UsuariosView.mostrarMensaje("❌ Estado no válido."); // Mensaje de error si la opción no es válida
                return false; // Retorna false si no se pudo cambiar el estado
        }
        return true; // Retorna true si el estado se cambió a PENDIENTE o EN PROGRESO
    }


    /* El metodo cambiarEstadoActividad gestiona la interacción del voluntario para seleccionar y cambiar el estado
             de una actividad, mientras que cambiarEstado se encarga específicamente de cambiar
              el estado de la actividad basada en la opción elegida por el voluntario. */


    private void añadirPuntosVoluntario(Actividad actividad) {
        // Sumar puntos a todos los voluntarios asignados a la actividad
        for (Voluntario v : actividad.getVoluntariosAsignados()) {
            v.añadirPuntos(100);  // Añadir 100 puntos a cada voluntario
        }
        UsuariosView.mostrarMensaje("✅ ¡+" + 100 + " puntos a todos los voluntarios asignados a la actividad '" + actividad.getNombre() + "'!");
    }


    // Pedir al usuario que elija una actividad
    private int eligeActividad(){
        int actividadElegida = Utilidades.pideEntero("Elige el número de la actividad para cambiar el estado:");

        // Verificar si la elección es válida
        if (actividadElegida < 1 || actividadElegida > voluntario.getActividadesAsignadas().size()) {
            UsuariosView.mostrarMensaje("Opción inválida.");
            return 0;
        }
        return actividadElegida;
    }

    // Mostrar todas las actividades del voluntario
    private void mostrarActvidadesVoluntario(){
        voluntario.mostrarActvidadesVoluntario();
    }

    /**
     * Muestra todas las actividades asignadas al voluntario.
     * Este metodo recorre la lista de actividades del voluntario y las imprime con su índice y estado actual.
     */
    private void comentarActividad() {
        // Mostrar un encabezado para las actividades
        System.out.println("\n--- Actividades Asignadas ---");

        // Inicializar el contador de índice para enumerar las actividades
        int index = 1;

        // Recorrer todas las actividades asignadas al voluntario
        for (Actividad actividad : voluntario.getActividadesAsignadas()) {
            // Mostrar la actividad con su índice y estado actual
            System.out.println(index + ". " + actividad); // La clase Actividad debe tener un metodo toString() adecuado
            index++; // Incrementar el índice para la siguiente actividad
        }
    }

    public void ajustesUsuario(){
        int opcion;
        do {
            opcion=UsuariosView.mostrarMenuAjustesUsuario();
            switch (opcion){
                case 1:
                    ListaUsuarios.getInstance().update(voluntario);
                    break;
                case 2:
                    ListaUsuarios.getInstance().remove(voluntario);
                    UsuariosView.mostrarMensaje("Usuario eliminado. Saliendo de la aplicación...");
                    break;
                case 3:
                    break;
            }
        }while (opcion !=3);
    }
}
