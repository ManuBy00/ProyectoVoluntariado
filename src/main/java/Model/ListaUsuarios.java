package Model;

import DataAccess.XML;
import Exceptions.UsuarioNoExiste;
import Exceptions.UsuarioYaExiste;
import Utils.Utilidades;
import View.UsuariosView;

import javax.xml.bind.annotation.*;
import java.util.HashSet;

@XmlRootElement(name = "Usuarios")
@XmlAccessorType(XmlAccessType.FIELD)
public class ListaUsuarios implements CRUD<Usuario> {
    @XmlElementWrapper(name = "creadores")
    @XmlElement(name = "creador")
    private HashSet<Creador> creadores;

    @XmlElementWrapper(name = "voluntarios")
    @XmlElement(name = "voluntario")
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
     * Obtiene la instancia única de la lista de usuarios (Singleton).
     *
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
     *
     * @return conjunto de objetos Creador
     */
    public HashSet<Creador> getCreadores() {
        return creadores;
    }

    /**
     * Obtiene el conjunto de voluntarios registrados.
     *
     * @return conjunto de objetos Voluntario
     */
    public HashSet<Voluntario> getVoluntarios() {
        return voluntarios;
    }

    /**
     * Añade un usuario a la lista correspondiente según su tipo (Creador o Voluntario).
     *
     * @param nuevoUsuario Usuario a añadir.
     * @throws UsuarioYaExiste Si el correo ya está registrado.
     */
    @Override
    public void add(Usuario nuevoUsuario) throws UsuarioYaExiste {
        if (nuevoUsuario instanceof Creador) {
            if (!creadores.add((Creador) nuevoUsuario)) {
                throw new UsuarioYaExiste("El correo introducido ya está registrado como creador.");
            }
        } else {
            if (!voluntarios.add((Voluntario) nuevoUsuario)) {
                throw new UsuarioYaExiste("El correo introducido ya está registrado como voluntario.");
            }
        }
    }

    /**
     * Elimina un usuario de la lista de usuarios.
     *
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
     *
     * @param usuario Usuario a actualizar.
     */
    public void update(Usuario usuario) {
        String nuevoNombre = Utilidades.pideString("Introduce el nuevo nombre");
        usuario.setNombre(nuevoNombre); // Actualizamos el nombre
        String nuevaContrasena = UsuariosView.pidePassword();
        usuario.setPassword(nuevaContrasena); // Actualizamos la contraseña
    }

    /**
     * Muestra la información de un usuario llamando a su método toString().
     *
     * @param usuario Usuario del que se mostrará la información.
     */
    @Override
    public void mostrar(Usuario usuario) {
        UsuariosView.mostrarMensaje(usuario.toString());
    }

    /**
     * Busca un creador a través de su correo electrónico.
     *
     * @param correo Correo del creador a buscar.
     * @return Objeto Creador si se encuentra, null en caso contrario.
     */
    public Creador encontrarCreador(String correo) {
        Creador usuarioEncontrado = null;
        for (Creador c : creadores) {
            if (correo.equals(c.getCorreo())) {
                usuarioEncontrado = c;
            }
        }
        return usuarioEncontrado;
    }

    /**
     * Busca un voluntario a través de su correo electrónico.
     *
     * @param correo Correo del voluntario a buscar.
     * @return Objeto Voluntario si se encuentra, null en caso contrario.
     */
    public Voluntario encontrarVoluntario(String correo) {
        Voluntario usuarioEncontrado = null;
        for (Voluntario v : voluntarios) {
            if (correo.equals(v.getCorreo())) {
                usuarioEncontrado = v;
            }
        }
        return usuarioEncontrado;
    }

    /**
     * Valida el inicio de sesión de un creador.
     *
     * @param correo   Correo del creador.
     * @param password Contraseña del creador.
     * @return Objeto Usuario si las credenciales son correctas, null en caso contrario.
     */
    public Usuario validarLoginCreador(String correo, String password) {
        Creador usuarioValidado = encontrarCreador(correo);
        if (usuarioValidado != null && usuarioValidado.verificarPassword(password)) {
            return usuarioValidado;
        }
        return null;
    }

    /**
     * Valida el inicio de sesión de un voluntario.
     *
     * @param correo   Correo del voluntario.
     * @param password Contraseña del voluntario.
     * @return Objeto Usuario si las credenciales son correctas, null en caso contrario.
     */
    public Usuario validarLoginVoluntario(String correo, String password) {
        Voluntario usuarioValidado = encontrarVoluntario(correo);
        if (usuarioValidado != null && usuarioValidado.verificarPassword(password)) {
            return usuarioValidado;
        }
        return null;
    }

    public void cargarUsuariosDesdeXML(String filename) {
        ListaUsuarios listaUsuarios = XML.readXML(ListaUsuarios.class, filename);
        if (listaUsuarios != null) {
            // Asegúrate de que no esté vacío antes de asignar
            if (listaUsuarios.getCreadores() != null) {
                this.creadores = listaUsuarios.getCreadores();
            }
            if (listaUsuarios.getVoluntarios() != null) {
                this.voluntarios = listaUsuarios.getVoluntarios();
            }
        }
    }
}
