import Controller.UsuariosController;
import Exceptions.UsuarioNoExiste;
import Model.Creador;
import Model.ListaUsuarios;
import View.VoluntariadoView;

public class Test {
    public static void main(String[] args) {

        ListaUsuarios l = new ListaUsuarios();

        UsuariosController lController = new UsuariosController(l);

        lController.registrarUsuario();

        try {
            lController.iniciarSesion();
        } catch (UsuarioNoExiste e){
            VoluntariadoView.mostrarMensaje(e.getMessage());
        }



    }
}
