import Controller.UsuariosController;
import DataAccess.XML;
import Exceptions.UsuarioNoExiste;
import Exceptions.UsuarioYaExiste;
import Model.*;
import View.UsuariosView;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Test {
    public static void main(String[] args) {





        Creador pepe = new Creador("pepe", "12345678", "pepe@gmail.com", "ong");
        ListaUsuarios.getInstance().add(pepe);
        XML.writeXML(ListaUsuarios.getInstance(), "usuarios.xml");



        UsuariosController lController = new UsuariosController(ListaUsuarios.getInstance());
        int opcion;
        do {
            // Mostrar el menú y pedir una opción
            opcion = UsuariosView.mostrarMenuInicial();

            switch (opcion) {
                case 1:  // Iniciar sesión como creador
                    try {
                        lController.iniciarSesionCreador();
                    } catch (UsuarioNoExiste e) {
                        UsuariosView.mostrarMensaje(e.getMessage());
                    }
                    break;

                case 2:  // Iniciar sesión como voluntario
                    try {
                        lController.iniciarSesionVoluntario();
                    } catch (UsuarioNoExiste e) {
                        UsuariosView.mostrarMensaje(e.getMessage());
                    }
                    break;

                case 3:  // Registrar un nuevo usuario
                    lController.registrarUsuario();
                    break;

                case 4:  // Salir del programa
                    UsuariosView.mostrarMensaje("Saliendo...");
                    break;

                default:  // Opción no válida
                    UsuariosView.mostrarMensaje("Opción no válida");
            }
        } while (opcion != 4);
    }
}
