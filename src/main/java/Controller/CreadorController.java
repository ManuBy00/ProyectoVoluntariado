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
                    System.out.print("*** MI PERFIL ***");
                    UsuariosView.mostrarMensaje(creador.toString());
                    break;

                    case 2:
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
                                    try {
                                        removeIniciativa();
                                    } catch (IniciativaNoExiste e) {
                                        View.UsuariosView.mostrarMensaje(e.getMessage());
                                    }
                                    break;

                                case 3:
                                    //Modificar por implementar
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
                        break;
                case 3:
                    int opcionActividad;
                    do {
                        opcionActividad = IniciativaView.mostrarMenuActividades();
                        System.out.println("Opción seleccionada: " + opcionActividad);
                        switch (opcionActividad) {
                            case 1:
                                // Añadir actividades a una iniciativa
                                try {
                                    addActividad();
                                } catch (IniciativaNoExiste e) {
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
                    break;
                case 4:
                    // Cerrar sesión
                    System.out.println("Cerrando sesión...");
                    Sesion.getInstancia().logOut();
                    break;
                default:
                    // Opción no válida
                    System.out.println("Opción no válida. Intente de nuevo.");
                    break;
            }
        } while (opcion != 4);
    }

    /**
     * Crea una iniciativa y la añade a la lista
     * @throws IniciativaYaExiste si existe en la lista una iniciativa con el mismo nombre
     */
    public void addIniciativa() throws IniciativaYaExiste {
        ListaIniciativas lista = ListaIniciativas.getInstance();
        Iniciativa nuevaIniciativa = IniciativaView.pedirDatosIniciativa();
        if (lista.addIniciativa(nuevaIniciativa)){
            UsuariosView.mostrarMensaje("La iniciativa se ha creado correctamente");
        }else{
            throw new IniciativaYaExiste("El nombre introducido ya existe.");
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
        if (lista.removeIniciativa(iniciativaBorrar)){
            UsuariosView.mostrarMensaje("Iniciativa eliminada.");
        }else {
            throw new IniciativaNoExiste("La iniciativa introducida no existe");
        }
    }

    public void mostrarIniciativasPropias(){
        ListaIniciativas lista = ListaIniciativas.getInstance();
        HashSet<Iniciativa> misIniciativas = lista.obtenerIniciativasPorCreador(Sesion.getInstancia().getUsuarioIniciado().getCorreo());
        UsuariosView.mostrarMensaje("*** INICIATIVAS DE " + creador.getNombre().toUpperCase() + " ***");
        for (Iniciativa i : misIniciativas){
            UsuariosView.mostrarMensaje(i.toString());
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
        Iniciativa iniciativa = creador.encontrarIniciativaPropia(nombreIniciativa); //el metodo encontrarIniciativaPropia busca una iniciativa por nombre en la lista de iniciativas del creador
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


    /**
     * Pide una iniciativa, muestra sus actividades y borra la actividad introducida por el usuario
     * @throws IniciativaNoExiste si no se encuentra una iniciativa con el nombre introducido
     * @throws ActividadNoExiste si no se encuntra una actividad con el nombre introducido
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
        UsuariosView.mostrarVoluntariosDisponibles();
        //pedimos qué usuario quiere asignar
        String correo = Utilidades.pideString("Introduce el correo del voluntario que quieres asignar.");
        Voluntario voluntarioAsignar = ListaUsuarios.getInstance().encontrarVoluntario(correo);

        if (voluntarioAsignar == null){
            throw new UsuarioNoExiste("El voluntario introducido no existe");
        }

        actividad.getVoluntariosAsignados().add(voluntarioAsignar); //Añadimos actividad a la lista de actividades de voluntarios

        voluntarioAsignar.getActividadesAsignadas().add(actividad); //Asignamos voluntario a la lista de voluntarios de actividades

    }
}