package Entity;
import java.util.*;

public class Juegos {

    // Atributos
    int juegoID;

    String descripcion;

    int niveles;

    List<Juegos> listaJuegos;

    //Constructor
    public Juegos(){}
    public Juegos(int juegoID, String descripcion, int niveles) {
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

    public List<Juegos> getListaJuegos() {
        return listaJuegos;
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

    public void setListaJuegos(List<Juegos> listaJuegos) {
        this.listaJuegos = listaJuegos;
    }
}
