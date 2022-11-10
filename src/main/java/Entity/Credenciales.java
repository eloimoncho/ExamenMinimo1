package Entity;
import java.util.*;
import Entity.*;
import Main.*;
import Manager.*;

public class Credenciales {

    //Atributos

    String email;
    String password;


    //Constructores

    public Credenciales(){}
    public Credenciales(String email, String password) {
        this.email = email;
        this.password = password;
    }

    //Getters y Setters
    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean isEqual(Credenciales credenciales){
        return (Objects.equals(this.email, credenciales.getEmail()) && Objects.equals(this.password, credenciales.getPassword()));
    }
}
