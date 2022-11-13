package Entity;
import java.util.*;

public class Actividad {

    //Atributos
    int nivel;
    int puntos;
    String fecha;

    //Constructor

    public Actividad (){}

    public Actividad(int nivel, int puntos, String fecha) {
        this.nivel = nivel;
        this.puntos = puntos;
        this.fecha = fecha;
    }

    //Getters y Setters


    public int getNivel() {
        return nivel;
    }

    public int getPuntos() {
        return puntos;
    }

    public String getFecha() {
        return fecha;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}
