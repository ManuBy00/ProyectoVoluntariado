package Exceptions;

public class UsuarioYaExiste extends RuntimeException {
    public UsuarioYaExiste(String message) {
        super(message);
    }
}
