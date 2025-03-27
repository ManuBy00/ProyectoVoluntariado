package Model;

import Exceptions.UsuarioNoExiste;

import java.util.ArrayList;
import java.util.List;

public class Iniciativa implements CRUD {
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

    // Getters (no setters para evitar modificaciones incontroladas) IMPORTANTE
    public String getNombre() { return nombre; }

    public ArrayList<Actividad> getActividades() { return actividades; }

    public Creador getCreador() {
        return creador;
    }

    // --- Métodos para añadir una actividad | falta completar actividad ---
    public void addActividad(Actividad actividad) {
        actividades.add(actividad);
    }

    @Override
    public boolean add(Object elemento) {
        boolean agregado = false;
        if (actividades.add((Actividad) elemento)){
            agregado = true;
        }
        return agregado;
    }

    @Override
    public boolean update(Object elemento) {
        boolean actualizado = false;

        return actualizado;
    }

    @Override
    public boolean remove(Object elemento) {
        boolean eliminado = false;
        if (actividades.remove((Actividad) elemento)){
            eliminado = true;
        }
        return eliminado;
    }

    @Override
    public String mostrar(Object elemento) { return toString(); }

    @Override
    public void mostrarConjunto() {
        for (Actividad actividad : actividades) {
            System.out.println(actividad);
        }
    }

    @Override
    public Object encontrarElemento(Object o) {
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
