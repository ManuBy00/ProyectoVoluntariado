import Controller.UsuariosController;
import DataAccess.XML;
import Exceptions.UsuarioNoExiste;
import Model.Creador;
import Model.ListaIniciativas;
import Model.ListaUsuarios;
import View.UsuariosView;

import java.io.File;

import static DataAccess.XML.readXML;

public class main {
    public static void main(String[] args) {


        //CARGAR XML USUARIOS
        File fileUsuarios = new File("usuarios.xml");
        if (!fileUsuarios.exists()) {
            // El archivo no existe, así que lo creamos
            ListaUsuarios listaUsuarios = ListaUsuarios.getInstance();
            XML.writeXML(listaUsuarios, "usuarios.xml");
            System.out.println("El archivo usuarios.xml no existía, se ha creado.");
        } else {
            // El archivo ya existe, lo puedes cargar
            ListaUsuarios listaUsuarios = ListaUsuarios.getInstance();
            listaUsuarios.cargarUsuariosDesdeXML("usuarios.xml");
        }



        //CARGAR XML INICIATIVAS
        File fileIniciativas = new File("iniciativas.xml");
        if (!fileIniciativas.exists()) {
            // El archivo no existe, así que lo creamos
            ListaIniciativas listaIniciativas = ListaIniciativas.getInstance();
            XML.writeXML(listaIniciativas, "iniciativas.xml");
            System.out.println("El archivo iniciativas.xml no existía, se ha creado.");
        } else {
            // El archivo ya existe, lo puedes cargar
            ListaIniciativas listaIniciativas = ListaIniciativas.getInstance();
            listaIniciativas.cargarIniciativasDesdeXML("iniciativas.xml");
        }



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


