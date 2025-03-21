package Model;

import Exceptions.UsuarioNoExiste;
import Exceptions.UsuarioYaExiste;

import java.util.ArrayList;
import java.util.HashSet;

public class ListaUsuarios implements CRUD<Usuario, String>{
    private HashSet<Usuario> usuarios;

    public ListaUsuarios() {
        this.usuarios = new HashSet<>();
    }

    public HashSet<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(HashSet<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    @Override
    public boolean add(Usuario nuevoUsuario) throws UsuarioYaExiste {
        if (!usuarios.add(nuevoUsuario)) {
            throw new UsuarioYaExiste("El correo introducido ya está registrado.");
        }
        return true;
    }

    @Override
    public boolean update(Usuario usuarioModificado) {
    return true;
    }

    @Override
    public boolean remove(Usuario usuario) throws UsuarioNoExiste {
        if (!usuarios.remove(usuario)){
            throw new UsuarioNoExiste("Error, el usuario introducido no existe.");
        }
        return true;
    }

    @Override
    public String mostrar(Usuario usuario) {
        return usuario.toString();
    }

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

    public Usuario validarUsuario(String correo, String password){
        Usuario usuarioValidado = null;
        for (Usuario u : usuarios){
            if (correo.equals(u.getCorreo()) && password.equals(u.getPassword())){
                usuarioValidado = u;
            }
        }
        return usuarioValidado;
    }
}
