package Controller;

import Model.Actividad;
import Model.Creador;
import Model.Iniciativa;
import Exceptions.IniciativaNoValidaException;
import Utils.Utilidades;

import static View.IniciativaView.mostrarMenuIniciativa;
import static View.IniciativaView.pedirDatosIniciativa;

public class IniciativaController {
    // Metodo para crear una iniciativa (solo si el usuario es Creador/ONG)
    public Iniciativa crearIniciativa(String nombre, String descripcion, Creador creador)
            throws IniciativaNoValidaException {
        // Validación 1: El creador debe pertenecer a una ONG
        if (creador.getOng() == null || creador.getOng().isEmpty()) {
            throw new IniciativaNoValidaException("El creador no está asociado a una ONG.");
        }

        // Validación 2: Nombre no vacío
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IniciativaNoValidaException("El nombre es obligatorio.");
        }

        return new Iniciativa(nombre, descripcion, creador);
    }

    // Añadir actividad a una iniciativa existente
    public void agregarActividad(Iniciativa iniciativa, String nombreActividad, String descripcion) {
        Actividad nuevaActividad = new Actividad(nombreActividad, descripcion);
        iniciativa.addActividad(nuevaActividad);
    }

    public void actualizarActividad(Actividad actividad, Iniciativa iniciativa) {
        iniciativa.update(actividad);
    }

    public void eliminarActividad(Actividad actividad, Iniciativa iniciativa) {
        iniciativa.remove(actividad);
    }

    public void mostrarIniciativa(Actividad actividad, Iniciativa iniciativa) {
        iniciativa.mostrar(actividad);
    }

    public void encontrarActividad(Actividad actividad, Iniciativa iniciativa) {
        iniciativa.encontrarElemento(actividad);
    }

    public void menuIniciativa(){
        int opcion = mostrarMenuIniciativa();
        Iniciativa iniciativa = pedirDatosIniciativa();
        do {
            switch (opcion) {
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
            }
        }while (opcion!=4);
    }

}
