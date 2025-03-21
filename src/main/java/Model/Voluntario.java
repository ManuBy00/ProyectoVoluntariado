package Model;

public class Voluntario extends Usuario{
    private int puntos;

    public Voluntario(String nombre, String contraseña, String correo) {
        super(nombre, contraseña, correo);
        puntos = 0;
    }

    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }



    public String toString() {
        return super.toString() + "\n Puntos : " + puntos;
    }
}
