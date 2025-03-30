package Controller;
import Model.Actividad;
import Model.Voluntario;
import Utils.Sesion;
import Utils.Utilidades;
import View.UsuariosView;


public class VoluntarioController {
    private Voluntario voluntario;

    /**
     * Constructor: recibe un voluntario y lo guarda.
     */
    public VoluntarioController() {
        this.voluntario = (Voluntario) Sesion.getInstancia().getUsuarioIniciado();
    }

    /**
     * Muestra el menú y ejecuta la opción elegida por el voluntario.
     */
    public void ejecutarSesion() {
        int opcion;
        do {
            opcion = UsuariosView.mostrarMenuVoluntario(); // Muestra el menú y obtiene la opción
            switch (opcion) {
                case 1: verPerfil(); break;
                case 2: verMisActividades(); break;
                case 3: cambiarEstadoActividad(); break;
                // AQUI FALTA IMPLEMENTAR EL METODO ACTUALIZAR ACTIVIDAD
                case 4: System.out.println("ACTUALIZAR"); break;
                case 5: comentarActividad(); break;
                case 6: System.out.println("Cerrando sesión..."); break;
                default: System.out.println("Opción no válida.");
            }
        } while (opcion != 6);
    }

    /**
     * Muestra la información del voluntario.
     */
    private void verPerfil() {
        System.out.println("\n--- Mi Perfil ---");
        System.out.println(voluntario);
    }

    /**
     * Lista las actividades asignadas al voluntario.
     */
        private void verMisActividades() {
        System.out.println("\n--- Mis Actividades ---");
        for (Actividad actividad : voluntario.getActividadesAsignadas()) {
            int i = 1;
            System.out.println(i + "." + actividad.toString());

        }
    }


    /**
     * Permite cambiar el estado de una actividad asignada al voluntario.
     */

    public void cambiarEstadoActividad() {
        // Mostrar todas las actividades del voluntario
        System.out.println("\n--- Actividades Asignadas ---");
        int index = 1;
        for (Actividad actividad : voluntario.getActividadesAsignadas()) {
            System.out.println(index + ". " + actividad); // Muestra actividad con estado actual
            index++;
        }

        // Pedir al usuario que elija una actividad
        int actividadElegida = Utilidades.pideEntero("Elige el número de la actividad para cambiar el estado:");

        // Verificar si la elección es válida
        if (actividadElegida < 1 || actividadElegida > voluntario.getActividadesAsignadas().size()) {
            System.out.println("Opción inválida.");
            return;
        }

        // Obtener la actividad seleccionada
        Actividad actividad = voluntario.getActividadesAsignadas().get(actividadElegida - 1);

        // Mostrar las opciones de estado disponibles
        System.out.println("\n--- Cambiar Estado de la Actividad ---");
        System.out.println("1. Pendiente");
        System.out.println("2. En Progreso");
        System.out.println("3. Finalizada");
        System.out.println("4. Cancelada");

        // Pedir al usuario que elija el nuevo estado
        int nuevoEstado = Utilidades.pideEntero("Elige el número del nuevo estado:");

        }

    /**
     * Muestra todas las actividades asignadas al voluntario.
     * Este método recorre la lista de actividades del voluntario y las imprime con su índice y estado actual.
     */
    private void comentarActividad() {
        // Mostrar un encabezado para las actividades
        System.out.println("\n--- Actividades Asignadas ---");

        // Inicializar el contador de índice para enumerar las actividades
        int index = 1;

        // Recorrer todas las actividades asignadas al voluntario
        for (Actividad actividad : voluntario.getActividadesAsignadas()) {
            // Mostrar la actividad con su índice y estado actual
            System.out.println(index + ". " + actividad); // La clase Actividad debe tener un método toString() adecuado
            index++; // Incrementar el índice para la siguiente actividad
        }
    }


}
