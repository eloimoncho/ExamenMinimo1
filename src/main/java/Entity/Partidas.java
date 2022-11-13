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

    List<Actividad> listaActividad;

    //Constructores

    public Partidas(){}

    public Partidas(int usuarioID, int juegoID) {
        this.usuarioID = usuarioID;
        this.juegoID = juegoID;
        this.fecha = "10/11/2022"; //fecha actual
        this.nivelActual=1;
        this.puntos=50;
        this.activo=true;
        this.listaActividad=new ArrayList<>();
        Actividad a= new Actividad(this.nivelActual,this.puntos,this.fecha);
        this.listaActividad.add(a);
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

    public List<Actividad> getListaActividad() {
        return listaActividad;
    }

    public void setListaActividad(List<Actividad> listaActividad) {
        this.listaActividad = listaActividad;
    }

    public void addActividad(Actividad actividad){
        this.listaActividad.add(actividad);
    }
}
