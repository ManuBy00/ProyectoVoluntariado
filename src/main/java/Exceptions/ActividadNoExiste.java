package Exceptions;

public class ActividadNoExiste extends RuntimeException {
    public ActividadNoExiste(String message) {
        super(message);
    }
}
