package Manager;

import java.util.List;
import Entity.*;

public interface ObjetoManager {
    public int registerUser(String name, String surname, String birthDate, String email, String password);
    public List<User> usersByAlphabet();
    public List<ObjetoTienda> objectByPrice();
    public int loginUser(String email, String password);
    public void addObject(String objectID,String name, String description, double coins);
    public int buyObjectByUser(String objectID, String userID);
    public List<ObjetoTienda> objectBoughtByUser(String userID);
    public int numObjetos();
    public int numUsuarios();




}
