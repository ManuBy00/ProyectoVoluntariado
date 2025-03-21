package Model;

import Utils.Utilidades;

import java.util.Objects;

public class Usuario {

    //Declaramos los atributos de usuario
    private String nombre;
    private String password;
    private String correo;


    // Controller full equip
    public Usuario(String nombre, String password, String correo) {
        this.nombre = nombre;
        this.password = password;
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
        return password;
    }

    public void setPassword(String contraseña) {
        this.password = contraseña;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }


    // Implementamos el metodo crearPassword
    public String crearPassword(String password) {

        if (password.length() < 8) {
            System.out.println("La contraseña debe tener al menos 8 caracteres");
        }else {

            System.out.println("Contraseña creada");
        }

        return password;
    }


    // toString para poder ver los atributos del usuario
    @Override
    public String toString() {
        return " Usuario: " +
                "\n nombre: " + nombre +
                "\n contraseña: " + password +
                "\n correo: " + correo;
    }



    // Implementamos Equals y HashCode
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return Objects.equals(password, usuario.password);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(password);
    }
}
