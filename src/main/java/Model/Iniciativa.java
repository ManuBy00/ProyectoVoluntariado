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
     * @return : Devuelve si se a podido agregar o no
     */

    public boolean add(Actividad actividad) {
        boolean agregado = false;
        if (actividades.add(actividad)) {
            agregado = true;
        }
        return agregado;
    }


    public boolean update(String nombreActividad, String nuevaDescripcion, LocalDate nuevaFechaInicio, LocalDate nuevaFechaFin, Voluntario nuevoEncargado) {
        Actividad actividad = encontrarActividad(nombreActividad);
        boolean updated = true;

        if (actividad != null) {
            // Actualizamos los atributos de la actividad
            actividad.setDescripcion(nuevaDescripcion);
            do {
                actividad.setFechaInicio(nuevaFechaInicio);
                actividad.setFechaFin(nuevaFechaFin);
            }while (!Utilidades.validarFechaInicioFin(nuevaFechaInicio, nuevaFechaFin)); //validamos la fecha de inicio y fin

            actividad.setVoluntarioEncargado(nuevoEncargado);
        }else {
            updated = false;
        }
        return updated; // Actualización exitosa
    }

    /**
     * Metodo que elimina una actividad del arrayList actividades
     * @param actividad : la actividad que vamos a eliminar
     * @return : si se a eliminado o no correctamente
     */

    public boolean remove(Actividad actividad) {
        boolean eliminado = false;
        if (actividades.remove(actividad)) {
            eliminado = true;
        }
        return eliminado;
    }

    /**
     * Metodo que muestra todos los atributos de una activdad
     * @param actividad : la actividad que queremos ver
     * @return : toString de la actividad que queremos mostrar
     */

    public String mostrar(Actividad actividad) { return actividad.toString(); }

    /**
     * Metodo que muestra el nombre de todas las actividades que hay en el ArrayList actividades
     */
    public String mostrarActividades() {
        String result = "";
        for (Actividad actividad : actividades) {
            result += actividad.getNombre() + " | ";
        }
        return result;
    }

    /**
     * Metodo que encuentra una actividad en el ArrayList actividades
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


    // Metodo para eliminar una actividad de la iniciativa
    public void eliminarActividad(Actividad actividad) {
        actividades.remove(actividad);
    }

    @Override
    public String toString() {
        return nombre +
                "\n - descripcion: " + descripcion +
                "\n - creador: " + creador.getNombre() +
                "\n - actividades: " + mostrarActividades();
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
