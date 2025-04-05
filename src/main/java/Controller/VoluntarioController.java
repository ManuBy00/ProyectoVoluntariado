package Controller;
import Exceptions.ActividadNoExiste;
import Model.*;
import Utils.Sesion;
import Utils.Utilidades;
import View.UsuariosView;
import View.ViewActividades;
import jdk.jshell.execution.Util;

import java.util.ArrayList;
import java.util.List;


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
    protected void ejecutarSesion() {
        int opcion;
        do {
            opcion = UsuariosView.mostrarMenuVoluntario(); // Muestra el menú y obtiene la opción
            switch (opcion) {
                case 1: verPerfil(); break;
                case 2: verMisActividades(); break;
                case 3: cambiarEstadoActividad(); break;
                case 4: comentarActividad(); break;

                case 5:
                    try {
                        unirseActividad();
                    }catch (ActividadNoExiste e) {
                        UsuariosView.mostrarMensaje(e.getMessage());
                    }
                break;
                case 6: tiendaPuntos(); break;
                case 7: ajustesUsuario(); break;
                case 8: System.out.println("Cerrando sesión..."); break;
                default: System.out.println("Opción no válida.");
            }
        } while (opcion != 8);
    }

    /**
     * Permite al voluntario unirse a una actividad.
     * Este metodo muestra las actividades disponibles y permite al voluntario elegir una para unirse.
     */

    private void unirseActividad() {
        //obtenemos la lista de actividades disponibles para el usuario
        ArrayList<Actividad> actividadesDisponibles = getActividadesDisponibles();

        //si no hay, se lanza excepción
        if (actividadesDisponibles.isEmpty()) {
            throw new ActividadNoExiste("No hay actividades disponibles");
        }
        int actividadElegida;
        boolean opcionValida = true;
        //mostramos lista en pantalla y le pedimos opción al usuario
        do {
            ViewActividades.mostrarActividadesDisponibles(actividadesDisponibles);
            actividadElegida = Utilidades.pideEntero("Elige el número de la actividad para unirte:");
            if (actividadElegida < 1 || actividadElegida > actividadesDisponibles.size()) {
                UsuariosView.mostrarMensaje("Opción inválida.");
                opcionValida = false;
            }
        }while (!opcionValida);

        Actividad actividad = actividadesDisponibles.get(actividadElegida - 1);
        voluntario.getActividadesAsignadas().add(actividad);
        actividad.getVoluntariosAsignados().add(voluntario.getNombre());
        UsuariosView.mostrarMensaje("Te has unido a la actividad: " + actividad.getNombre());

    }

    /**
     * Permite al voluntario acceder a la tienda de puntos.
     * Este metodo muestra las opciones disponibles en la tienda de puntos.
     */

    private void tiendaPuntos() {
        int opcion;
        do {
            opcion = UsuariosView.mostrarMenuTiendaPuntos();
            switch (opcion) {
                case 1: canjearPuntos(); break;
                case 2: UsuariosView.mostrarMensaje("💰 Tu saldo actual es: " +
                        UsuariosView.ANSI_YELLOW + voluntario.getPuntos() +
                        " puntos" + UsuariosView.ANSI_RESET);
                    break;
                case 3: System.out.println("Saliendo de la tienda..."); break;
                default: System.out.println("Opción no válida.");
            }
        } while (opcion != 3);
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
        int i = 1;
        for (Actividad actividad : voluntario.getActividadesAsignadas()) {
            System.out.println(i + "." + actividad.toString());
            i++; // Aumenta el índice para la próxima actividad
        }
    }

    /**
     * Permite cambiar el estado de una actividad asignada al voluntario.
     * Este metodo muestra las actividades del voluntario, permite seleccionar una
     * actividad y cambiar su estado a pendiente, en progreso o finalizada.
     */
    private void cambiarEstadoActividad() {
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
            addPuntosVoluntario(actividad); // Añadir puntos si la actividad se ha completado
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


    /**
     * pide al usuario que elija una actividad (numerada)
     * @return numero elegido
     */
    private int eligeActividad(){
        int actividadElegida = Utilidades.pideEntero("Elige el número de la actividad:");

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
        mostrarActvidadesVoluntario();
        int actividadElegida = eligeActividad();
        Actividad actividad = voluntario.getActividadesAsignadas().get(actividadElegida - 1);
        String comentario = Utilidades.pideString("Introduce el nuevo comentario");
        actividad.setComentario(comentario);
    }


    /**
     * Llama a la lista de actividades sin terminar y añade un nuevo filtro para no mostrar actividades
     * en las que el usuario ya paticipa.
     * @return lista de actividades disponibles para un usuario en concreto
     */
    private ArrayList<Actividad> getActividadesDisponibles() {
        ArrayList<Actividad> actividadesDisponibles = ListaIniciativas.getInstance().getActividadesSinTerminar();
        ArrayList<Actividad> actividadesDispUsuario = new ArrayList<>();
        for (Actividad actividad : actividadesDisponibles) {
            if (!actividad.getVoluntariosAsignados().contains(Sesion.getInstancia().getUsuarioIniciado().getNombre() + " | " + Sesion.getInstancia().getUsuarioIniciado().getCorreo())){
                actividadesDispUsuario.add(actividad);
            }
        }
        return actividadesDispUsuario;
    }


    private void canjearPuntos() {
        // Lista de recompensas premium
        List<Recompensa> recompensas = List.of(
                new Recompensa("Certificado de Reconocimiento Voluntario", 200),
                new Recompensa("Kit de Merchandising Exclusivo (Camiseta + Taza)", 500),
                new Recompensa("Curso Online de Liderazgo Social", 800),
                new Recompensa("Experiencia VIP en Evento Benéfico", 1500),
                new Recompensa("Financiación para Tu Propio Proyecto Solidario", 3000)
        );

        // Mostrar recompensas
        UsuariosView.mostrarMensaje("\n" + UsuariosView.ANSI_CYAN + "🌟 TIENDA DE RECOMPENSAS 🌟" + UsuariosView.ANSI_RESET);
        for (int i = 0; i < recompensas.size(); i++) {
            Recompensa r = recompensas.get(i);
            UsuariosView.mostrarMensaje(UsuariosView.ANSI_GREEN + (i+1) + ". " + r.getNombre() +
                    UsuariosView.ANSI_YELLOW + " - " + r.getCosto() +
                    " puntos" + UsuariosView.ANSI_RESET);
        }

        int eleccion = Utilidades.pideEntero("\nElige una recompensa (0 para cancelar):");
        if (eleccion == 0) return;

        if (eleccion < 1 || eleccion > recompensas.size()) {
            UsuariosView.mostrarMensaje("❌ Opción inválida");
            return;
        }

        Recompensa seleccionada = recompensas.get(eleccion-1);
        if (voluntario.getPuntos() >= seleccionada.getCosto()) {
            voluntario.restarPuntos(seleccionada.getCosto());
            voluntario.getRecompensasDisponibles().add(seleccionada);
            UsuariosView.mostrarMensaje(UsuariosView.ANSI_GREEN + "🎉 ¡Has canjeado: '" + seleccionada.getNombre() + "'!" + UsuariosView.ANSI_RESET);
        } else {
            UsuariosView.mostrarMensaje("❌ No tienes suficientes puntos. Sigue participando en actividades!");
        }
    }


    /**
     * Añade 100 puntos al completar una actividad.
     * @param actividad Actividad completada
     */
    private void addPuntosVoluntario(Actividad actividad) {
        voluntario.addPuntos(100);
        UsuariosView.mostrarMensaje(UsuariosView.ANSI_GREEN + "✅ ¡+" + 100 +
                " puntos por completar '" + actividad.getNombre() +
                "'!" + UsuariosView.ANSI_RESET);
    }
    /**
     * Switch para ejecutar las opciones de borrar usaurio y cambiar datos de usuario
     */
    private void ajustesUsuario(){
        UsuariosController u = new UsuariosController(ListaUsuarios.getInstance());
        int opcion;
        do {
            opcion=UsuariosView.mostrarMenuAjustesUsuario();
            switch (opcion){
                case 1:
                    u.updateUsuario(voluntario);
                    break;
                case 2:
                    u.removeUsuario(voluntario);
                    Sesion.getInstancia().logOut();
                    UsuariosView.mostrarMensaje("Usuario eliminado. Saliendo de la aplicación...");
                    break;
                case 3:
                    break;
            }
        }while (opcion !=3);
    }
}
