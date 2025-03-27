package Model;

import Exceptions.UsuarioNoExiste;

import java.util.ArrayList;
import java.util.List;

public class Iniciativa implements CRUD< Actividad, String> {
    private String nombre;
    private String descripcion;
    private Creador creador; // ¡Debe ser un usuario de tipo Creador!
    private ArrayList<Actividad> actividades;
    private ArrayList<Usuario> ranking;

    // Constructor
    public Iniciativa(String nombre, String descripcion, Creador creador) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.creador = creador;
        this.actividades = new ArrayList<>();
    }

    public Iniciativa(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.actividades = new ArrayList<>();
    }

    public String getNombre() { return nombre; }

    public ArrayList<Actividad> getActividades() { return actividades; }

    public Creador getCreador() {
        return creador;
    }



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

    @Override
    public boolean remove(Actividad actividad) {
        boolean eliminado = false;
        if (actividades.remove(actividad)) {
            eliminado = true;
        }
        return eliminado;
    }

    @Override
    public String mostrar(Actividad actividad) { return toString(); }

    @Override
    public void mostrarConjunto() {
        for (Actividad actividad : actividades) {
            System.out.println(actividad);
        }
    }

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


    @Override
    public String toString() {
        return "Iniciativa: " +
                "\n nombre: " + nombre +
                "\n descripcion: " + descripcion +
                "\n creador: " + creador +
                "\n actividades: " + actividades;
    }
}
