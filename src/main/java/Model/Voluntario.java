package Model;

import View.UsuariosView;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

import static View.IniciativaView.ANSI_YELLOW;
import static View.UsuariosView.ANSI_PURPLE;
import static View.UsuariosView.ANSI_RESET;

@XmlRootElement(name = "voluntario")
@XmlAccessorType(XmlAccessType.FIELD)
public class Voluntario extends Usuario{
    @XmlElement
    private int puntos;
    @XmlElement
    private ArrayList<Actividad> actividadesAsignadas;
    @XmlElement
    private List<Recompensa> recompensasDisponibles;

    public Voluntario(String nombre, String password, String correo) {
        super(nombre, password, correo);
        puntos = 0;
        actividadesAsignadas = new ArrayList<>();
        this.recompensasDisponibles = new ArrayList<>();
    }

    public Voluntario() { //constructor vacío para deserializar
        super();
        actividadesAsignadas = new ArrayList<>();
        this.recompensasDisponibles = new ArrayList<>();

    }


    public int getPuntos() {
        return puntos;
    }

    public List<Recompensa> getRecompensasDisponibles() {
        return recompensasDisponibles;
    }

    public void addPuntos(int puntos) {
        this.puntos += puntos;
    }

    public void restarPuntos(int puntos) { this.puntos -= puntos; }

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
                ANSI_PURPLE + "\n- Recompensas canjeadas: " + ANSI_RESET + recompensasDisponibles +
                ANSI_YELLOW + "\n===============================" + ANSI_RESET;
    }
}
