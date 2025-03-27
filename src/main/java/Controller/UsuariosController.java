package Controller;

import Exceptions.UsuarioNoExiste;
import Exceptions.UsuarioYaExiste;
import Model.ListaUsuarios;
import Model.Usuario;
import Utils.Utilidades;
import View.VoluntariadoView;


/**
 * Controlador para gestionar las operaciones relacionadas con usuarios:
 * registro, inicio de sesión, actualización y eliminación.
 */

public class UsuariosController {
    private ListaUsuarios misUsuarios;

    /**
     * Constructor que recibe una instancia de ListaUsuarios.
     *
     * @param misUsuarios Lista de usuarios (debería ser Singleton según el comentario).
     */


    public UsuariosController(ListaUsuarios misUsuarios) { //hay que hacerlo por defecto y hacer mis usuarios singletone
        this.misUsuarios = misUsuarios;
    }

    /** Comentario de muestraMenuInicial
     * Muestra el menú inicial de la aplicación.
     * - Iniciar sesión
     * - Registrar usuario
     * - Salir
     */


//    public void mostrarMenuInicial() {
//        int opcion;
//        do {
//            VoluntariadoView.mostrarMensaje("*** VOLUNTAPP ***");
//            VoluntariadoView.mostrarMensaje("1. Iniciar sesión");
//            VoluntariadoView.mostrarMensaje("2. Registrar usuario");
//            VoluntariadoView.mostrarMensaje("3. Salir");
//
//            opcion = Utilidades.pideEntero("");
//            if (opcion < 1 || opcion > 3) {
//                VoluntariadoView.mostrarMensaje("Opción no válida. Inténtelo de nuevo.");
//            }
//            switch (opcion) {
//                case 1:
//                    try {
//                        iniciarSesion();
//                    } catch (UsuarioNoExiste e) {
//                        System.out.println(e.getMessage());
//                    }
//                    break;
//                case 2:
//                    registrarUsuario();
//                    break;
//                case 3:
//                    System.exit(0);
//                    break;
//            }
//        } while (opcion < 1 || opcion > 3);
//    }


    // -----------------------------------------------------------------------------------------


    /**
     * Registra un nuevo usuario en el sistema.
     * - Pide tipo de usuario (voluntario/creador) y datos mediante la vista.
     * - Valida si el usuario ya existe (lanza UsuarioYaExiste).
     */


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
//        VoluntariadoView.mostrarMensaje("*** ACTUALIZAR USUARIO ***");
//        String correo = Utilidades.pideString("Correo:");
//        Usuario usuario = misUsuarios.encontrarElemento(correo);
//        if (usuario == null){
//            VoluntariadoView.mostrarMensaje("No se encontró el usuario.");
//            return;
//        }
//        String nombre = Utilidades.pideString("Nombre (actual: " + usuario.getNombre() + "): ");
//        String password = Utilidades.pideString("Contraseña (actual: " + usuario.getPassword() + "): ");
//        String nuevoCorreo = Utilidades.pideString("Correo (actual: " + usuario.getCorreo() + "): ");
//        if (!nombre.isEmpty()) usuario.setNombre(nombre);
//        if (!password.isEmpty()) usuario.setPassword(password);

    }

    public void eliminarUsuario(){
//        VoluntariadoView.mostrarMensaje("*** ELIMINAR USUARIO ***");
//        String correo = Utilidades.pideString("Correo:");
//        Usuario usuario = misUsuarios.encontrarElemento(correo);
//        if (usuario == null){
//            VoluntariadoView.mostrarMensaje("No se encontró el usuario.");
//            return;
//        }
//        misUsuarios.remove(usuario);
//        VoluntariadoView.mostrarMensaje("Usuario eliminado.");

    }


    /**
     * Inicia sesión de un usuario validando credenciales.
     *
     * @return boolean Siempre devuelve false (¿debería devolver true si hay éxito?).
     * @throws UsuarioNoExiste Si las credenciales son incorrectas o el usuario no existe.
     */


    public boolean iniciarSesion() throws UsuarioNoExiste{
        boolean autorizado = false;
        View.VoluntariadoView.mostrarMensaje("*** INICIO DE SESIÓN ***");
        String correo = Utilidades.pideString("Correo:");
        String password = Utilidades.pideString("Contraseña:");
        Usuario usuarioLogeado = misUsuarios.validarUsuario(correo, password);
        if (usuarioLogeado == null){
            throw new UsuarioNoExiste("No existe ningún usuario con estas credenciales.");
        }else{
            Sesion nuevaSesion = Sesion.getSesion(usuarioLogeado);
            nuevaSesion.sesionIniciada();
        }
        return autorizado;
    }
}

