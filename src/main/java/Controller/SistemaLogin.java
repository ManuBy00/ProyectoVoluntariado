package Controller;

import Model.Creador;
import Model.Usuario;
import Model.Voluntario;
import View.VoluntariadoView;

import java.util.ArrayList;

public class SistemaLogin {
    private ArrayList<Usuario> usuarios;

    public void registrarUsuario(){
        //pedimos tipo de usuario
        int tipoUsuario = View.VoluntariadoView.elegirTipoUsuario();
        //creamos el usuario
        Usuario nuevo = View.VoluntariadoView.crearUsuario(tipoUsuario);

        //comprobamos si el usuario existe
        boolean usuarioExiste = false;
        for (Usuario u : usuarios){
            if (u.getCorreo().equals(nuevo.getCorreo())){
                usuarioExiste = true;
                View.VoluntariadoView.mostrarMensaje("Ya existe un usuario con el correo especificado.");
            }else {
                VoluntariadoView.mostrarMensaje("Usuario registrado");
                usuarios.add(nuevo);
            }
        }
    }

    private static void iniciarSesion(){

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
}

