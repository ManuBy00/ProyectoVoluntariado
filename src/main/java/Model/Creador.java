package Model;

import java.util.ArrayList;

public class Creador extends Usuario{

    private String ong;
    private ArrayList<Iniciativa> iniciativas;

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
    }

    public void addIniciativa(Iniciativa nuevaIniciativa){
        iniciativas.add(nuevaIniciativa);
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
                "\n Iniciativas: " + iniciativas;
    }
}
