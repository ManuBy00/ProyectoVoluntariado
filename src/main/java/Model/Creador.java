package Model;

import java.util.ArrayList;

public class Creador extends Usuario{

    private String ong;
    private ArrayList<Iniciativa> iniciativas;

    public Creador(String nombre, String contraseña, String correo, String ong) {
        super(nombre, contraseña, correo);
        this.ong = ong;
    }

    public String getOng() {
        return ong;
    }

    public void setOng(String ong) {
        this.ong = ong;
    }



    @Override
    public String toString() {
        return super.toString() +
                "\n ONG: " + ong;
    }



}
