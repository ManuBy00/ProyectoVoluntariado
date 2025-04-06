package Model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.HashSet;
import static View.IniciativaView.*;


@XmlRootElement(name = "creador")
@XmlAccessorType(XmlAccessType.FIELD)
public class Creador extends Usuario{
    @XmlElement
    private String ong;



    public Creador(String nombre, String password, String correo, String ong) {
        super(nombre, password, correo);
        this.ong = ong;
    }

    public Creador() { //para deserializar
        super();
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
        StringBuilder result = new StringBuilder();
        for (Iniciativa i : getMisIniciativas()){
            result.append(i.getNombre()).append(" | ");
        }
        return result.toString();
    }

    /**
     * Busca una iniciativa creada por el usuario a través del  nombre y la devuelve.
     * @param nombre nombre de la iniciativa que se busca
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
