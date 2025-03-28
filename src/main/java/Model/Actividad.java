package Model;


import java.util.HashSet;
import java.util.Objects;

public class Actividad {

    private String nombre;
    private String descripcion;
    private String fechaInicio;
    private String fechaFin;
    private Voluntario voluntarioEncargado;
    private EstadoActividad estado;
    private String comentario;
    private HashSet<Voluntario> voluntariosAsignados;

    public Actividad(String nombre, String descripcion, String fechaInicio, String fechaFin, Voluntario voluntarioEncargado, String comentario) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.voluntarioEncargado = voluntarioEncargado;
        this.estado = estado.PENDIENTE;
        this.comentario = comentario;
        this.voluntariosAsignados = new HashSet<>();
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

    public Voluntario getVoluntarioEncargado() {
        return voluntarioEncargado;
    }

    public EstadoActividad getEstado() {
        return estado;
    }

    public String getComentario() {
        return comentario;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }


    /**
     * Cambia el estado de la actividad.
     * @param nuevoEstado El nuevo estado que se asignará a la actividad.
     */

    public void setEstado(EstadoActividad nuevoEstado) {
        this.estado = nuevoEstado;
    }

    public HashSet<Voluntario> getVoluntariosAsignados() {
        return voluntariosAsignados;
    }


    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Actividad actividad)) return false;
        return Objects.equals(nombre, actividad.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(nombre);
    }

    @Override
    public String toString() {
        return "Actividad: \n" +
                "nombre: " + nombre + " | " +
                "descripcion: " + descripcion + " | " +
                "fechaInicio: " + fechaInicio + " | " +
                "fechaFin: " + fechaFin + " | " +
                "voluntarioEncargado: " + voluntarioEncargado.getNombre() + " | " +
                "estado: " + estado + " | " +
                "comentario: " + comentario;
    }






}
