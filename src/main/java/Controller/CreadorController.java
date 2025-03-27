package Controller;

import Model.Creador;
import Model.Iniciativa;
import Utils.Sesion;
import View.UsuariosView;

/**
 * Controlador para manejar las acciones del creador.
 */
public class CreadorController {
    private Creador creador;
    private IniciativaController iniciativaController;

    /**
     * Constructor que inicializa el creador con la sesión actual.
     */
    public CreadorController() {
        this.creador = (Creador) Sesion.getInstancia().getUsuarioIniciado();
        this.iniciativaController = new IniciativaController();
    }

    /**
     * Metodo para ejecutar la sesión del creador.
     */
    public void ejecutarSesion() {
        int opcion;
        do {
            // Muestra el menú del creador y obtiene la opción seleccionada
            opcion = UsuariosView.mostrarMenuCreador();
            switch (opcion) {
                case 1:
                    // Ver perfil del creador
                    System.out.println("Ver perfil");
                    System.out.println(creador.toString());
                    break;
                case 2:
                    // Crear una nueva iniciativa

                    iniciativaController.addIniciativa();



                    //creador.addIniciativa();
                    break;
                case 3:
                    // Asignar voluntarios a actividades

                    System.out.println("Asignar voluntarios a actividades");
                    break;
                case 4:
                    // Añadir actividades

                    break;
                case 5: // Mostrar iniciativas

                    break;
                case 6:
                    // Cerrar sesión
                    System.out.println("Cerrando sesión...");
                    Sesion.getInstancia().logOut();
                    break;
                default:
                    // Opción no válida
                    System.out.println("Opción no válida. Intente de nuevo.");
                    break;
            }
        } while (opcion != 6);
    }
}