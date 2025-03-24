package Model;

import View.VoluntariadoView;

public class Sesion {
    private Usuario usuarioLogeado;
    private static Sesion sesion;

    public static Sesion getSesion(Usuario usuario) {
        if (sesion == null){
            sesion = new Sesion(usuario);
        }
        return sesion;
    }

    private Sesion(Usuario usuarioLogeado) {
        this.usuarioLogeado = usuarioLogeado;
    }

    public void sesionCreador() {
        int opcion;
        do {
            opcion = VoluntariadoView.mostrarMenuCreador();
            switch (opcion) {
                case 1:
                    System.out.println("Ver perfil");
                    break;
                case 2:
                    System.out.println("Crear iniciativa");
                    break;
                case 3:
                    System.out.println("Asignar voluntarios a actividades");
                    break;
                case 4:
                    System.out.println("Iniciativas disponibles");
                    break;

                case 5:
                    System.out.println("Cerrando sesión...");
                    this.usuarioLogeado = null;
                    break;
            }

        } while (opcion != 5);
    }

    public void sesionVoluntario() {
        int opcion;
        do {
            opcion = VoluntariadoView.mostrarMenuVoluntario();
            switch (opcion) {
                case 1:
                    System.out.println("Ver perfil");
                    break;
                case 2:
                    System.out.println("Ver mis actividades");
                    break;
                case 3:
                    System.out.println("Cambiar estado de actividad");
                    break;
                case 4:
                    System.out.println("Iniciativas disponibles");
                    break;
                case 5:
                    System.out.println("Cambiar estado de actividad");
                    break;
                case 6:
                    System.out.println("Hacer comentario de una actividad");
                    break;
                case 7:
                    System.out.println("me saltao alguna");
                    break;
                case 8:
                    System.out.println("Cerrando sesión...");
                    this.usuarioLogeado = null;
                    break;
            }
        } while (opcion != 8);
    }
}
