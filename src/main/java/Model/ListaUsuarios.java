package Model;

import Exceptions.UsuarioNoExiste;
import Exceptions.UsuarioYaExiste;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * Clase que gestiona la colección de usuarios del sistema (Creadores y Voluntarios).
 * Implementa operaciones CRUD y métodos específicos de búsqueda/validación.
 * Usa HashSet para evitar duplicados basados en equals()/hashCode() de Usuario.
 */

public class ListaUsuarios implements CRUD<Usuario, String>{
    private HashSet<Usuario> usuarios; // Usa HashSet para garantizar que no hayan duplicados

    /**
     * Constructor que inicializa la lista vacía.
     */

    public ListaUsuarios() {
        this.usuarios = new HashSet<>();
    }


    // --- Getters/Setters ---

    public HashSet<Usuario> getUsuarios() {
        return usuarios;
    }


    public void setUsuarios(HashSet<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    /**
     * Añade un nuevo usuario al conjunto.
     * @param nuevoUsuario Usuario a añadir.
     * @return true si se añade correctamente.
     * @throws UsuarioYaExiste Si el correo ya está registrado.
     */



    @Override
    public boolean add(Usuario nuevoUsuario) throws UsuarioYaExiste {
        if (!usuarios.add(nuevoUsuario)) {
            throw new UsuarioYaExiste("El correo introducido ya está registrado.");
        }
        return true;
    }


    /**
     * Actualiza un usuario existente (PENDIENTE DE IMPLEMENTAR).
     * @param usuarioModificado Usuario con datos actualizados.
     * @return true siempre (debería validar cambios).
     */

    @Override
    public boolean update(Usuario usuarioModificado) {
    return true;
    }


    /**
     * Elimina un usuario del conjunto.
     * @param usuario Usuario a eliminar.
     * @return true si se elimina correctamente.
     * @throws UsuarioNoExiste Si el usuario no existe.
     */


    @Override
    public boolean remove(Usuario usuario) throws UsuarioNoExiste {
        if (!usuarios.remove(usuario)){
            throw new UsuarioNoExiste("Error, el usuario introducido no existe.");
        }
        return true;
    }

    /**
     * Devuelve la representación en String de un usuario.
     * @param usuario Usuario a mostrar.
     * @return String con detalles del usuario.
     */


    @Override
    public String mostrar(Usuario usuario) {
        return usuario.toString();
    }

    /**
     * Muestra todos los usuarios del sistema (para depuración).
     */

    @Override
    public void mostrarConjunto() {
        for (Usuario u : usuarios){
            System.out.println(u.toString());
        }
    }

    //metodo para obetner lista de creadores
    public ArrayList<Creador> ListaCreadores(ArrayList<Usuario> usuarios) {
        ArrayList<Creador> creadores = new ArrayList<>();

        for (Usuario usuario : usuarios) {
            if (usuario instanceof Creador) {
                creadores.add((Creador) usuario); // Casting para añadirlo al array de creadores
            }
        }
        return creadores;
    }





    //metodo para obetner lista de voluntarios
    public ArrayList<Voluntario> ListaVoluntarios(ArrayList<Usuario> usuarios) {
        ArrayList<Voluntario> voluntarios = new ArrayList<>();

        for (Usuario usuario : usuarios) {
            if (usuario instanceof Voluntario) {
                voluntarios.add((Voluntario) usuario); // Casting para añadirlo al array de voluntarios
            }
        }
        return voluntarios;
    }


    /**
     * Busca un usuario por su correo.
     * @param correo Correo del usuario a buscar.
     * @return Usuario encontrado o null si no existe.
     */


    @Override
    public Usuario encontrarElemento(String correo) {
        Usuario usuarioEncontrado = null;
        for (Usuario u : usuarios){
            if (correo.equals(u.getCorreo())){
                usuarioEncontrado = u;
            }
        }
        return usuarioEncontrado;
    }

    /**
     * Valida un usuario por su correo y contraseña.
     * @param correo Correo del usuario.
     * @param password Contraseña del usuario.
     * @return Usuario validado o null si no existe.
     */

    public Usuario validarUsuario(String correo, String password){
        Usuario usuarioValidado = null;
        for (Usuario u : usuarios){
            if (correo.equals(u.getCorreo()) && password.equals(u.getPassword())){
                usuarioValidado = u;
            }
        }
        return usuarioValidado;
    }

    // CREO QUE TE FALTA ESTO
    //metodo para obtener la lista de usuarios
//    public ArrayList<Usuario> getListaUsuarios() {
//        ArrayList<Usuario> listaUsuarios = new ArrayList<>(usuarios);
//        return listaUsuarios;
//    }



}
