package Controller;

import Model.Creador;
import Utils.Sesion;
import View.VoluntariadoView;

public class CreadorController {
    private Creador creador;

    public CreadorController() {
        this.creador = (Creador) Sesion.getInstancia().getUsuarioIniciado();
    }

    public void ejecutarSesion() {
        int opcion;
        do {
            opcion = VoluntariadoView.mostrarMenuCreador();
            switch (opcion) {
                case 1:
                    System.out.println("Ver perfil");
                    System.out.println(creador.toString());
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
                    Sesion.getInstancia().logOut();
                    break;
            }
        } while (opcion != 5);
    }
}
