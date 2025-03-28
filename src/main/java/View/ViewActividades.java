package View;

import Model.*;
import Utils.Utilidades;

import java.util.HashSet;

public class ViewActividades {

    public static Actividad pedirDatosActividad() {
        String nombre = Utilidades.pideString("Nombre de la Actividad:");
        String descripcion = Utilidades.pideString("Descripción:");
        String fechaInicio = Utilidades.pideString("Introduce la fecha de inicio");
        String fechaFin = Utilidades.pideString("Introduce la fecha de fin");
        Voluntario encargado = asignarEncargado();
        String comentario = Utilidades.pideString("Introduce un comentario");


        Actividad actividad = new Actividad(nombre, descripcion, fechaInicio, fechaFin, encargado, comentario);
        return actividad;
    }

    public static Voluntario asignarEncargado(){
        boolean encontrado = false;
        Usuario encargado;
        do {
            UsuariosView.mostrarMensaje("Voluntarios disponibles:\n:");
            UsuariosView.mostrarVoluntariosDisponibles();

            String correoVoluntario = Utilidades.pideString("Introduce el correo del voluntario.");
            encargado = ListaUsuarios.getInstance().encontrarElemento(correoVoluntario);
            encontrado = false;
            if (encargado.getClass().equals(Voluntario.class)){
                encontrado = true;
            }else {
                View.UsuariosView.mostrarMensaje("El usuario seleccionado no es un voluntario.");
            }
        }while (!encontrado);
        return (Voluntario)encargado;
    }

}
