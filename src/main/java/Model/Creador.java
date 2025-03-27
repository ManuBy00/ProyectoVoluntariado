package Model;

import java.util.HashSet;

public class Creador extends Usuario{

    private String ong;
    private ListaIniciativas iniciativas;

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
        this.iniciativas = ListaIniciativas.getInstance();
    }

    public ListaIniciativas getIniciativas() {
        return iniciativas;
    }

    public HashSet<Iniciativa> getMisIniciativas() {
        return iniciativas.obtenerIniciativasPorCreador(this.getCorreo());
    }

    public String mostrarMisIniciativas(){
        String result = "";
        for (Iniciativa i : getMisIniciativas()){
            result += i.getNombre() + " | ";
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

    public Iniciativa encontrarIniciativaPropia(String nombre) {
        Iniciativa iniciativaEncontrada = null;
        for (Iniciativa i : getMisIniciativas()){
            if (nombre.equals(i.getNombre())){
                iniciativaEncontrada = i;
            }
        }
        return iniciativaEncontrada;
    }


    // toString para ver los atributos del creador
    @Override
    public String toString() {
        return super.toString() +
                "\n ONG: " + ong +
                "\n Iniciativas: " + mostrarMisIniciativas();
    }
}
