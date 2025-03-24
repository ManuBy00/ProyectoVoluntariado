package Controller;

import Exceptions.UsuarioNoExiste;
import Exceptions.UsuarioYaExiste;
import Model.Creador;
import Model.ListaUsuarios;
import Model.Sesion;
import Model.Usuario;
import Utils.Utilidades;
import View.VoluntariadoView;


public class UsuariosController {
    private ListaUsuarios misUsuarios;

    public UsuariosController(ListaUsuarios misUsuarios) { //hay que hacerlo por defecto y hacer mis usuarios singletone
        this.misUsuarios = misUsuarios;
    }

    public void registrarUsuario() {
        VoluntariadoView.mostrarMensaje("*** REGISTRO ***");
        //pedimos tipo de usuario
        int tipoUsuario = View.VoluntariadoView.elegirTipoUsuario();
        //creamos el usuario
        Usuario nuevo = View.VoluntariadoView.crearUsuario(tipoUsuario);

        try {
            misUsuarios.add(nuevo);
            VoluntariadoView.mostrarMensaje("Usuario registrado.");
        } catch (UsuarioYaExiste e) {
            System.out.println(e.getMessage());
        }
    }

    public void updateUsuario(){

    }

    public void eliminarUsuario(){

    }


    public boolean iniciarSesion() throws UsuarioNoExiste{
        boolean autorizado = false;

        View.VoluntariadoView.mostrarMensaje("*** INICIO DE SESIÓN ***");
        String correo = Utilidades.pideString("Correo:");
        String password = Utilidades.pideString("Contraseña:");

        Usuario usuarioLogeado = misUsuarios.validarLogin(correo, password);

        if (usuarioLogeado == null){
            throw new UsuarioNoExiste("No existe ningún usuario con estas credenciales.");
        }else{
            Sesion nuevaSesion = Sesion.getSesion(usuarioLogeado);
            if (usuarioLogeado.getClass()== Creador.class){
                nuevaSesion.sesionCreador();
            }else{
                nuevaSesion.sesionVoluntario();
            }

        }
        return autorizado;
    }
}

