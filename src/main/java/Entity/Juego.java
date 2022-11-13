package Entity;
import java.util.*;

public class Juego {

    // Atributos
    int juegoID;
    String descripcion;
    int niveles;

    //Constructor
    public Juego(){}
    public Juego(int juegoID, String descripcion, int niveles) {
        this.juegoID = juegoID;
        this.descripcion = descripcion;
        this.niveles = niveles;
    }

    //Getters y Setters
    public int getJuegoID() {
        return juegoID;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public int getNiveles() {
        return niveles;
    }

    public void setJuegoID(int juegoID) {
        this.juegoID = juegoID;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setNiveles(int niveles) {
        this.niveles = niveles;
    }


}
