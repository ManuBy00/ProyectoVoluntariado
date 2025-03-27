package Model;

import Utils.Utilidades;
import org.mindrot.jbcrypt.BCrypt;

import java.util.Objects;

public abstract class Usuario {

    //Declaramos los atributos de usuario
    private String nombre;
    private String passwordHash;
    private String correo;

    // Controller full equip
    public Usuario(String nombre, String password, String correo) {
        this.nombre = nombre;
        this.passwordHash = Seguridad.hashPassword(password);
        this.correo = correo;
    }

    // Creamos getters y setters de los atributos

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPassword() {
        return passwordHash;
    }

    public void setPassword(String passwordPlana) {
        if (passwordPlana == null || passwordPlana.trim().isEmpty()) {
            throw new IllegalArgumentException("La contraseña no puede estar vacía");
        }
        if (passwordPlana.length() < 8) {
            throw new IllegalArgumentException("La contraseña debe tener al menos 8 caracteres");
        }
        this.passwordHash = BCrypt.hashpw(passwordPlana, BCrypt.gensalt());
    }


    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }


    /**
     * Verifica el la contraseña con su respectivo hash
     * @param password
     * @return true si coincide, false si no.
     */
    public boolean verificarPassword(String password){
        return Seguridad.checkPassword(password, this.passwordHash);
    }

    // toString para poder ver los atributos del usuario
    @Override
    public String toString() {
        return
                "\n Nombre de usuario: " + nombre +
                "\n Contraseña: " + passwordHash +
                "\n Correo: " + correo;
    }

    // Implementamos Equals y HashCode

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return Objects.equals(passwordHash, usuario.passwordHash) && Objects.equals(correo, usuario.correo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(passwordHash, correo);
    }
}
