package Model;

import java.util.ArrayList;
import java.util.HashSet;

public class Creador extends Usuario{

    private String ong;
    private HashSet<Iniciativa> iniciativas;

    /**
     * Constructor de creador
     * @param nombre
     * @param contraseña
     * @param correo
     * @param ong
     */
    public Creador(String nombre, String contraseña, String correo, String ong) {
        super(nombre, contraseña, correo);
        this.ong = ong;
        this.iniciativas = new HashSet<>();
    }

    public HashSet<Iniciativa> getIniciativas() {
        return iniciativas;
    }

    public boolean addIniciativa(Iniciativa nuevaIniciativa){
        boolean added = false;
        if (iniciativas.add(nuevaIniciativa)) {
            added = true;
        }
        return added;
    }

    public String mostrarIniciativas() {
        String result = "";
        // Si la lista de iniciativas no está vacía, la recorremos
        if (iniciativas != null) {
            for (Iniciativa i : iniciativas) {
                result += "Iniciativa " + ": " + i.getNombre();
            }
        } else {
            result = "No hay iniciativas disponibles.";
        }

        return result;
    }


    //get y set ONG
    public String getOng() {
        return ong;
    }

    public void setOng(String ong) {
        this.ong = ong;
    }

    // toString para ver los atributos del creador
    @Override
    public String toString() {
        return super.toString() +
                "\n ONG: " + ong +
                "\n Iniciativas: " + mostrarIniciativas();
    }
}
