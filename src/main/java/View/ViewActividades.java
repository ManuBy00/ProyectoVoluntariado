package View;

import Exceptions.UsuarioNoExiste;
import Model.*;
import Utils.Utilidades;



public class ViewActividades {

    public static Actividad pedirDatosActividad() {
        String nombre = Utilidades.pideString("Nombre de la Actividad:");
        String descripcion = Utilidades.pideString("Descripción:");
        String fechaInicio = Utilidades.pideString("Introduce la fecha de inicio");
        String fechaFin = Utilidades.pideString("Introduce la fecha de fin");
        Voluntario encargado;
        encargado = asignarEncargado();
        String comentario = Utilidades.pideString("Introduce un comentario");

        Actividad actividad = new Actividad(nombre, descripcion, fechaInicio, fechaFin, encargado, comentario);
        return actividad;
    }

    public static Voluntario asignarEncargado()throws UsuarioNoExiste{
        boolean encontrado = false;
        Usuario encargado;
        do {
            UsuariosView.mostrarVoluntariosDisponibles();

            String correoVoluntario = Utilidades.pideString("Introduce el correo de un voluntario para asignarlo como encargado.");
            encargado = ListaUsuarios.getInstance().encontrarElemento(correoVoluntario);
            if (encargado!=null && encargado.getClass().equals(Voluntario.class)){
                encontrado = true;
            }else {
                View.UsuariosView.mostrarMensaje("El usuario seleccionado no es válido.");
            }
        }while (!encontrado);
        return (Voluntario)encargado;
    }

}
