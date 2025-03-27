package Model;

import java.io.Serializable;
import java.util.Objects;

public class Actividad {

    private String nombre;
    private String descripcion;
    private String fechaInicio;
    private String fechaFin;
    private String voluntarioEncargado;
    private EstadoActividad estado;
    private String comentario;

    public Actividad(String nombre, String descripcion, String fechaInicio, String fechaFin, String voluntarioEncargado, EstadoActividad estado, String comentario) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.voluntarioEncargado = voluntarioEncargado;
        this.estado = estado;
        this.comentario = comentario;
    }

    public Actividad(String nombreActividad, String descripcion) {
        this.nombre = nombreActividad;
        this.descripcion = descripcion;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public String getFechaFin() {
        return fechaFin;
    }

    public String getVoluntarioEncargado() {
        return voluntarioEncargado;
    }

    public EstadoActividad getEstado() {
        return estado;
    }

    public String getComentario() {
        return comentario;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Actividad actividad = (Actividad) o;
        return Objects.equals(nombre, actividad.nombre) && Objects.equals(descripcion, actividad.descripcion) && Objects.equals(fechaInicio, actividad.fechaInicio) && Objects.equals(fechaFin, actividad.fechaFin) && Objects.equals(voluntarioEncargado, actividad.voluntarioEncargado) && estado == actividad.estado && Objects.equals(comentario, actividad.comentario);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre, descripcion, fechaInicio, fechaFin, voluntarioEncargado, estado, comentario);
    }

    @Override
    public String toString() {
        return "Actividad: \n" +
                "nombre: " + nombre + " | " +
                "descripcion: " + descripcion + " | " +
                "fechaInicio: " + fechaInicio + " | " +
                "fechaFin: " + fechaFin + " | " +
                "voluntarioEncargado: " + voluntarioEncargado + " | " +
                "estado: " + estado + " | " +
                "comentario: " + comentario;
    }
}
