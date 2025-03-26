package Controller;

import Model.Actividad;
import Model.Creador;
import Model.Iniciativa;
import Exceptions.IniciativaNoValidaException;

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
/*
    // Añadir actividad a una iniciativa existente
    public void agregarActividad(Iniciativa iniciativa, String nombreActividad, String descripcion) {
        Actividad nuevaActividad = new Actividad(nombreActividad, descripcion);
        iniciativa.addActividad(nuevaActividad);
    }
 */
}
