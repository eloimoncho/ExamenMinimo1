package Entity;
import java.util.*;
import Entity.*;
import Main.*;
import Manager.*;

public class Usuarios {

    //Atributos

    int usuarioID;
    String nombre;

    List<Usuarios> listaUsuarios;


    //Constructores
    public Usuarios(){}

    public Usuarios(int usuarioID, String nombre) {
        this.usuarioID = usuarioID;
        this.nombre = nombre;
    }

    //Getters y Setters

    public int getUsuarioID() {
        return usuarioID;
    }

    public String getNombre() {
        return nombre;
    }

    public List<Usuarios> getListaUsuarios() {
        return listaUsuarios;
    }

    public void setUsuarioID(int usuarioID) {
        this.usuarioID = usuarioID;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setListaUsuarios(List<Usuarios> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }
}
