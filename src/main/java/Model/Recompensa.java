package Model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "recompensa")
@XmlAccessorType(XmlAccessType.FIELD)
public class Recompensa {
    @XmlElement
    private String nombre;
    @XmlElement
    private int costo;

    public Recompensa(String nombre, int costo) {
        this.nombre = nombre;
        this.costo = costo;
    }

    public Recompensa(){

    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCosto() {
        return costo;
    }


    @Override
    public String toString() {
        return nombre + " - " + costo + " puntos";
    }
}