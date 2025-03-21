package View;

import Model.Creador;
import Model.Usuario;
import Model.Voluntario;
import Utils.Utilidades;

public class VoluntariadoView {

    /**
     * Muestra un menu al usuario para que eliga una opcion
     * @return devuelve un entero con la opcion seleccionada
     */
    public int mostrarMenuVoluntario() {
        int opcion;
        do {
            opcion = Utilidades.pideEntero("Seleccione una de las siguientes opciones");
            mostrarMensaje("1. Ver perfil");
            mostrarMensaje("2. Ver puntos");
            mostrarMensaje("3. Mostrar iniciativas en las que participas");
            mostrarMensaje("4. Mostrar actividades en que realice");
            mostrarMensaje("5. Iniciativas disponibles");
            mostrarMensaje("6. Cambiar estado de actividad");
            mostrarMensaje("7. Hacer comentario de una actividad");
            mostrarMensaje("8. Salir");
        }while (opcion>=1 && opcion<=8);
        return opcion;
    }

    // Creamos metodo para mostrar mensaje del menu
    public static void mostrarMensaje(String msg){
        System.out.println(msg);
    }

    // Aplicamos un metodo para crear un usuario
    public static Usuario crearUsuario(int tipoUsuario) {
        String nombre = Utilidades.pideString("Introduce el nombre de usuario");
        String password = crearPassword();
        String correo = crearCorreo();
        Usuario nuevoUsuario = null;

        switch (tipoUsuario){
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

    public static String crearPassword() { //aquí podemos añadir más validaciones a la password.
        String password;
        boolean esValido = false;

        do {
            password = Utilidades.pideString("Introduce la contraseña");
            if (password.length() < 8) {
                System.out.println("La contraseña debe tener al menos 8 caracteres");
            }else {
                System.out.println("Contraseña creada");
                esValido = true;
            }
        }while (!esValido);

        return password;
    }

    public static String crearCorreo() { //metodo para pedir y validar correo
        String correo = "";

        return correo;

    }

    public static int elegirTipoUsuario() {
        int opcion;
        do {
            System.out.println("\n👤 ¿Qué tipo de usuario deseas registrar?");
            System.out.println("1. Creador");
            System.out.println("2. Voluntario");
            opcion = Utilidades.pideEntero("Elige una opción");

        }while (opcion > 0 && opcion < 3);

        return opcion;

    }
}
