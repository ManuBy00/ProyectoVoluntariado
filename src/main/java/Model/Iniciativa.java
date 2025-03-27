package Model;

import Exceptions.UsuarioNoExiste;
import Utils.Sesion;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Iniciativa implements CRUD< Actividad, String> {
    private String nombre;
    private String descripcion;
    private Creador creador; // ¡Debe ser un usuario de tipo Creador!
    private ArrayList<Actividad> actividades;


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


    /**
     *  Metodo que añade una actividad al ArrayList actividades
     * @param actividad : la actividad que queremos añadir
     * @return : Devuelve si se a podido agregar o no
     */
    @Override
    public boolean add(Actividad actividad) {
        boolean agregado = false;
        if (actividades.add(actividad)) {
            agregado = true;
        }
        return agregado;
    }

    @Override
    public boolean update(Actividad actividad) {
        boolean actualizado = false;

        return actualizado;
    }

    /**
     * Metodo que elimina una actividad del arrayList actividades
     * @param actividad : la actividad que vamos a eliminar
     * @return : si se a eliminado o no correctamente
     */
    @Override
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
    @Override
    public String mostrar(Actividad actividad) { return actividad.toString(); }

    /**
     * Metodo que muestra todas las actividades que hay en el ArrayList actividades
     */
    @Override
    public void mostrarConjunto() {
        for (Actividad actividad : actividades) {
            System.out.println(actividad);
        }
    }

    /**
     * Metodo que encuentra una actividad en el ArrayList actividades
     * @param nombre : nombre de la actividad que queremos encontrar
     * @return : Devuelve la actividad si la a encontrado o null si no la a encontrado
     */
    @Override
    public Actividad encontrarElemento(String nombre) {
            Actividad acitividadEncontrado = null;
            for (Actividad a : actividades){
                if (nombre.equals(a.getNombre())){
                    acitividadEncontrado = a;
                }
            }
            return acitividadEncontrado;
    }

    // Metodo para agregar una actividad a la iniciativa
    public void agregarActividad(Actividad actividad) {
        actividades.add(actividad);
    }

    // Metodo para eliminar una actividad de la iniciativa
    public void eliminarActividad(Actividad actividad) {
        actividades.remove(actividad);
    }

    @Override
    public String toString() {
        return "Iniciativa: " +
                "\n nombre: " + nombre +
                "\n descripcion: " + descripcion +
                "\n creador: " + creador +
                "\n actividades: " + actividades;
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
