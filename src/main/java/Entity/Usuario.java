package Entity;
import java.util.*;
import Entity.*;
import Main.*;
import Manager.*;

public class Usuario {

    //Atributos

    int usuarioID;
    String nombre;

    List<Usuario> listaUsuarios;


    //Constructores
    public Usuario(){}

    public Usuario(int usuarioID, String nombre) {
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

    public List<Usuario> getListaUsuarios() {
        return listaUsuarios;
    }

    public void setUsuarioID(int usuarioID) {
        this.usuarioID = usuarioID;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setListaUsuarios(List<Usuario> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }

    public Usuario getUsuario(int usuarioID) {
        int numUsers = this.listaUsuarios.size();
        for (int i = 0; i < numUsers; i++) {
            if (this.listaUsuarios.get(i).getUsuarioID() == usuarioID) { //Encontramos el usuario
                return this.listaUsuarios.get(i);
            }
        }
        Usuario noExiste = new Usuario(-1,"noExisto");
        return noExiste;
    }
}
