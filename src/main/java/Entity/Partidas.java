package Entity;

import java.util.*;
public class Partidas {

    // Atributos
    int usuarioID;
    int juegoID;
    String fecha;
    int nivelActual;
    int puntos;
    boolean activo;

    List<Partidas> listaPartidas;




    //Constructores

    public Partidas(){}

    public Partidas(int usuarioID, int juegoID) {
        this.usuarioID = usuarioID;
        this.juegoID = juegoID;
        this.fecha = "dd/mm/year"; //fecha actual
        this.nivelActual=1;
        this.puntos=50;
        this.activo=true;
    }
    //Getters y Setters
    public int getUsuarioID() {
        return usuarioID;
    }

    public int getJuegoID() {
        return juegoID;
    }

    public String getFecha() {
        return fecha;
    }

    public int getNivelActual() {
        return nivelActual;
    }

    public int getPuntos() {
        return puntos;
    }

    public boolean isActivo() {
        return activo;
    }

    public List<Partidas> getListaPartidas() {
        return listaPartidas;
    }

    public void setUsuarioID(int usuarioID) {
        this.usuarioID = usuarioID;
    }

    public void setJuegoID(int juegoID) {
        this.juegoID = juegoID;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public void setNivelActual(int nivelActual) {
        this.nivelActual = nivelActual;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public void setListaPartidas(List<Partidas> listaPartidas) {
        this.listaPartidas = listaPartidas;
    }

    public void anadirPartida(Partidas partida){
        this.listaPartidas.add(partida);
    }
    public Partidas getPartida(int usuarioID){
        int numPartidas = this.listaPartidas.size();
        for (int i=0; i<numPartidas; i++){
            if (this.listaPartidas.get(i).getUsuarioID() == usuarioID){ //Encontramos el usuario
                return this.listaPartidas.get(i);
            }
        }
        Partidas noExiste = new Partidas(-1,-1);
        return noExiste;
    }
}
