package Controller;

import DataAccess.XML;
import Exceptions.UsuarioNoExiste;
import Exceptions.UsuarioYaExiste;
import Model.Creador;
import Model.ListaUsuarios;
import Model.Usuario;
import Utils.Sesion;
import Utils.Utilidades;
import View.UsuariosView;

/**
 * Controlador para manejar las acciones relacionadas con los usuarios.
 */
public class UsuariosController {
    private ListaUsuarios misUsuarios;

    /**
     * Constructor que inicializa el controlador con la lista de usuarios.
     * @param misUsuarios la lista de usuarios.
     */
    public UsuariosController(ListaUsuarios misUsuarios) {
        this.misUsuarios = ListaUsuarios.getInstance();
    }

    /**
     * Metodo para registrar un nuevo usuario.
     */
    public void registrarUsuario() {
        UsuariosView.mostrarMensaje("*** REGISTRO ***");
        // Pedimos tipo de usuario
        int tipoUsuario = UsuariosView.elegirTipoUsuario();
        // Creamos el usuario
        Usuario nuevo = UsuariosView.crearUsuario(tipoUsuario);


        try {
            misUsuarios.add(nuevo);
            UsuariosView.mostrarMensaje("Usuario registrado.");
        } catch (UsuarioYaExiste e) {
            System.out.println(e.getMessage());
        }

        //XML.writeXML(ListaUsuarios.getInstance(), "usuarios.xml");
    }


    /**
     * Metodo para iniciar sesión de un usuario.
     * @throws UsuarioNoExiste si no existe un usuario con las credenciales proporcionadas.
     */
    public void iniciarSesionCreador() throws UsuarioNoExiste {
        UsuariosView.mostrarMensaje("*** INICIO DE SESIÓN ***");
        String correo = Utilidades.pideString("Correo:");
        String password = Utilidades.pideString("Contraseña:");

        Usuario usuarioLogeado = misUsuarios.validarLoginCreador(correo, password);

        if (usuarioLogeado == null) {
            throw new UsuarioNoExiste("No existe ningún creador con estas credenciales.");
        } else {
            Sesion.getInstancia().logIn(usuarioLogeado);  // Guarda el usuario en la sesión

            CreadorController creadorController = new CreadorController();
            creadorController.ejecutarSesion();
        }
    }

    public void iniciarSesionVoluntario() throws UsuarioNoExiste {
        UsuariosView.mostrarMensaje("*** INICIO DE SESIÓN ***");
        String correo = Utilidades.pideString("Correo:");
        String password = Utilidades.pideString("Contraseña:");

        Usuario usuarioLogeado = misUsuarios.validarLoginVoluntario(correo, password);

        if (usuarioLogeado == null) {
            throw new UsuarioNoExiste("No existe ningún usuario con estas credenciales.");
        } else {
            Sesion.getInstancia().logIn(usuarioLogeado);  // Guarda el usuario en la sesión

            if (usuarioLogeado.getClass() == Creador.class) {
                CreadorController creadorController = new CreadorController();
                creadorController.ejecutarSesion();
            } else {
                VoluntarioController voluntarioController = new VoluntarioController();
                voluntarioController.ejecutarSesion();
            }
        }
    }
}