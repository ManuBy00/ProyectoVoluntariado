import Controller.UsuariosController;
import Exceptions.UsuarioNoExiste;
import Model.ListaUsuarios;
import View.VoluntariadoView;

public class Test {
    public static void main(String[] args) {

        ListaUsuarios l = new ListaUsuarios();

        UsuariosController lController = new UsuariosController(l);
        int opcion;
        do{
            opcion = View.VoluntariadoView.mostrarMenuInicial();

            switch (opcion) {
                case 1:
                    try {
                        lController.iniciarSesion();
                    } catch (UsuarioNoExiste e) {
                        VoluntariadoView.mostrarMensaje(e.getMessage());
                    }
                    break;
                case 2:
                    lController.registrarUsuario();
                    break;
                case 3:
                    VoluntariadoView.mostrarMensaje("Saliendo...");
                    break;
                default:
                    VoluntariadoView.mostrarMensaje("Opción no válida");
            }
        }while (opcion != 3);
    }
}
