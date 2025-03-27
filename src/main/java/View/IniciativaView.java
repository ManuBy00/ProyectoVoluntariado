package View;

import Model.Creador;
import Model.Iniciativa;
import Model.Usuario;
import Utils.Utilidades;

public class IniciativaView {

    // Devuelve array?
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



}