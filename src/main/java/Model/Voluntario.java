package Model;

import java.util.ArrayList;
import java.util.HashSet;

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

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    public ArrayList<Actividad> getActividadesAsignadas() {
        return actividadesAsignadas;
    }


    public String toString() {
        return super.toString() + "\n Puntos : " + puntos;
    }
}
