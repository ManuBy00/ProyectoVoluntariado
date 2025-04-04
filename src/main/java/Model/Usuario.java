package Model;
import Utils.Seguridad;
import org.mindrot.jbcrypt.BCrypt;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import java.util.Objects;
import static View.IniciativaView.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlSeeAlso({Creador.class, Voluntario.class})  // Le decimos a JAXB que considere estas clases también
public abstract class Usuario {
    @XmlElement
    private String nombre;
    @XmlElement
    private String passwordHash;
    @XmlElement
    private String correo;

    // Controller full equip
    public Usuario(String nombre, String password, String correo) {
        this.nombre = nombre;
        this.passwordHash = Seguridad.hashPassword(password);
        this.correo = correo;
    }

    public Usuario() {
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
        return ANSI_BLUE + "\n=== Información de Usuario ===" + ANSI_RESET +
                ANSI_CYAN + "\n- Nombre de usuario: " + ANSI_RESET + nombre +
                ANSI_GREEN + "\n- Correo: " + ANSI_RESET + correo +
                ANSI_BLUE + "\n==============================" + ANSI_RESET;
    }

    // Implementamos Equals y HashCode


    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Usuario usuario)) return false;
        return Objects.equals(correo, usuario.correo);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(correo);
    }

}
