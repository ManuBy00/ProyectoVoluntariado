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

    // Menú de gestión de iniciativas
    public static int mostrarMenuIniciativa() {
        System.out.println("\n1. Crear actividad");
        System.out.println("2. Añadir actividad");
        System.out.println("3. Ver mis iniciativas");
        System.out.println("4. Volver");
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