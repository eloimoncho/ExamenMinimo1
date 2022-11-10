package Entity;
import java.util.*;

public class Usuario {

    // Atributos


    String usuarioID;
    String usarioName;
    String fecha;

    double puntos;

    List<Partidas> listaPartidas;

    //Constructores

    public Usuario(String usuarioID, String usarioName, String fecha) {
        this.usuarioID = usuarioID;
        this.usarioName = usarioName;
        this.fecha = fecha;
        this.puntos = 50;
    }


    //Getters y Setters


    public String getUsuarioID() {
        return usuarioID;
    }

    public String getFecha() {
        return fecha;
    }

    public double getPuntos() {
        return puntos;
    }


    public void setUsuarioID(String usuarioID) {
        this.usuarioID = usuarioID;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public void setPuntos(double puntos) {
        this.puntos = puntos;
    }

    public List<Partidas> getListaPartidas() {
        return listaPartidas;
    }
}
