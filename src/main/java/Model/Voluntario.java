package Model;

import View.UsuariosView;

import java.util.ArrayList;
import java.util.HashSet;

import static View.IniciativaView.ANSI_YELLOW;
import static View.UsuariosView.ANSI_PURPLE;
import static View.UsuariosView.ANSI_RESET;

public class Voluntario extends Usuario{
    private int puntos;
    private ArrayList<Actividad> actividadesAsignadas;

    public Voluntario(String nombre, String contraseña, String correo) {
        super(nombre, contraseña, correo);
        puntos = 0;
        actividadesAsignadas = new ArrayList<>();
    }

    public int getPuntos() {
        return puntos;
    }

    public void añadirPuntos(int puntos) {
        this.puntos += puntos;
    }

    public ArrayList<Actividad> getActividadesAsignadas() {
        return actividadesAsignadas;
    }

    public void mostrarActvidadesVoluntario(){
        int index = 1;
        for (Actividad actividad : actividadesAsignadas) {
            UsuariosView.mostrarMensaje(index + ". " + actividad); // Muestra actividad con estado actual
            index++;
        }
    }


    @Override
    public String toString() {
        return super.toString() +  // Llamada al toString de la clase padre
                ANSI_YELLOW + "\n=== Información de Voluntario ===" + ANSI_RESET +
                ANSI_PURPLE + "\n- Actividades Asignadas: " + ANSI_RESET + actividadesAsignadas.size() +
                ANSI_YELLOW + "\n===============================" + ANSI_RESET;
    }
}
