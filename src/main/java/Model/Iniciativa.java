package Model;

import Utils.Sesion;
import Utils.Utilidades;

import javax.xml.bind.annotation.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;

@XmlRootElement(name = "iniciativa") // Define la raíz XML
@XmlAccessorType(XmlAccessType.FIELD)
public class Iniciativa {
    @XmlElement
    private String nombre;
    @XmlElement
    private String descripcion;
    @XmlElement
    private Creador creador;
    @XmlElementWrapper(name = "actividades")
    @XmlElement(name = "actividad")
    private ArrayList<Actividad> actividades;

    public Iniciativa(){

    }

    // Constructor full equip
    public Iniciativa(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.creador = (Creador) Sesion.getInstancia().getUsuarioIniciado();
        this.actividades = new ArrayList<>();
    }


    public String getNombre() { return nombre; }

    public ArrayList<Actividad> getActividades() { return actividades; }

    public Creador getCreador() {
        return creador;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     *  Metodo que añade una actividad al ArrayList actividades
     * @param actividad : la actividad que queremos añadir
     * @return : devuelve true si se añade, false si no.
     */
    public boolean addActividad(Actividad actividad) {
        boolean agregado = false;
        if (actividades.add(actividad)) {
            agregado = true;
        }
        return agregado;
    }

    /**
     * Busca la actividad requerida y actualiza sus datos
     * @param actividad .
     * @param nuevaDescripcion .
     * @param nuevaFechaInicio .
     * @param nuevaFechaFin .
     * @param nuevoEncargado .
     */
    public void updateActividad(Actividad actividad, String nuevaDescripcion, LocalDate nuevaFechaInicio, LocalDate nuevaFechaFin, Voluntario nuevoEncargado) {
        // Actualizamos los atributos de la actividad
        actividad.setDescripcion(nuevaDescripcion);
        do {
            actividad.setFechaInicio(nuevaFechaInicio);
            actividad.setFechaFin(nuevaFechaFin);
        } while (!Utilidades.validarFechaInicioFin(nuevaFechaInicio, nuevaFechaFin)); //validamos la fecha de inicio y fin
        actividad.setVoluntarioEncargado(nuevoEncargado.getNombre());
    }

    /**
     * Metodo que elimina una actividad del arrayList actividades
     * @param actividad : la actividad que vamos a eliminar
     * @return : Devuelve true si la actividad fue eliminada (estaba en la lista y se eliminó), false si no estaba
     */

    public boolean removeActividad(Actividad actividad) {
        return actividades.remove(actividad);
    }


    /**
     * Metodo que muestra el nombre de todas las actividades que hay en el ArrayList actividades
     */
    public String mostrarActividades() {
        StringBuilder result = new StringBuilder();
        for (Actividad actividad : actividades) {
            result.append(actividad.getNombre()).append(" | ");
        }
        return result.toString();
    }

    /**
     * Metodo que busca una actividad en el ArrayList actividades
     * @param nombre : nombre de la actividad que queremos encontrar
     * @return : Devuelve la actividad si la a encontrado o null si no la a encontrado
     */
    public Actividad encontrarActividad(String nombre) {
            Actividad acitividadEncontrado = null;
            for (Actividad a : actividades){
                if (nombre.equals(a.getNombre())){
                    acitividadEncontrado = a;
                }
            }
            return acitividadEncontrado;
    }


    @Override
    public String toString() {
        return "\n🌟 Iniciativa: " + nombre +
                "\n━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━" +
                "\n📝 Descripción: " + descripcion +
                "\n👤 Creador: " + creador.getNombre() +
                "\n📋 Actividades:\n" + mostrarActividades() +
                "\n━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━\n";
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Iniciativa that)) return false;
        return Objects.equals(nombre, that.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(nombre);
    }
}
