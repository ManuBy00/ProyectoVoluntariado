package Model;

import Exceptions.UsuarioNoExiste;
import Exceptions.UsuarioYaExiste;

import java.util.ArrayList;
import java.util.HashSet;

public class ListaUsuarios implements CRUD<Usuario, String>{
    private HashSet<Creador> creadores;
    private HashSet<Voluntario> voluntarios;
    private static ListaUsuarios instance;

    /**
     * Constructor que inicializa la lista vacía.
     */
    private ListaUsuarios() {
        this.creadores = new HashSet<>();
        this.voluntarios = new HashSet<>();
    }

    /**
     * get singletone para no crear varias listas de usuarios.
     * @return lista única de usuarios
     */
    public static ListaUsuarios getInstance() {
        if (instance == null) {
            instance = new ListaUsuarios();
        }
        return instance;
    }

    public HashSet<Creador> getCreadores() {
        return creadores;
    }

    public HashSet<Voluntario> getVoluntarios() {
        return voluntarios;
    }

    /**
     * Identifica la clase del usuario y lo añade a la lista correspondiente.
     * @param nuevoUsuario Usuario a añadir.
     * @return true si se añade correctamente.
     * @throws UsuarioYaExiste Si el correo ya está registrado.
     */
    @Override
    public boolean add(Usuario nuevoUsuario) throws UsuarioYaExiste {
        if (nuevoUsuario instanceof Creador) {
            if (!creadores.add((Creador) nuevoUsuario)) {
                throw new UsuarioYaExiste("El correo introducido ya está registrado como creador.");
            }
        }else{
            if (!voluntarios.add((Voluntario) nuevoUsuario)) {
                throw new UsuarioYaExiste("El correo introducido ya está registrado como voluntario.");
            }
        }

        return true;
    }

    /** *PENDIENTE*
     * Actualiza un usuario existente (PENDIENTE DE IMPLEMENTAR).
     * @param usuarioModificado Usuario con datos actualizados.
     * @return true siempre (debería validar cambios).
     */
    @Override
    public boolean update(Usuario usuarioModificado) {
    return true;
    }

    /**
     * Elimina un usuario de la lista de usuarios
     * @param usuario
     * @return
     * @throws UsuarioNoExiste
     */
    @Override
    public boolean remove(Usuario usuario) throws UsuarioNoExiste {
        if (usuario instanceof Creador) {
            if (!creadores.remove((Creador) usuario)) {
                throw new UsuarioNoExiste("Error, el usuario introducido no existe como creador.");
            }
        } else {
            if (!voluntarios.remove((Voluntario) usuario)) {
                throw new UsuarioNoExiste("Error, el usuario introducido no existe como voluntario.");
            }
        }

        return true;
    }

    /**
     * muestra un usuario de la lista (llamando al toString del usuario)
     * @param usuario usuario del que se va a mostrar la información.
     * @return string detalles del usuario.
     */
    @Override
    public String mostrar(Usuario usuario) {
        return usuario.toString();
    }

    /**
     * Devuelve una string de la lista completa de voluntarios.
     */

    public String mostrarVoluntarios() {
        String result = "";
        for (Voluntario v : voluntarios) {
            result += v.toString();
        }
        return result;
    }

    /**
     * Devuelve una string de la lista completa de creadores.
     */
    public String mostrarCreadores() {
        String result = "";
        for (Creador c : creadores) {
            result += c.toString();
        }
        return result;
    }


    /**
     * Busca un usuario a través de su correo
     * @param correo correo del usuario que se desea buscar
     * @return usuario al que le pertenece el correo
     * @throws UsuarioNoExiste si no hay ningún usuario con el correo introducido
     */

    public Creador encontrarCreador(String correo){
        Creador usuarioEncontrado = null;
        for (Creador c : creadores){
            if (correo.equals(c.getCorreo())){
                usuarioEncontrado = c;
            }
        }
        return usuarioEncontrado;
    }

    public Voluntario encontrarVoluntario(String correo){
        Voluntario usuarioEncontrado = null;
        for (Voluntario v : voluntarios){
            if (correo.equals(v.getCorreo())){
                usuarioEncontrado = v;
            }
        }
        return usuarioEncontrado;
    }


    /**
     * valida el login de un creador. Primero busca si el usuario está registrado, y después verifica su contraseña.
     * @param correo
     * @param password
     * @return usuario validado
     */
    public Usuario validarLoginCreador(String correo, String password){ //comprobar si el usuario esta en el arraylist de usuarios y validar contraseña
        Creador usuarioValidado = encontrarCreador(correo);

        if (usuarioValidado != null && usuarioValidado.verificarPassword(password)) {
            return usuarioValidado; // Solo devuelve el usuario si la contraseña y el correo coinciden
        }
        return null; // devuelve null si la contraseña es incorrecta
    }

    /**
     * valida el login de un voluntario. Primero busca si el usuario está registrado, y después verifica su contraseña.
     * @param correo
     * @param password
     * @return usuario validado
     */
    public Usuario validarLoginVoluntario(String correo, String password){ //comprobar si el usuario esta en el arraylist de usuarios y validar contraseña
        Voluntario usuarioValidado = encontrarVoluntario(correo);

        if (usuarioValidado != null && usuarioValidado.verificarPassword(password)) {
            return usuarioValidado; // Solo devuelve el usuario si la contraseña y el correo coinciden
        }
        return null; // devuelve null si la contraseña es incorrecta
    }
}


