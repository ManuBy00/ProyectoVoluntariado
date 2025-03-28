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
                    UsuariosView.mostrarMensaje(creador.toString());
                    break;
                case 2:
                    // Crear una nueva iniciativa
                    try {
                        addIniciativa();
                    }catch (IniciativaYaExiste e) {
                        UsuariosView.mostrarMensaje(e.getMessage());
                    }

                    break;
                case 3:
                    // Añadir actividades a una iniciativa
                    try {
                        addActividad();
                    }catch (IniciativaNoExiste e) {
                        UsuariosView.mostrarMensaje(e.getMessage());
                    }

                    break;
                case 4:
                        //AsignarVoluntarios a una actividad
                        try {
                            asignarVoluntario();
                        } catch (IniciativaYaExiste | UsuarioNoExiste | ActividadNoExiste e) {
                            UsuariosView.mostrarMensaje(e.getMessage());
                        }


                    break;
                case 5: // Mostrar detalles de una iniciativa
                    try {
                        mostrarIniciativa();
                    }catch (IniciativaNoExiste e) {
                        UsuariosView.mostrarMensaje(e.getMessage());
                    }

                    break;
                case 6:
                    // Cerrar sesión
                    System.out.println("Cerrando sesión...");
                    Sesion.getInstancia().logOut();
                    break;
                default:
                    // Opción no válida
                    System.out.println("Opción no válida. Intente de nuevo.");
                    break;
            }
        } while (opcion != 6);
    }

    /**
     * Crea una iniciativa y al añade a la lista.
     */
    public void addIniciativa() throws IniciativaYaExiste {
        Iniciativa nuevaIniciativa = IniciativaView.pedirDatosIniciativa();
        if (ListaIniciativas.getInstance().addIniciativa(nuevaIniciativa)){
            UsuariosView.mostrarMensaje("La iniciativa se ha creado correctamente");
        }else{
            throw new IniciativaYaExiste("El nombre introducido ya existe.");
        }
    }

    /**
     * Busca una iniciativa del creador a través del nombre y le añade una actividad
     */
    public void addActividad() throws IniciativaNoExiste {
        String nombreIniciativa = Utilidades.pideString("Introduce el nombre de la iniciativa a la que quieres añadir una actividad");
        Iniciativa iniciativa = creador.encontrarIniciativaPropia(nombreIniciativa); //el metodo encontrarIniciativaPropia busca una iniciativa por nombre en la lista de iniciativas del creador
        if (iniciativa == null){
            throw new IniciativaNoExiste("La iniciativa introducida no existe");
        }

        Actividad nuevaActividad = ViewActividades.pedirDatosActividad();
        if (iniciativa.add(nuevaActividad)){
            UsuariosView.mostrarMensaje("La actividad se ha añadido correctamente.");
        }else{
            UsuariosView.mostrarMensaje("Hubo un error al añadir la actividad.");
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
        String nombreIniciativa = Utilidades.pideString("Introduce el nombre de la iniciativa que qieres asignar");
        Iniciativa iniciativa = creador.encontrarIniciativaPropia(nombreIniciativa);
        if (iniciativa==null){
            throw new IniciativaNoExiste("La iniciativa introducida no existe");
        }

        //enseñamos los voluntarios disponibles
        HashSet<Voluntario> listaVoluntarios = ListaUsuarios.ListaVoluntarios();
        UsuariosView.mostrarMensaje("Voluntarios disponibles:\n");
        UsuariosView.mostrarVoluntariosDisponibles();

        //pedimos qué usuario quiere asignar
        String correo = Utilidades.pideString("Introduce el correo del voluntario que quieres asignar.");
        Voluntario voluntarioAsignar = (Voluntario) ListaUsuarios.getInstance().encontrarElemento(correo);
        if (voluntarioAsignar == null){
            throw new UsuarioNoExiste("El voluntario introducido no existe");
        }

        //pedimos qué actividad quiere asignar
        String nombreActividad = Utilidades.pideString("Introduce el nombre de la actividad que quieres asignar");
        Actividad actividad = iniciativa.encontrarElemento(nombreActividad);
        if (actividad == null){
            throw new ActividadNoExiste("La actividad introducida no existe");
        }

        actividad.getVoluntariosAsignados().add(voluntarioAsignar); //Añadimos actividad a la lista de actividades de voluntarios

        voluntarioAsignar.getActividadesAsignadas().add(actividad); //Asignamos voluntario a la lista de voluntarios de actividades

    }
}