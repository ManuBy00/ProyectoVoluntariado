package Controller;

import Exceptions.UsuarioNoExiste;
import Exceptions.UsuarioYaExiste;
import Model.ListaUsuarios;
import Model.Usuario;
import Utils.Utilidades;
import View.VoluntariadoView;




public class UsuariosController {
    private ListaUsuarios misUsuarios;

    public UsuariosController(ListaUsuarios misUsuarios) { //hay que hacerlo por defecto y hacer mis usuarios singletone
        this.misUsuarios = misUsuarios;
    }

    public void registrarUsuario() {
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


    public boolean iniciarSesion() throws UsuarioNoExiste{
        boolean autorizado = false;
        String correo = Utilidades.pideString("Introduce tu correo");
        String password = Utilidades.pideString("Intoduce tu contraseña");
        Usuario usuarioLogeado = misUsuarios.validarUsuario(correo, password);
        if (usuarioLogeado == null){
            throw new UsuarioNoExiste("No existe ningún usuario con estas credenciales.");
        }else{
            Sesion nuevaSesion = new Sesion(usuarioLogeado);
            nuevaSesion.sesionIniciada();
        }



        return autorizado;
    }
}

