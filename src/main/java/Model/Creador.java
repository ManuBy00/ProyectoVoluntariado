package Model;

import java.util.ArrayList;

public class Creador extends Usuario{

    private String ong;
    private ArrayList<Iniciativa> iniciativa;
    // Declaramos la lista de la iniciativa



    // Constructor de Creador
    public Creador(String nombre, String contraseña, String correo, String ong) {
        super(nombre, contraseña, correo);
        this.ong = ong;
    }


    /**
     *  Getters y Setters
     * @return
     */

    public String getOng() {
        return ong;
    }

    public void setOng(String ong) {
        this.ong = ong;
    }


    // toString para poder ver los atributos del creador
    @Override
    public String toString() {
        return super.toString() + "Creador : " +
                "\n pertenece a la ong : " + ong;
    }



}
