package Model;

import Exceptions.UsuarioNoExiste;
import Exceptions.UsuarioYaExiste;
import Utils.Sesion;
import Utils.Utilidades;
import View.UsuariosView;
import View.ViewActividades;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class ListaUsuarios implements CRUD<Usuario, String>{
    private HashSet<Creador> creadores;
    private HashSet<Voluntario> voluntarios;
    private static ListaUsuarios instance;
    private List<Actividad> actividadesDisponibles;
    private List<Producto> productosDisponibles;
    private List<Recompensa> recompensasDisponibles;

    /**
     * Constructor que inicializa la lista vacía.
     */
    private ListaUsuarios() {
        this.creadores = new HashSet<>();
        this.voluntarios = new HashSet<>();
        this.recompensasDisponibles = new ArrayList<>();
    }

    /**
     * Obtiene la instancia única de la lista de usuarios (Singleton).
     * @return instancia única de ListaUsuarios
     */
    public static ListaUsuarios getInstance() {
        if (instance == null) {
            instance = new ListaUsuarios();
        }
        return instance;
    }

    /**
     * Obtiene el conjunto de creadores registrados.
     * @return conjunto de objetos Creador
     */
    public HashSet<Creador> getCreadores() {
        return creadores;
    }

    /**
     * Obtiene el conjunto de voluntarios registrados.
     * @return conjunto de objetos Voluntario
     */
    public HashSet<Voluntario> getVoluntarios() {
        return voluntarios;
    }

    /**
     * Añade un usuario a la lista correspondiente según su tipo (Creador o Voluntario).
     * @param nuevoUsuario Usuario a añadir.
     * @throws UsuarioYaExiste Si el correo ya está registrado.
     */
    @Override
    public void add(Usuario nuevoUsuario) throws UsuarioYaExiste {
        if (nuevoUsuario instanceof Creador) {
            if (!creadores.add((Creador) nuevoUsuario)) {
                throw new UsuarioYaExiste("El correo introducido ya está registrado como creador.");
            }
        }else{
            if (!voluntarios.add((Voluntario) nuevoUsuario)) {
                throw new UsuarioYaExiste("El correo introducido ya está registrado como voluntario.");
            }
        }
    }

    /**
     * Elimina un usuario de la lista de usuarios.
     * @param usuario Usuario a eliminar.
     * @throws UsuarioNoExiste Si el usuario no existe en la lista.
     */
    @Override
    public void remove(Usuario usuario) throws UsuarioNoExiste {
        if (usuario instanceof Creador) {
            creadores.remove((Creador) usuario);
        } else {
            voluntarios.remove((Voluntario) usuario);
        }
    }

    /**
     * Actualiza los datos de un usuario cambiando su nombre y contraseña.
     * @param usuario Usuario a actualizar.
     */
    public void update(Usuario usuario){
        String nuevoNombre = Utilidades.pideString("Introduce el nuevo nombre");
        usuario.setNombre(nuevoNombre); // Actualizamos el nombre
        String nuevaContrasena = UsuariosView.pidePassword();
        usuario.setPassword(nuevaContrasena); // Actualizamos la contraseña
    }

    /**
     * Muestra la información de un usuario llamando a su método toString().
     * @param usuario Usuario del que se mostrará la información.
     */
    @Override
    public void mostrar(Usuario usuario) {
        UsuariosView.mostrarMensaje(usuario.toString());
    }

    /**
     * Busca un creador a través de su correo electrónico.
     * @param correo Correo del creador a buscar.
     * @return Objeto Creador si se encuentra, null en caso contrario.
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

    /**
     * Busca un voluntario a través de su correo electrónico.
     * @param correo Correo del voluntario a buscar.
     * @return Objeto Voluntario si se encuentra, null en caso contrario.
     */
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
     * Valida el inicio de sesión de un creador.
     * @param correo Correo del creador.
     * @param password Contraseña del creador.
     * @return Objeto Usuario si las credenciales son correctas, null en caso contrario.
     */
    public Usuario validarLoginCreador(String correo, String password){
        Creador usuarioValidado = encontrarCreador(correo);
        if (usuarioValidado != null && usuarioValidado.verificarPassword(password)) {
            return usuarioValidado;
        }
        return null;
    }

    /**
     * Valida el inicio de sesión de un voluntario.
     * @param correo Correo del voluntario.
     * @param password Contraseña del voluntario.
     * @return Objeto Usuario si las credenciales son correctas, null en caso contrario.
     */
    public Usuario validarLoginVoluntario(String correo, String password){
        Voluntario usuarioValidado = encontrarVoluntario(correo);
        if (usuarioValidado != null && usuarioValidado.verificarPassword(password)) {
            return usuarioValidado;
        }
        return null;
    }

    /**
     * Establece la lista de actividades disponibles.
     * @param actividadesDisponibles Lista de actividades.
     */
    public void setActividadesDisponibles(List<Actividad> actividadesDisponibles) {
        this.actividadesDisponibles = actividadesDisponibles;
    }

    /**
     * Obtiene la lista de productos disponibles.
     * @return Lista de productos.
     */
    public List<Producto> getProductosDisponibles() {
        return productosDisponibles;
    }

    /**
     * Establece la lista de productos disponibles.
     * @param productosDisponibles Lista de productos.
     */
    public void setProductosDisponibles(List<Producto> productosDisponibles) {
        this.productosDisponibles = productosDisponibles;
    }

    /**
     * Obtiene la lista de recompensas disponibles.
     * @return Lista de recompensas.
     */
    public List<Recompensa> getRecompensasDisponibles() {
        return recompensasDisponibles;
    }
}
