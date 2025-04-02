package Model;


import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;

public class Actividad {

    private String nombre;
    private String descripcion;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private Voluntario voluntarioEncargado;
    private EstadoActividad estado;
    private String comentario;
    private HashSet<Voluntario> voluntariosAsignados;

    public Actividad(String nombre, String descripcion, LocalDate fechaInicio, LocalDate fechaFin, Voluntario voluntarioEncargado, String comentario) {
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

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public LocalDate getFechaFin() {
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

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setVoluntariosAsignados(HashSet<Voluntario> voluntariosAsignados) {
        this.voluntariosAsignados = voluntariosAsignados;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public void setVoluntarioEncargado(Voluntario voluntarioEncargado) {
        this.voluntarioEncargado = voluntarioEncargado;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
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

    public String imprimirNombreVoluntarios(){
        String voluntarios = "";
        for (Voluntario v : voluntariosAsignados){
            voluntarios += v.getNombre() + ", ";
        }
        return voluntarios;
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
        return   nombre +
                "\n - descripcion: " + descripcion +
                "\n - fechaInicio: " + fechaInicio +
                "\n - fechaFin: " + fechaFin+
                "\n - voluntario encargado: " + voluntarioEncargado.getNombre()+
                "\n - voluntarios asignados: " + imprimirNombreVoluntarios() +
                "\n - estado: " + estado +
                "\n - comentario: " + comentario;
    }

    public boolean isCancelada() {
        return estado == EstadoActividad.CANCELADA;
    }

    public boolean isFinalizada() {
        return estado == EstadoActividad.FINALIZADA;
    }
}
