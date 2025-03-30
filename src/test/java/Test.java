import Controller.UsuariosController;
import Exceptions.UsuarioNoExiste;
import Model.Creador;
import Model.ListaUsuarios;
import Model.Voluntario;
import View.UsuariosView;

public class Test {
    public static void main(String[] args) {

        ListaUsuarios l = ListaUsuarios.getInstance();

        UsuariosController lController = new UsuariosController(l);
        int opcion;

        Creador pepe = new Creador("pepe", "12345678", "pepe@gmail.com", "ong");
        Voluntario juan = new Voluntario("juan", "12345678","juan@gmail.com");
        Voluntario jose = new Voluntario("jose", "12345678","jose@gmail.com");


        l.add(pepe);
        l.add(jose);
        l.add(juan);

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
