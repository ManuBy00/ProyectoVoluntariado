package Controller;

import Exceptions.UsuarioNoExiste;
import Exceptions.UsuarioYaExiste;
import Model.Creador;
import Model.ListaUsuarios;
import Model.Usuario;
import Utils.Sesion;
import Utils.Utilidades;
import View.UsuariosView;


public class UsuariosController {
    private ListaUsuarios misUsuarios;

    public UsuariosController(ListaUsuarios misUsuarios) { //hay que hacerlo por defecto y hacer mis usuarios singletone
        this.misUsuarios = misUsuarios;
    }

    public void registrarUsuario() {
        UsuariosView.mostrarMensaje("*** REGISTRO ***");
        //pedimos tipo de usuario
        int tipoUsuario = UsuariosView.elegirTipoUsuario();
        //creamos el usuario
        Usuario nuevo = UsuariosView.crearUsuario(tipoUsuario);

        try {
            misUsuarios.add(nuevo);
            UsuariosView.mostrarMensaje("Usuario registrado.");
        } catch (UsuarioYaExiste e) {
            System.out.println(e.getMessage());
        }
    }

    public void updateUsuario(){

    }

    public void eliminarUsuario(){

    }


    public void iniciarSesion() throws UsuarioNoExiste{

        UsuariosView.mostrarMensaje("*** INICIO DE SESIÓN ***");
        String correo = Utilidades.pideString("Correo:");
        String password = Utilidades.pideString("Contraseña:");

        Usuario usuarioLogeado = misUsuarios.validarLogin(correo, password);

        if (usuarioLogeado == null){
            throw new UsuarioNoExiste("No existe ningún usuario con estas credenciales.");
        }else{
            Sesion.getInstancia().logIn(usuarioLogeado);  // Guarda el usuario en la sesión

            if (usuarioLogeado.getClass()== Creador.class){
                CreadorController creadorController = new CreadorController();
                creadorController.ejecutarSesion();
            }else{
               VoluntarioController voluntarioController = new VoluntarioController();
               voluntarioController.ejecutarSesion();
            }
        }
    }
}

