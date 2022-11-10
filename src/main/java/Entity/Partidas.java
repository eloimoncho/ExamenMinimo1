package Entity;

import java.util.*;
public class Partidas {

    // Atributos
    String partidaID;

    String partidaDescripcion;

    int niveles;

    List<Usuario> listaUsuarios;

    int estadoPartida; //0 se está jugando la partida, 1 se ha finalizado la partida

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

    public int getEstadoPartida() {
        return estadoPartida;
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

    public void setEstadoPartida(int estadoPartida) {
        this.estadoPartida = estadoPartida;
    }

    public List<Usuario> getListaUsuarios() {
        return listaUsuarios;
    }

}
