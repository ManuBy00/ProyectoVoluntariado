package Controller;
import Model.Iniciativa;
import static View.IniciativaView.mostrarMenuIniciativa;
import static View.IniciativaView.pedirDatosIniciativa;

public class IniciativaController {




    // Añadir actividad a una iniciativa existente
    public void agregarActividad() {

    }

    public void actualizarActividad() {

    }

    public void eliminarActividad() {
    }

    public void mostrarIniciativa() {

    }


    public void menuIniciativa(){
        int opcion = mostrarMenuIniciativa();
        Iniciativa iniciativa = pedirDatosIniciativa();
        do {
            switch (opcion) {
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
            }
        }while (opcion!=4);
    }

}
