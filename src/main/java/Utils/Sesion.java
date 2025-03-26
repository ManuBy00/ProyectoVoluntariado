package Utils;

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
        this.userLoged = null;
    }
}
