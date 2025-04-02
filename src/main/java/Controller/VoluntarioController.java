package Controller;
import Exceptions.ActividadNoExiste;
import Model.*;
import Utils.Sesion;
import Utils.Utilidades;
import View.UsuariosView;
import View.ViewActividades;

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
    public void ejecutarSesion() {
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
        actividad.getVoluntariosAsignados().add(voluntario);
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
                case 1: comprarProducto(); break;
                case 2: verProductosDisponibles(); break;
                case 3: System.out.println("Saliendo de la tienda..."); break;
                default: System.out.println("Opción no válida.");
            }
        } while (opcion != 3);
    }

    private void comprarProducto() {
        List<Producto> productos = ListaUsuarios.getInstance().getProductosDisponibles();

        if (productos.isEmpty()) {
            UsuariosView.mostrarMensaje("No hay productos disponibles en la tienda.");
            return;
        }

        System.out.println("\n--- Productos Disponibles ---");
        for (int i = 0; i < productos.size(); i++) {
            System.out.println((i + 1) + ". " + productos.get(i) + " - " + productos.get(i).getPrecio() + " puntos");
        }

        int productoElegido = Utilidades.pideEntero("Elige el número del producto que deseas comprar:");

        if (productoElegido < 1 || productoElegido > productos.size()) {
            UsuariosView.mostrarMensaje("Opción inválida.");
            return;
        }

        Producto producto = productos.get(productoElegido - 1);
        if (voluntario.getPuntos() >= producto.getPrecio()) {
            voluntario.restarPuntos((int) producto.getPrecio());
            UsuariosView.mostrarMensaje("Has comprado: " + producto.getNombre());
        } else {
            UsuariosView.mostrarMensaje("No tienes suficientes puntos.");
        }
    }

    private void verProductosDisponibles() {
        List<Producto> productos = ListaUsuarios.getInstance().getProductosDisponibles();

        if (productos.isEmpty()) {
            UsuariosView.mostrarMensaje("No hay productos disponibles en la tienda.");
            return;//lanzar excepción en vez de return
        }

        System.out.println("\n--- Productos Disponibles ---");
        for (Producto producto : productos) {
            System.out.println(producto.getNombre() + " - " + producto.getPrecio() + " puntos");
        }
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

    /**
     * Llama a la lista de actividades sin terminar y añade un nuevo filtro para no mostrar actividades
     * en las que el usuario ya paticipa.
     * @return lista de actividades disponibles para un usuario en concreto
     */
    public ArrayList<Actividad> getActividadesDisponibles() {
        ArrayList<Actividad> actividadesDisponibles = ListaIniciativas.getInstance().getActividadesSinTerminar();
        ArrayList<Actividad> actividadesDispUsuario = new ArrayList<>();
        for (Actividad actividad : actividadesDisponibles) {
            if (!actividad.getVoluntariosAsignados().contains((Voluntario) Sesion.getInstancia().getUsuarioIniciado())){
                actividadesDispUsuario.add(actividad);
            }
        }
        return actividadesDispUsuario;
    }
}
