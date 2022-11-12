package Entity;
import java.util.*;

public class Juego {

    // Atributos
    int juegoID;
    String descripcion;
    int niveles;
    List<Juego> listaJuegos;

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

    public List<Juego> getListaJuegos() {
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

    public void setListaJuegos(List<Juego> listaJuegos) {
        this.listaJuegos = listaJuegos;
    }

    public void anadirJuego(Juego juego){
        this.listaJuegos.add(juego);
    }
    public Juego getJuego(int juegoID){
        int numJuegos=this.listaJuegos.size();
        for (int i=0; i<numJuegos; i++){
            if (this.listaJuegos.get(i).juegoID==juegoID){
                return this.listaJuegos.get(i);
            }
        }
        Juego noExiste= new Juego(-1,"noExiste",0);
        return noExiste;
    }
}
