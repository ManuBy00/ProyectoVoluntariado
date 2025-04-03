package Utils;

import DataAccess.XML;
import Model.ListaIniciativas;
import Model.ListaUsuarios;
import Model.Usuario;

public class Sesion {
    private static Sesion instance;
    private Usuario userLoged;

    // Constructor privado para evitar instancias externas
    private Sesion() {}

    // Metodo para obtener la instancia única
    public static Sesion getInstancia() {
        if (instance == null) {
            instance = new Sesion();
        }
        return instance;
    }

    // Metodo para iniciar sesión
    public void logIn(Usuario user) {
        this.userLoged = user;
    }

    // Metodo para obtener el usuario logueado
    public Usuario getUsuarioIniciado() {
        return userLoged;
    }

    // Metodo para actualizar el usuario logueado
    public void setUsuarioIniciado(Usuario usuarioIniciado) {
        this.userLoged = usuarioIniciado;
    }

    // Metodo para cerrar sesión
    public void logOut() {
        XML.writeXML(ListaUsuarios.getInstance(), "usuarios.xml");
        XML.writeXML(ListaIniciativas.getInstance(), "Iniciativas.xml");
        this.userLoged = null;
    }
}
