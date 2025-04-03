package Model;


import DataAccess.LocalDateAdapter;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
@XmlRootElement(name = "actividad")
@XmlAccessorType(XmlAccessType.FIELD)
public class Actividad {
    @XmlElement
    private String nombre;
    @XmlElement
    private String descripcion;
    @XmlElement
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    private LocalDate fechaInicio;
    @XmlElement
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    private LocalDate fechaFin;
    @XmlElement
    private String voluntarioEncargado;
    @XmlElement
    private EstadoActividad estado;
    @XmlElement
    private String comentario;
    @XmlElement
    private HashSet<String> voluntariosAsignados;

    public Actividad(String nombre, String descripcion, LocalDate fechaInicio, LocalDate fechaFin, String voluntarioEncargado, String comentario) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.voluntarioEncargado = voluntarioEncargado;
        this.estado = EstadoActividad.PENDIENTE;
        this.comentario = comentario;
        this.voluntariosAsignados = new HashSet<>();
    }

   public Actividad(){
       this.voluntariosAsignados = new HashSet<>();
   }

    public String getNombre() {
        return nombre;
    }


    public EstadoActividad getEstado() {
        return estado;
    }


    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    public void setVoluntarioEncargado(String voluntarioEncargado) {
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

    public HashSet<String> getVoluntariosAsignados() {
        return voluntariosAsignados;
    }

    public String imprimirNombreVoluntarios() {
        if (voluntariosAsignados.isEmpty()) {
            return "No hay voluntarios asignados.";
        }
        return String.join(", ", voluntariosAsignados);
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
                "\n - voluntario encargado: " + voluntarioEncargado +
                "\n - voluntarios asignados: " + imprimirNombreVoluntarios() +
                "\n - estado: " + estado.getEstado() +
                "\n - comentario: " + comentario;
    }

    /**
     * devuelve si la actividad está cancelada
     * @return true si está cancealda
     */
    public boolean isCancelada() {
        return estado == EstadoActividad.CANCELADA;
    }

    /**
     * devuelve si la actividad está finalizada
     * @return true si está finalizada
     */
    public boolean isFinalizada() {
        return estado == EstadoActividad.FINALIZADA;
    }
}
