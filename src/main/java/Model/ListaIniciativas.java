package Model;
import DataAccess.XML;
import Exceptions.IniciativaNoExiste;
import Exceptions.IniciativaYaExiste;
import Utils.Utilidades;
import View.UsuariosView;

import javax.xml.bind.annotation.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;

@XmlRootElement(name = "Iniciativas") // Define el elemento raíz del XML
@XmlAccessorType(XmlAccessType.FIELD) // Acceso a los campos directamente
public class ListaIniciativas implements CRUD<Iniciativa>{
    @XmlElementWrapper(name = "listaIniciativas") // Agrupa los elementos en un solo nodo
    @XmlElement(name = "iniciativa") // Define cada elemento dentro del wrapper
    private HashSet<Iniciativa> iniciativasList; // Lista que almacena todas las iniciativas existentes.
    private static ListaIniciativas instance;

    private ListaIniciativas() { //Contrustor que inicializa la lista
        this.iniciativasList = new HashSet<>();
    }


    public static ListaIniciativas getInstance() {
        if (instance == null) {
            instance = new ListaIniciativas();
        }
        return instance;
    }

    /**
     * Metodo para añadir una iniciativa a la lista.
     */
    @Override
    public void add(Iniciativa iniciativa) {
        if (!iniciativasList.add(iniciativa)){
            throw new IniciativaYaExiste("Ya existe una iniciativa con el nombre introducido");
        }
    }

    @Override
    public void update(Iniciativa iniciativa) throws IniciativaNoExiste{
        if (iniciativa == null){
            throw new IniciativaNoExiste("La iniciativa introducida no existe.");
        }
        String nuevoNombre = Utilidades.pideString("Introduce el nuevo nombre de la iniciativa:");
        String nuevaDescripcion = Utilidades.pideString("Introduce la nueva descripción:");


        iniciativa.setNombre(nuevoNombre);
        iniciativa.setDescripcion(nuevaDescripcion);
    }

    /**
     * Metodo para eliminar una iniciativa de la lista.
     */
    @Override
    public void remove(Iniciativa iniciativa) {
        if (!iniciativasList.remove(iniciativa)){
            throw new IniciativaNoExiste("La iniciativa introducida no existe");
        }
    }

    @Override
    public void mostrar(Iniciativa iniciativa){
        UsuariosView.mostrarMensaje(iniciativa.toString());
    }



    /**
     * Busca una iniciativa por nombre.
     * @param nombre : nombre de la iniciativa que estamos buscando
     * @return : devuelve la iniciativa si se a encontrado, null si no se a encontrado
     */
    public Iniciativa encontrarIniciativa(String nombre) {
        Iniciativa iniciativaEncontrada = null;
        for (Iniciativa i : iniciativasList){
            if (nombre.equals(i.getNombre())){
                iniciativaEncontrada = i;
            }
        }
        return iniciativaEncontrada;
    }


    public HashSet<Iniciativa> obtenerIniciativasPorCreador(String correoCreador) {
        HashSet<Iniciativa> iniciativasDelCreador = new HashSet<>();

        for (Iniciativa iniciativa : iniciativasList) {
            if (iniciativa.getCreador().getCorreo().equals(correoCreador)) {
                iniciativasDelCreador.add(iniciativa);
            }
        }
        return iniciativasDelCreador;
    }

    /**
     * crea una lista y añade en ella las actividades que no están canceladas ni terminadas.
     * @return arrayList de actividades sin termianr
     */
    public ArrayList<Actividad> getActividadesSinTerminar() {
        ArrayList<Actividad> actividadesSinTerminar = new ArrayList<>();
        for (Iniciativa iniciativa : iniciativasList) {
            for (Actividad actividad : iniciativa.getActividades()) {
                if (!actividad.isFinalizada() && !actividad.isCancelada()) {
                    actividadesSinTerminar.add(actividad);
                }
            }
        }
        return actividadesSinTerminar;
    }

    public void cargarIniciativasDesdeXML(String filename) {
        ListaIniciativas listaIniciativas = XML.readXML(ListaIniciativas.class, filename);
        if (listaIniciativas != null && listaIniciativas.iniciativasList != null) {
            this.iniciativasList = listaIniciativas.iniciativasList;
        }
    }
}
