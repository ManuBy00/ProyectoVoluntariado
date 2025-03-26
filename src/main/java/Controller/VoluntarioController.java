package Controller;

import Model.Voluntario;
import Utils.Sesion;
import View.VoluntariadoView;

public class VoluntarioController {
    private Voluntario voluntario;

    public VoluntarioController() {
        this.voluntario = (Voluntario) Sesion.getInstancia().getUsuarioIniciado();
    }

    public void ejecutarSesion() {
        int opcion;
        do {
            opcion = VoluntariadoView.mostrarMenuVoluntario();
            switch (opcion) {
                case 1:
                    System.out.println("Ver perfil");
                    System.out.println(voluntario.toString());
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
                    System.out.println("Hacer comentario de una actividad");
                    break;
                case 6:
                    System.out.println("Cerrando sesión...");
                    Sesion.getInstancia().logOut();
                    break;
                default:
                    System.out.println("Opción no válida. Inténtalo de nuevo.");
            }
        } while (opcion != 6);
    }
}
