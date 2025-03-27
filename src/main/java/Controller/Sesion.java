package Controller;

import Model.Usuario;
import View.VoluntariadoView;

import java.sql.SQLOutput;

public class Sesion {
    private Usuario usuarioLogeado;
    private static Sesion sesion;

    /**
     * Obtiene la instancia única de la sesión (Singleton).
     * Si no existe, la crea con el usuario proporcionado.
     *
     * @param usuario Usuario que inicia sesión (no puede ser null).
     * @return Instancia única de Sesion.
     */


    public static Sesion getSesion(Usuario usuario) {
        if (sesion == null){
            sesion = new Sesion(usuario);
        }
        return sesion;
    }

    /**
     * Constructor privado para evitar instancias externas (Singleton).
     *
     * @param usuarioLogeado Usuario asociado a la sesión.
     */

    private Sesion(Usuario usuarioLogeado) {
        this.usuarioLogeado = usuarioLogeado;
    }

    /**
     * Gestiona el menú principal después del login.
     * - Muestra opciones según el rol (voluntario/creador).
     * - Mantiene la sesión activa hasta que el usuario cierra sesión (opción 8).
     */


    public void sesionIniciada() {
        int opcion;
        do {
            opcion = VoluntariadoView.mostrarMenuVoluntario();
            switch (opcion) {
                case 1:
                    System.out.println("Opción Ver perfil"); // Llamar a método para mostrar perfil
                    break;
                case 2:  // Listar iniciativas del usuario
                    System.out.println("Opción: Mostrar iniciativas en las que participas");
                    break;
                case 3:
                    System.out.println("Opción: Mostrar actividades en que realice"); //Listar actividades asignadas
                    break;
                case 4:
                    System.out.println("Iniciativas disponibles"); //Listar iniciativas disponibles
                    break;
                case 5:
                    System.out.println("Cambiar estado de actividad"); //Cambiar estado de actividad
                    break;
                case 6:
                    System.out.println("Hacer comentario de una actividad"); //Hacer comentario de una actividad
                    break;
                case 7:
                    System.out.println("me saltao alguna"); // ?¿?¿?¿?¿?
                    break;
                case 8:
                    System.out.println("Cerrando sesión..."); // Cerrar sesión
                    this.usuarioLogeado = null;

                    break;
            }

        } while (opcion != 8);

    }
}
