package Model;

import java.util.HashSet;

import static View.IniciativaView.*;

public class Creador extends Usuario{

    private String ong;


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

    /**
     * Llama al la lista de iniciativas haciendo uso del metodo "ObtenerIniciativasPorCreador",
     * que devuelve una lista de iniciativas con un correo pasado como parámetro
     * @return lista de iniciativas creadas por el usuario
     */
    public HashSet<Iniciativa> getMisIniciativas() {
        return ListaIniciativas.getInstance().obtenerIniciativasPorCreador(this.getCorreo());
    }

    public String mostrarMisIniciativas(){
        String result = "";
        for (Iniciativa i : getMisIniciativas()){
            result += i.getNombre() + " | ";
        }
        return result;
    }

    /**
     * Devuelve una iniciativa a través de su nombre
     * @param nombre criterio de búsqueda
     * @return la iniciativa que coincida con el nombre introducido
     */
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
                ANSI_RED + "\n=== Información de Creador ===" + ANSI_RESET +
                ANSI_CYAN + "\n- ONG: " + ANSI_RESET + ong +  // Suponiendo que 'ong' es el nombre de la ONG del creador
                ANSI_YELLOW + "\n- Iniciativas Creadas: " + ANSI_RESET + mostrarMisIniciativas() + "\n" +
                ANSI_RED + "===============================" + ANSI_RESET;
    }
}
