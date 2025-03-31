package View;

import Model.*;
import Utils.Sesion;
import Utils.Utilidades;

import java.util.HashSet;

public class IniciativaView {

    // Devuelve array de iniciativas
    public static Iniciativa pedirDatosIniciativa() {
        String nombre = Utilidades.pideString("Nombre de la iniciativa:");
        String descripcion = Utilidades.pideString("Descripción:");
        Iniciativa iniciativa = new Iniciativa(nombre, descripcion);
        return iniciativa;
    }

    public static int MenuIniciativas() {
        int opcion;

        UsuariosView.mostrarMensaje("\n1. Crear iniciativa");
        UsuariosView.mostrarMensaje("2. Eliminar iniciativa");
        UsuariosView.mostrarMensaje("3. Modificar iniciativa");
        UsuariosView.mostrarMensaje("4. Buscar iniciativa");
        UsuariosView.mostrarMensaje("5. Ver mis iniciativas");
        UsuariosView.mostrarMensaje("6. Volver");
        opcion = Utilidades.pideEntero("Elige una opcion: ");
        return opcion;
    }

    // Menú de gestión de iniciativas
    public static int mostrarMenuActividades() {
        UsuariosView.mostrarMensaje("\n1. Crear actividad");
        UsuariosView.mostrarMensaje("2. Eliminar actividad");
        UsuariosView.mostrarMensaje("3. Modificar actividad");
        UsuariosView.mostrarMensaje("4. Asignar voluntarios a una actividad");
        UsuariosView.mostrarMensaje("5. Mostrar iniciativas y actividades");
        UsuariosView.mostrarMensaje("6. Volver");
        return Utilidades.pideEntero("Elige una opción:");
    }

    public static void imprimirMisIniciativas(){
        HashSet<Iniciativa> misIniciativas = ListaIniciativas.getInstance().obtenerIniciativasPorCreador(Sesion.getInstancia().getUsuarioIniciado().getCorreo());

        UsuariosView.mostrarMensaje("Iniciativas disponibles:\n");
        for (Iniciativa i : misIniciativas){
            View.UsuariosView.mostrarMensaje(i.toString());
            UsuariosView.mostrarMensaje("---------------------");
        }
    }



}