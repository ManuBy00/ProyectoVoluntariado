package Controller;

import Model.Usuario;
import View.VoluntariadoView;

import java.sql.SQLOutput;

public class Sesion {
    private Usuario usuarioLogeado;

    public Sesion(Usuario usuarioLogeado) {
        this.usuarioLogeado = usuarioLogeado;
    }

    public void sesionIniciada() {
        int opcion;
        do {
            opcion = VoluntariadoView.mostrarMenuVoluntario();
            switch (opcion) {
                case 1:
                    System.out.println("Opción Ver perfil");
                    break;
                case 2:
                    System.out.println("Opción: Mostrar iniciativas en las que participas");
                    break;
                case 3:
                    System.out.println("Opción: Mostrar actividades en que realice");
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
