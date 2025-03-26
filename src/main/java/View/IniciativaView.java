package View;

import Model.Iniciativa;
import Utils.Utilidades;

public class IniciativaView {
    // ESTO VA EN EL TOSTRING DE INICIATIVA
    public static void mostrarIniciativa(Iniciativa iniciativa) {
        System.out.println("\n--- INICIATIVA ---");
        System.out.println("Nombre: " + iniciativa.getNombre());
        System.out.println("Creador: " + iniciativa.getCreador().getNombre());
        System.out.println("ONG: " + iniciativa.getCreador().getOng());
        System.out.println("Actividades: " + iniciativa.getActividades().size());
    }

    // Devuelve array?
    public static String[] pedirDatosIniciativa() {
        String nombre = Utilidades.pideString("Nombre de la iniciativa:");
        String descripcion = Utilidades.pideString("Descripción:");
        return new String[]{nombre, descripcion};
    }

    // Menú de gestión de iniciativas
    public static int mostrarMenuIniciativa() {
        System.out.println("\n1. Crear iniciativa");
        System.out.println("2. Añadir actividad");
        System.out.println("3. Ver mis iniciativas");
        System.out.println("4. Volver");
        return Utilidades.pideEntero("Elige una opción:");
    }
}