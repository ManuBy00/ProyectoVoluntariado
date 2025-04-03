package Controller;

import Exceptions.ActividadNoExiste;
import Exceptions.IniciativaNoExiste;
import Exceptions.IniciativaYaExiste;
import Exceptions.UsuarioNoExiste;
import Model.*;
import Utils.Sesion;
import Utils.Utilidades;
import View.IniciativaView;
import View.UsuariosView;
import View.ViewActividades;

import java.time.LocalDate;
import java.util.HashSet;

/**
 * Controlador para manejar las acciones del creador.
 */
public class CreadorController {
    private Creador creador;

    /**
     * Constructor que inicializa el creador con la sesión actual.
     */
    public CreadorController() {
        this.creador = (Creador) Sesion.getInstancia().getUsuarioIniciado();
    }

    /**
     * Metodo para ejecutar la sesión del creador.
     */
    public void ejecutarSesion() {
        int opcion;
        do {
            // Muestra el menú del creador y obtiene la opción seleccionada
            opcion = UsuariosView.mostrarMenuCreador();
            switch (opcion) {
                case 1:
                    // Ver perfil del creador
                    verPerfil();
                    break;

                case 2:
                    gestionarIniciativas();
                    break;

                case 3:
                    gestionarActividades();
                    break;

                case 4:
                    ajustesUsuario();
                    break;

                case 5:
                    // Cerrar sesión
                    System.out.println("Cerrando sesión...");

                    Sesion.getInstancia().logOut();
                    break;

                default:
                    // Opción no válida
                    System.out.println("Opción no válida. Intente de nuevo.");
                    break;
            }
        } while (opcion != 5);
    }

    public void verPerfil(){
        System.out.print("*** MI PERFIL ***");
        ListaUsuarios.getInstance().mostrar(creador);
    }

    /**
     * Crea una iniciativa y la añade a la lista
     */
    public void addIniciativa() throws IniciativaYaExiste {
        ListaIniciativas lista = ListaIniciativas.getInstance();
        Iniciativa nuevaIniciativa = IniciativaView.pedirDatosIniciativa();
        try {
            lista.add(nuevaIniciativa);
            UsuariosView.mostrarMensaje("La iniciativa se ha creado correctamente");
        }catch (IniciativaYaExiste e){
            UsuariosView.mostrarMensaje(e.getMessage());
        }

    }

    /**
     * Pide los datos para actualizar y llama al metodo update de ListaIniciativas.
     */
    public void updateIniciativa() {
            // Pedir datos al usuario
            String nombreIniciativa = Utilidades.pideString("Introduce el nombre de la iniciativa:");
            Iniciativa iniciativa= ListaIniciativas.getInstance().encontrarIniciativa(nombreIniciativa);

            //Actualizamos los datos llamando al metodo updateIniciativa de la clase ListaIniciativas.
        try {
            ListaIniciativas.getInstance().update(iniciativa);
            UsuariosView.mostrarMensaje("La iniciativa ha sido actualizada correctamente.");
        } catch (IniciativaNoExiste e) {
            UsuariosView.mostrarMensaje(e.getMessage());
        }
    }

    /**
     * Pide el nombre de una iniciativa y la borra.
     * @throws IniciativaNoExiste si no encuentra una iniciativa con el nombre introducido.
     */
    public void removeIniciativa() throws IniciativaNoExiste {
        ListaIniciativas lista = ListaIniciativas.getInstance();
        String nombreIniciativa = Utilidades.pideString("Introduce el nombre de la iniciativa que quieres eliminar");
        Iniciativa iniciativaBorrar = lista.encontrarIniciativa(nombreIniciativa);
        try{
            lista.remove(iniciativaBorrar);
        }catch (IniciativaNoExiste e){
            UsuariosView.mostrarMensaje(e.getMessage());
        }

    }

    public void mostrarIniciativasPropias(){
        ListaIniciativas lista = ListaIniciativas.getInstance();
        HashSet<Iniciativa> misIniciativas = lista.obtenerIniciativasPorCreador(Sesion.getInstancia().getUsuarioIniciado().getCorreo());
        UsuariosView.mostrarMensaje("*** INICIATIVAS DE " + creador.getNombre().toUpperCase() + " ***");
        for (Iniciativa i : misIniciativas){
            ListaIniciativas.getInstance().mostrar(i);
            UsuariosView.mostrarMensaje("-------------------------------");
        }
    }

    /**
     * Busca una iniciativa por su nombre y la añade a la lista
     * @throws IniciativaNoExiste si no encuentra una iniciativa con el nombre introducido
     */
    public void addActividad() throws IniciativaNoExiste {
        IniciativaView.imprimirMisIniciativas();
        String nombreIniciativa = Utilidades.pideString("Introduce el nombre de la iniciativa a la que quieres añadir una actividad");
        Iniciativa iniciativa = creador.encontrarIniciativaPropia(nombreIniciativa);
        if (iniciativa == null){
            throw new IniciativaNoExiste("La iniciativa introducida no existe \n");
        }

        Actividad nuevaActividad = ViewActividades.pedirDatosActividad();
        if (iniciativa.add(nuevaActividad)){
            UsuariosView.mostrarMensaje("La actividad se ha añadido correctamente.");
        }else{
            UsuariosView.mostrarMensaje("Hubo un error al añadir la actividad.");
        }
    }

    public void updateActividad() throws UsuarioNoExiste, ActividadNoExiste, IniciativaNoExiste{
        // Mostramos las iniciativas del creador
        IniciativaView.imprimirMisIniciativas();

        String nombreIniciativa = Utilidades.pideString("Introduce el nombre de la iniciativa:");
        String nombreActividad = Utilidades.pideString("Introduce el nombre de la actividad que quieres actualizar:");

        // Pedimos los nuevos datos
        String nuevaDescripcion = Utilidades.pideString("Introduce la nueva descripción:");
        LocalDate nuevaFechaInicio = Utilidades.pideFecha("Introduce la nueva fecha de inicio (yyyy-MM-dd):");
        LocalDate nuevaFechaFin = Utilidades.pideFecha("Introduce la nueva fecha de fin (yyyy-MM-dd):");
        String correoEncargado = Utilidades.pideString("Introduce el correo del nuevo voluntario encargado:");

        Voluntario nuevoEncargado = ListaUsuarios.getInstance().encontrarVoluntario(correoEncargado);
        if (nuevoEncargado == null) {
           throw new UsuarioNoExiste("El encargado introducido no existe");
        }

        // Llamamos al metodo de ListaIniciativas
        ListaIniciativas.getInstance().updateActividad(nombreIniciativa, nombreActividad, nuevaDescripcion, nuevaFechaInicio, nuevaFechaFin, nuevoEncargado);
        UsuariosView.mostrarMensaje("✅ Actividad actualizada correctamente.");

    }



    /**
     * Pide una iniciativa, muestra sus actividades y borra la actividad introducida por el usuario
     * @throws IniciativaNoExiste si no se encuentra una iniciativa con el nombre introducido
     * @throws ActividadNoExiste si no se encuentra una actividad con el nombre introducido
     * @throws IniciativaNoExiste si no se encuentra una iniciativa con el nombre introducido
     */
    public void removeActividad() throws IniciativaNoExiste, ActividadNoExiste{
        IniciativaView.imprimirMisIniciativas();
        String nombreIniciativa = Utilidades.pideString("Introduce el nombre de la iniciativa de la que quieres eliminar una actividad");
        Iniciativa iniciativa = creador.encontrarIniciativaPropia(nombreIniciativa);

        if (iniciativa == null) {
            throw new IniciativaNoExiste("La iniciativa introducida no existe \n");
        }

        iniciativa.mostrarActividades();
        String nombreActividad = Utilidades.pideString("Introduce el nombre de la actividad que deseas eliminar");

        Actividad actividadAEliminar = iniciativa.encontrarActividad(nombreActividad);

        if (actividadAEliminar == null) {
            UsuariosView.mostrarMensaje("No se encontró la actividad especificada.");
            return;
        }

        if (iniciativa.remove(actividadAEliminar)) {
            UsuariosView.mostrarMensaje("La actividad se ha eliminado correctamente.");
        } else {
            throw new ActividadNoExiste("La actividad introducida no existe.");
        }
    }

    /**
     * Pide el nombre de una iniciativa y la muestra.
     */
    public void mostrarIniciativa()throws IniciativaNoExiste {
        String nombreIniciativa = Utilidades.pideString("Introduce el nombre de la iniciativa que quieres ver");

        Iniciativa iniciativa = creador.encontrarIniciativaPropia(nombreIniciativa);

        if (iniciativa==null){
            throw new IniciativaNoExiste("La iniciativa introducida no existe");
        }

        UsuariosView.mostrarMensaje(iniciativa.toString());
    }

    public void asignarVoluntario()throws IniciativaNoExiste, UsuarioNoExiste, ActividadNoExiste {
        //Pido la iniciativa a la que pertenece la actividad
        IniciativaView.imprimirMisIniciativas();
        String nombreIniciativa = Utilidades.pideString("Introduce el nombre de una iniciativa.");
        Iniciativa iniciativa = creador.encontrarIniciativaPropia(nombreIniciativa);
        if (iniciativa==null){
            throw new IniciativaNoExiste("La iniciativa introducida no existe");
        }

        //pedimos qué actividad quiere asignar
        UsuariosView.mostrarMensaje("Actividades disponibles: " + iniciativa.mostrarActividades());
        String nombreActividad = Utilidades.pideString("Introduce el nombre de la actividad que quieres asignar");
        Actividad actividad = iniciativa.encontrarActividad(nombreActividad);
        if (actividad == null){
            throw new ActividadNoExiste("La actividad introducida no existe");
        }

        //enseñamos los voluntarios disponibles
        if (UsuariosView.mostrarVoluntariosDisponibles(actividad)){
            //pedimos qué usuario quiere asignar
            String correo = Utilidades.pideString("Introduce el correo del voluntario que quieres asignar.");
            Voluntario voluntarioAsignar = ListaUsuarios.getInstance().encontrarVoluntario(correo);
            if (voluntarioAsignar == null){
                throw new UsuarioNoExiste("El voluntario introducido no existe");
            }else{
                UsuariosView.mostrarMensaje("El voluntario " + voluntarioAsignar.getNombre() + " ha sido asignado a " + iniciativa.getNombre());
                actividad.getVoluntariosAsignados().add(voluntarioAsignar); //Añadimos actividad a la lista de actividades de voluntarios
                voluntarioAsignar.getActividadesAsignadas().add(actividad); //Asignamos voluntario a la lista de voluntarios de actividades
            }
        }else {
            UsuariosView.mostrarMensaje("Voliendo al menú...");
        }
    }

    /**
     * Menú y opciones relacionados con las iniciativas
     */
    public void gestionarIniciativas(){
        int opcionIniciativa;
        do {
            opcionIniciativa = IniciativaView.MenuIniciativas();
            System.out.println("Opción seleccionada: " + opcionIniciativa);
            switch (opcionIniciativa) {
                case 1:
                    //crear y añadir iniciativa
                    try {
                        addIniciativa();
                    } catch (IniciativaYaExiste e) {
                        UsuariosView.mostrarMensaje(e.getMessage());
                    }
                    break;
                case 2:
                    //Eliminar iniciativa
                    removeIniciativa();
                    break;

                case 3:
                    //Modificar por implementar
                    updateIniciativa();
                    break;

                case 4:
                    //Ver detalles de una iniciativa buscada por nombre
                    try {
                        mostrarIniciativa();
                    } catch (IniciativaNoExiste e) {
                        UsuariosView.mostrarMensaje(e.getMessage());
                    }
                    break;
                case 5:
                    //Ver todas las iniciativas del usuario con detalle.
                    mostrarIniciativasPropias();
                    break;
                case 6:
                    UsuariosView.mostrarMensaje("Volver al menú principal");
                    break;
            }
        }while (opcionIniciativa != 6);
    }

    /**
     * Menú y switch de las opciones relacionadas con actividades.
     */
    public void gestionarActividades(){
        int opcionActividad;
        do {
            opcionActividad = IniciativaView.mostrarMenuActividades();
            System.out.println("Opción seleccionada: " + opcionActividad);
            switch (opcionActividad) {
                case 1:
                    // Añadir actividades a una iniciativa
                    try {
                        addActividad();
                    }catch (IniciativaNoExiste e){
                        UsuariosView.mostrarMensaje(e.getMessage());
                    }

                    break;
                case 2:
                    //eliminar una actividad
                    try {
                        removeActividad();
                    } catch (IniciativaNoExiste | ActividadNoExiste e) {
                        UsuariosView.mostrarMensaje(e.getMessage());
                    }
                    break;

                case 3:
                    //Modificar por implementar
                    updateActividad();
                    break;

                case 4:
                    //Asignar voluntarios a una actividad
                    try {
                        asignarVoluntario();
                    } catch (IniciativaNoExiste | UsuarioNoExiste | ActividadNoExiste e) {
                        UsuariosView.mostrarMensaje(e.getMessage());
                    }
                    break;
                case 5:
                    //Ver todas las iniciativas del usuario con detalle.
                    mostrarIniciativasPropias();
                    break;
                case 6:
                    UsuariosView.mostrarMensaje("Volver al menú principal");
                    break;
            }
        }while (opcionActividad != 6);
    }

    public void ajustesUsuario(){
        int opcion;
        do {
            opcion=UsuariosView.mostrarMenuAjustesUsuario();
            switch (opcion){
                case 1:
                    ListaUsuarios.getInstance().update(creador);
                    break;
                case 2:
                    ListaUsuarios.getInstance().remove(creador);
                    UsuariosView.mostrarMensaje("Usuario eliminado. Saliendo de la aplicación...");
                    break;
                case 3:
                    break;
            }
        }while (opcion !=3);
    }
}