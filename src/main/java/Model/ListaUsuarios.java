package Model;

import Exceptions.UsuarioNoExiste;
import Exceptions.UsuarioYaExiste;

import java.util.ArrayList;
import java.util.HashSet;

public class ListaUsuarios implements CRUD<Usuario, String>{
    private HashSet<Usuario> usuarios;
    private static ListaUsuarios instance;

    /**
     * Constructor que inicializa la lista vacía.
     */
    private ListaUsuarios() {
        this.usuarios = new HashSet<>();
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
        if (!usuarios.remove(usuario)){
            throw new UsuarioNoExiste("Error, el usuario introducido no existe.");
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
     * muestra la lista completa de usuarios.
     */
    @Override
    public void mostrarConjunto() {
        for (Usuario u : usuarios){
            System.out.println(u.toString());
        }
    }

    /**
     * Crea una lista de usuarios creadores
     * @return lista de creadores
     */
    public HashSet<Creador> ListaCreadores() {
        HashSet<Creador> creadores = new HashSet<>();

        for (Usuario usuario : usuarios) {
            if (usuario instanceof Creador) {
                creadores.add((Creador) usuario); // Casting para añadirlo al array de creadores
            }
        }
        return creadores;
    }

    /**
     * crea una lista de usuarios voluntarios
     * @return arrayList de voluntarios
     */
    public HashSet<Voluntario> ListaVoluntarios() {
        HashSet<Voluntario> voluntarios = new HashSet<>();

        for (Usuario usuario : usuarios) {
            if (usuario instanceof Voluntario) {
                voluntarios.add((Voluntario) usuario); // Casting para añadirlo al array de voluntarios
            }
        }
        return voluntarios;
    }

    /**
     * Busca un usuario a través de su correo
     * @param correo correo del usuario que se desea buscar
     * @return usuario al que le pertenece el correo
     * @throws UsuarioNoExiste si no hay ningún usuario con el correo introducido
     */
    @Override
    public Usuario encontrarElemento(String correo) throws UsuarioNoExiste{
        Usuario usuarioEncontrado = null;
        for (Usuario u : usuarios){
            if (correo.equals(u.getCorreo())){
                usuarioEncontrado = u;
            }
        }
        return usuarioEncontrado;
    }

    /**
     * valida el login de un usuario. Primero busca si el usuario está registrado, y después verifica su contraseña.
     * @param correo
     * @param password
     * @return usuario validado
     */
    public Usuario validarLogin(String correo, String password){ //comprobar si el usuario esta en el arraylist de usuarios y validar contraseña
        Usuario usuarioValidado = encontrarElemento(correo);

        if (usuarioValidado != null && usuarioValidado.verificarPassword(password)) {
            return usuarioValidado; // Solo devuelve el usuario si la contraseña y el correo coinciden
        }

        return null; // devuelve null si la contraseña es incorrecta
    }
}
