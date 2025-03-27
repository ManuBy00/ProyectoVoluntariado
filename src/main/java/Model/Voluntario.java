package Model;

public class Voluntario extends Usuario{
    private int puntos;


    // Constructor de Voluntario
    public Voluntario(String nombre, String contraseña, String correo) {
        super(nombre, contraseña, correo);
        puntos = 0;
    }



    // GETTERS Y SETTERS
    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }


    // toString para poder ver los atributos del voluntario
    public String toString() {
        return super.toString() + "\n Puntos : " + puntos;
    }
}
