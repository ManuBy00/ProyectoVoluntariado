package Controller;

import Exceptions.UsuarioNoExiste;
import Exceptions.UsuarioYaExiste;
import Model.Creador;
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
            if (usuarioLogeado.getClass()== Creador.class){
                ejecutarSesionCreador(usuarioLogeado);
            }else{
               ejecutarSesionVoluntario(usuarioLogeado);
            }
        }
        return autorizado;
    }

    public void ejecutarSesionCreador(Usuario usuarioLogeado){
        int opcion;
        do {
            opcion = VoluntariadoView.mostrarMenuCreador();
            switch (opcion) {
                case 1:
                    System.out.println("Ver perfil");
                    System.out.println(usuarioLogeado.toString());

                    break;
                case 2:
                    System.out.println("Crear iniciativa");
                    break;
                case 3:
                    System.out.println("Asignar voluntarios a actividades");
                    break;
                case 4:
                    System.out.println("Iniciativas disponibles");
                    break;

                case 5:
                    System.out.println("Cerrando sesión...");
                    break;
            }

        } while (opcion != 5);
    }

    public void ejecutarSesionVoluntario(Usuario usuarioLogeado) {
        int opcion;
        do {
            opcion = VoluntariadoView.mostrarMenuVoluntario();
            switch (opcion) {
                case 1:
                    System.out.println("Ver perfil");
                    System.out.println(usuarioLogeado.toString());
                    break;
                case 2:
                    System.out.println("Ver mis actividades");
                    break;
                case 3:
                    System.out.println("Cambiar estado de actividad");
                    break;
                case 4:
                    System.out.println("Iniciativas disponibles");
                    break;
                case 5:
                    System.out.println("Cambiar estado de actividad");
                    break;
                case 6:
                    System.out.println("Hacer comentario de una actividad");
                    break;
                case 7:
                    System.out.println("me saltao alguna");
                    break;
                case 8:
                    System.out.println("Cerrando sesión...");
                    break;
            }
        } while (opcion != 8);
    }
}

