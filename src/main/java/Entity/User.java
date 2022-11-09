package Entity;
import java.util.*;

public class User {

    // Atributos

    String userID;
    String userName;
    String userSurname;
    String date;
    Credenciales credenciales;
    double coins;
    List<ObjetoTienda> objectsUser;

    //Constructores

    public User(){}
    public User(String userID, String userName, String userSurname, String date, Credenciales credenciales) {
        this.userID = userID;
        this.userName = userName;
        this.userSurname = userSurname;
        this.date = date;
        this.credenciales = credenciales;
        this.coins=50;
    }



    //Getters y Setters


    public String getUserID() {
        return userID;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserSurname() {
        return userSurname;
    }

    public String getDate() {
        return date;
    }

    public Credenciales getCredenciales() {
        return credenciales;
    }

    public double getCoins() {
        return coins;
    }

    public List<ObjetoTienda> getObjectsUser() {
        return objectsUser;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUserSurname(String userSurname) {
        this.userSurname = userSurname;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setCredenciales(Credenciales credenciales) {
        this.credenciales = credenciales;
    }

    public void setCoins(double coins) {
        this.coins = coins;
    }

    public void setObjectsUser(List<ObjetoTienda> objectsUser) {
        this.objectsUser = objectsUser;
    }

    public void descontarDinero(double dinero){
        this.coins = this.coins - dinero;
    }

    public void addObjetoComprado(ObjetoTienda obj){
        this.objectsUser.add(obj);
    }

}
