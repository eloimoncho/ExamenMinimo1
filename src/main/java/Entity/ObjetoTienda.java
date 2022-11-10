package Entity;

import java.util.*;
public class ObjetoTienda {

    // Atributos
    String objectID;
    String objectName;
    String objectDescription;
    double objectCoins;

    //Constructores

    public ObjetoTienda(){}
    public ObjetoTienda(String ID, String objectName, String objectDescription, double objectCoins) {
        this.objectID = objectID;
        this.objectName = objectName;
        this.objectDescription = objectDescription;
        this.objectCoins = objectCoins;
    }

    //Getters y Setters


    public String getObjectID() {
        return objectID;
    }

    public String getObjectName() {
        return objectName;
    }

    public String getObjectDescription() {
        return objectDescription;
    }

    public double getObjectCoins() {
        return objectCoins;
    }

    public void setObjectID(String objectID) {
        this.objectID = objectID;
    }

    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }

    public void setObjectDescription(String objectDescription) {
        this.objectDescription = objectDescription;
    }

    public void setObjectCoins(int objectCoins) {
        this.objectCoins = objectCoins;
    }
}
