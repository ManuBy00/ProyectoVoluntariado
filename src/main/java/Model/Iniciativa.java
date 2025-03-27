package Model;

import java.util.ArrayList;
import java.util.List;

public class Iniciativa {
    private String nombre;
    private String descripcion;
    private Creador creador; // ¡Debe ser un usuario de tipo Creador!
    private List<Actividad> actividades;

    // Constructor
    public Iniciativa(String nombre, String descripcion, Creador creador) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.creador = creador;
        this.actividades = new ArrayList<>();
    }

    // --- Métodos para añadir una actividad | falta completar actividad ---
    public void addActividad(Actividad actividad) {
        actividades.add(actividad);
    }

    // Getters (no setters para evitar modificaciones incontroladas) IMPORTANTE
    public String getNombre() { return nombre; }
    public List<Actividad> getActividades() { return actividades; }

    public Creador getCreador() {
        return creador;
    }
}