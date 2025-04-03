package Model;

public enum EstadoActividad {

    // vamos a hacerlo usando variable y constructor

    PENDIENTE("Pendiente"),
    EN_PROGRESO("En Progreso"),
    FINALIZADA("Finalizada"),
    CANCELADA("Cancelada");

    private final String estado;

    EstadoActividad(String estado) {
        this.estado = estado;
    }

    public String getEstado() {
        return estado;
    }
}
