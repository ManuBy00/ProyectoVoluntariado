package Model;

import Utils.Sesion;
import Utils.Utilidades;
import View.IniciativaView;
import View.UsuariosView;
import View.ViewActividades;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static View.IniciativaView.mostrarMenuIniciativa;

public class ListaIniciativas {
    private HashSet<Iniciativa> iniciativasList; // Lista que almacena todas las iniciativas existentes.
    private static ListaIniciativas instance;

    private ListaIniciativas() { //Contrustor que inicializa la lista
        this.iniciativasList = new HashSet<>();
    }

    // Método estático para obtener la única instancia de la clase
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
    public boolean addIniciativa(Iniciativa iniciativa){
        boolean added = false;
        if (iniciativasList.add(iniciativa)){
            added = true;
        }
        return added;
    }

    /**
     * Metodo para actualizar una actividad dentro de una iniciativa existente.
     */
    public void actualizarActividad() {
        Iniciativa iniciativa = IniciativaView.pedirDatosIniciativa();

        if (iniciativa == null) {
            System.out.println("No se encontró la iniciativa. Intente de nuevo.");
            return;
        }

        int opcion = mostrarMenuIniciativa();

        switch (opcion) {
            case 1:
                actualizarActividad(iniciativa, "Actividad 1", "Descripción actualizada de la actividad 1");
                break;
            default:
                System.out.println("Opción inválida.");
        }
    }


    /**
     * Metodo para agregar una actividad a una iniciativa
     */
    public void agregarActividad() {
        Iniciativa iniciativa = encontrarIniciativa(Utilidades.pideString("Introduce el nombre de la inciativa en la que quiere añadir"));
        Actividad actividad = ViewActividades.pedirDatosActividad();
        if (iniciativa == null){
            UsuariosView.mostrarMensaje("La iniciativa no exite");
        }else {
            if (iniciativa.add(actividad)) {
                UsuariosView.mostrarMensaje("Actividad agregada exitosamente.");
            } else {
                System.out.println("No se pudo agregar la actividad.");
            }
        }
    }

    /**
     * Metodo privado para actualizar una actividad en una iniciativa dada.
     * @param iniciativa La iniciativa que contiene la actividad.
     * @param nombre Nombre de la actividad a actualizar.
     * @param descripcion Nueva descripción de la actividad.
     */
    private void actualizarActividad(Iniciativa iniciativa, String nombre, String descripcion) {
        Actividad actividad = iniciativa.encontrarElemento(nombre);
        if (actividad != null) {
            actividad.setDescripcion(descripcion);
            if (iniciativa.update(actividad)) {
                System.out.println("Actividad actualizada exitosamente.");
            } else {
                System.out.println("No se pudo actualizar la actividad.");
            }
        } else {
            System.out.println("No se encontró la actividad.");
        }
    }



    /**
     * Metodo privado para eliminar una actividad específica de una iniciativa.
     * @param iniciativa La iniciativa que contiene la actividad.
     * @param nombre Nombre de la actividad a eliminar.
     */
    private void eliminarActividad(Iniciativa iniciativa, String nombre) {
        Actividad actividad = iniciativa.encontrarElemento(nombre);
        if (actividad != null) {
            if (iniciativa.remove(actividad)) {
                System.out.println("Actividad eliminada exitosamente.");
            } else {
                System.out.println("No se pudo eliminar la actividad.");
            }
        } else {
            System.out.println("No se encontró la actividad.");
        }
    }

    /**
     * Metodo para mostrar la información de una iniciativa específica.
     */
    public void mostrarIniciativa() {
        Iniciativa iniciativa = IniciativaView.pedirDatosIniciativa();

        if (iniciativa == null) {
            System.out.println("No se encontró la iniciativa. Intente de nuevo.");
            return;
        }

        System.out.println(iniciativa);
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


    public String mostrarIniciativas() {
        String result = "";
        // Si la lista de iniciativas no está vacía, la recorremos
        if (iniciativasList != null) {
            for (Iniciativa i : iniciativasList) {
                result += "Iniciativa " + ": " + i.getNombre();
            }
        } else {
            result = "No hay iniciativas disponibles.";
        }

        return result;
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



}
