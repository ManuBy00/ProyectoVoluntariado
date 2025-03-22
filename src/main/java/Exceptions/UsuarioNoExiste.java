package Exceptions;

public class UsuarioNoExiste extends RuntimeException {
    public UsuarioNoExiste(String message) {
        super(message);
    }
}
