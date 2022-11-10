package Entity;
import java.util.*;

public class Usuario {

    // Atributos


    String usuarioID;
    String fecha;

    int puntos;
    int nivelUsuario;
    List<Partidas> listaPartidasUsuario;

    String jugandoPartida; //0 el usuario no está jugando una partida, otro num es el ID de la partida

    //Constructores

    public Usuario(String usuarioID, String fecha) {
        this.usuarioID = usuarioID;
        this.fecha = fecha;
        this.puntos = 50;
        this.listaPartidasUsuario=new LinkedList<>();
        this.nivelUsuario=1;
    }


    //Getters y Setters


    public String getUsuarioID() {
        return usuarioID;
    }

    public String getFecha() {
        return fecha;
    }

    public int getPuntos() {
        return puntos;
    }

    public String getJugandoPartida() {
        return jugandoPartida;
    }

    public int getNivelUsuario() {
        return nivelUsuario;
    }

    public void setUsuarioID(String usuarioID) {
        this.usuarioID = usuarioID;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    public void setJugandoPartida(String jugandoPartida) {
        this.jugandoPartida = jugandoPartida;
    }

    public List<Partidas> getListaPartidas() {
        return listaPartidasUsuario;
    }
}
