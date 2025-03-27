import Controller.UsuariosController;
import Exceptions.UsuarioNoExiste;
import Model.ListaUsuarios;
import View.UsuariosView;

public class Test {
    public static void main(String[] args) {

        ListaUsuarios l = ListaUsuarios.getInstance();

        UsuariosController lController = new UsuariosController(l);
        int opcion;
        do{
            opcion = UsuariosView.mostrarMenuInicial();

            switch (opcion) {
                case 1:
                    try {
                        lController.iniciarSesion();
                    } catch (UsuarioNoExiste e) {
                        UsuariosView.mostrarMensaje(e.getMessage());
                    }
                    break;
                case 2:
                    lController.registrarUsuario();
                    break;
                case 3:
                    UsuariosView.mostrarMensaje("Saliendo...");
                    break;
                default:
                    UsuariosView.mostrarMensaje("Opción no válida");
            }
        }while (opcion != 3);
    }
}
