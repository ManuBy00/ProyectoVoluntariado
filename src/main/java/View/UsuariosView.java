package View;

import Model.Creador;
import Model.ListaUsuarios;
import Model.Usuario;
import Model.Voluntario;
import Utils.Utilidades;

import java.util.HashSet;

public class UsuariosView {

    /**
     * Muestra un menu inicial al usuario para que eliga una opcion
     *
     * @return devuelve un entero con la opcion seleccionada
     */
    public static int mostrarMenuInicial() {
        int opcion;
        do {
            mostrarMensaje("*** VOLUNTAPP ***");
            mostrarMensaje("1. Iniciar sesión como creador");
            mostrarMensaje("2. Iniciar sesión como voluntario");
            mostrarMensaje("3. Registrar usuario");
            mostrarMensaje("4. Salir");

            opcion = Utilidades.pideEntero("");
            if (opcion < 1 || opcion > 4) {
                mostrarMensaje("Opción no válida. Inténtelo de nuevo.");
            }
        } while (opcion < 1 || opcion > 4);
        return opcion;
    }

    /**
     * Muestra un menu al Voluntaario para que eliga una opcion
     *
     * @return devuelve un entero con la opcion seleccionada
     */
    public static int mostrarMenuVoluntario() {
        int opcion;
        do {
            mostrarMensaje("1. Ver perfil");
            mostrarMensaje("2. Ver mis actividades");
            mostrarMensaje("3. Cambiar estado actividad");
            mostrarMensaje("4. Actualizar POR IMPLEMENTAR");
            mostrarMensaje("5. Comentar actividad");
            mostrarMensaje("6. Salir");
            opcion = Utilidades.pideEntero("Seleccione una de las siguientes opciones");
        } while (opcion < 1 || opcion > 6);
        return opcion;
    }

    /**
     * Muestra un menu al usuario para que eliga una opcion EXCLUSIVO DE CREADOR
     *
     * @return devuelve un entero con la opcion seleccionada
     */
    public static int mostrarMenuCreador() {
        int opcion;
        do {
            mostrarMensaje("1. Ver perfil");
            mostrarMensaje("2. Gestión de iniciativas");
            mostrarMensaje("3. Gestión de actividades");
            mostrarMensaje("4. Salir");

            opcion = Utilidades.pideEntero("Seleccione una de las siguientes opciones");
        } while (opcion < 1 || opcion > 4);
        return opcion;
    }

    /**
     * Metodo para mostrar un mensaje por pantalla
     *
     * @param msg el mensaje a mostrar
     */
    public static void mostrarMensaje(String msg) {
        System.out.println(msg);
    }

    /**
     * Metodo para crear un nuevo usuario
     *
     * @param tipoUsuario el tipo de usuario a crear
     * @return devuelve un objeto de tipo Usuario
     */
    public static Usuario crearUsuario(int tipoUsuario) {
        String nombre = Utilidades.pideString("Introduce el nombre de usuario");
        String password = pidePassword();
        String correo = pideCorreo();

        Usuario nuevoUsuario = null;

        switch (tipoUsuario) {
            case 1:
                String ong = Utilidades.pideString("Introduce la ONG a la que perteneces.");
                nuevoUsuario = new Creador(nombre, password, correo, ong);
                break;
            case 2:
                nuevoUsuario = new Voluntario(nombre, password, correo);
                break;
        }

        return nuevoUsuario;
    }

    /**
     * Metodo para pedir una contraseña al usuario
     *
     * @return devuelve un String con la contraseña introducida por el usuario
     */
    public static String pidePassword() {
        String password;
        boolean esValido = false;

        do {
            password = Utilidades.pideString("Introduce la contraseña");
            if (password.length() < 8) {
                System.out.println("La contraseña debe tener al menos 8 caracteres");
            } else {
                esValido = true;
            }
        } while (!esValido);

        return password;
    }

    /**
     * Metodo para pedir un correo al usuario
     *
     * @return devuelve un String con el correo introducido por el usuario
     */
    public static String pideCorreo() {
        boolean esValido = false;
        String correo;
        do {
            correo = Utilidades.pideString("Introduce el correo");
            if (!Utilidades.validarCorreo(correo)) {
                mostrarMensaje("Formato de correo no válido.");
            } else {
                esValido = true;
            }
        } while (!esValido);
        return correo;
    }

    /**
     * Metodo para elegir el tipo de usuario puede ser creador o voluntario
     *
     * @return devuelve un entero con la opción seleccionada
     */
    public static int elegirTipoUsuario() {
        int opcion;
        do {
            System.out.println("\n¿Qué tipo de usuario deseas registrar?");
            System.out.println("1. Creador");
            System.out.println("2. Voluntario");
            opcion = Utilidades.pideEntero("Elige una opción");

        } while (opcion < 1 || opcion > 2);

        return opcion;
    }

    public static void mostrarVoluntariosDisponibles(){
        HashSet<Voluntario> listaVoluntarios = ListaUsuarios.getInstance().getVoluntarios();
        UsuariosView.mostrarMensaje("Voluntarios disponibles:\n");
        for (Voluntario v : listaVoluntarios){
            UsuariosView.mostrarMensaje(v.getNombre() + " , " + v.getCorreo()+ " | ");
        }
    }

    /**
     * Actualiza un usuario existente.
     *
     * @param usuario el usuario a actualizar
     */
    public static void actualizarUsuario(Usuario usuario) {
        usuario.setNombre(Utilidades.pideString("Nuevo nombre:"));
        usuario.setCorreo(Utilidades.pideString("Nuevo correo:"));
        usuario.setPassword(pidePassword());
    }
}