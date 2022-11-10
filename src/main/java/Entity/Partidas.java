package Entity;

import java.util.*;
public class Partidas {

    // Atributos
    String partidaID;

    String partidaDescripcion;

    int niveles;

    List<Usuario> listaUsuarios;

    //Constructores

    public Partidas(){}

    public Partidas(String partidaID, String partidaDescripcion, int niveles) {
        this.partidaID = partidaID;
        this.partidaDescripcion = partidaDescripcion;
        this.niveles = niveles;
    }

    //Getters y Setters

    public String getPartidaID() {
        return partidaID;
    }

    public String getPartidaDescripcion() {
        return partidaDescripcion;
    }

    public int getNiveles() {
        return niveles;
    }

    public void setPartidaID(String partidaID) {
        this.partidaID = partidaID;
    }

    public void setPartidaDescripcion(String partidaDescripcion) {
        this.partidaDescripcion = partidaDescripcion;
    }

    public void setNiveles(int niveles) {
        this.niveles = niveles;
    }

    public List<Usuario> getListaUsuarios() {
        return listaUsuarios;
    }
}
