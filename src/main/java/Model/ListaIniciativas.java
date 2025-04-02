package Model;
import Exceptions.IniciativaNoExiste;
import Exceptions.IniciativaYaExiste;
import Utils.Utilidades;
import View.UsuariosView;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;


public class ListaIniciativas implements CRUD<Iniciativa, String>{
    private HashSet<Iniciativa> iniciativasList; // Lista que almacena todas las iniciativas existentes.
    private static ListaIniciativas instance;

    private ListaIniciativas() { //Contrustor que inicializa la lista
        this.iniciativasList = new HashSet<>();
    }

    // Metodo estático para obtener la única instancia de la clase
    public static ListaIniciativas getInstance() {
        if (instance == null) {
            instance = new ListaIniciativas();
        }
        return instance;
    }

    public HashSet<Iniciativa> getIniciativasList() {
        return iniciativasList;
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
     * Pide seleccionar la iniciativa de la actividad que se quiere actualizar
     * @param nombreIniciativa
     * @param nombreActividad
     * @param nuevaDescripcion
     * @param nuevaFechaInicio
     * @param nuevaFechaFin
     * @param nuevoEncargado
     * @return el resultado de iniciativa.update
     * @throws IniciativaNoExiste si no se encuentra una inciativa con el nombre introducido
     */
    public void updateActividad(String nombreIniciativa, String nombreActividad, String nuevaDescripcion, LocalDate nuevaFechaInicio, LocalDate nuevaFechaFin, Voluntario nuevoEncargado) throws IniciativaNoExiste{
        Iniciativa iniciativa = encontrarIniciativa(nombreIniciativa);

        if (iniciativa == null) {
            throw new IniciativaNoExiste("La iniciativa introducida no existe");
        }

        iniciativa.update(nombreActividad, nuevaDescripcion, nuevaFechaInicio, nuevaFechaFin, nuevoEncargado);
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
        ArrayList<Actividad> actividadesDisponibles = new ArrayList<>();
        for (Iniciativa iniciativa : iniciativasList) {
            for (Actividad actividad : iniciativa.getActividades()) {
                if (!actividad.isFinalizada() && !actividad.isCancelada()) {
                    actividadesDisponibles.add(actividad);
                }
            }
        }
        return actividadesDisponibles;
    }
}
