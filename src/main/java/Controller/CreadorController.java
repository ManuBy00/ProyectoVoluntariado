package Controller;

import Model.*;
import Utils.Sesion;
import Utils.Utilidades;
import View.IniciativaView;
import View.UsuariosView;
import View.ViewActividades;

import javax.swing.text.View;
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
                    addIniciativa();
                    break;
                case 3:
                    // Añadir actividades a una iniciativa
                    addActividad();
                    break;
                case 4:


                    break;
                case 5: // Mostrar detalles de una iniciativa
                    mostrarIniciativa();
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
    public void addIniciativa(){
        Iniciativa nuevaIniciativa = IniciativaView.pedirDatosIniciativa();
        if (ListaIniciativas.getInstance().addIniciativa(nuevaIniciativa)){
            UsuariosView.mostrarMensaje("La iniciativa se ha creado correctamente");
        }else{
            UsuariosView.mostrarMensaje("La iniciativa ya existe");
        }
    }

    /**
     * Busca una iniciativa del creador a través del nombre y le añade una actividad
     */
    public void addActividad(){
        String nombreIniciativa = Utilidades.pideString("Introduce el nombre de la iniciativa a la que quieres añadir una actividad");
        Iniciativa iniciativa = creador.encontrarIniciativaPropia(nombreIniciativa); //el metodo encontrarIniciativaPropia busca una iniciativa por nombre en la lista de iniciativas del creador

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
    public void mostrarIniciativa(){

        String nombreIniciativa = Utilidades.pideString("Introduce el nombre de la iniciativa que quieres ver");
        Iniciativa iniciativa = creador.encontrarIniciativaPropia(nombreIniciativa);

        UsuariosView.mostrarMensaje(iniciativa.toString());
    }

    public void asignarVoluntarios(){

        HashSet<Voluntario> listaVoluntarios = ListaUsuarios.ListaVoluntarios();
        UsuariosView.mostrarMensaje("Puedes elegir un voluntario entre los siguientes.");
        for (Voluntario v : listaVoluntarios){
            UsuariosView.mostrarMensaje(v.getNombre() + " , " + v.getCorreo()+ " | ");
        }
        String correo = Utilidades.pideString("Introduce el correo del voluntario que quieres asignar.");
    }
}