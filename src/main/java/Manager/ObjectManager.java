import java.util.List;
import Entity.*;
import Entity.Object;

public interface ObjectManager {
    public List<Object> objectByPrice();
    public List<User> usersByAlphabet();
    public void addUser(int userID, String name, String surname, String date, String email, String password, int coins);
    public void addObject(String name, String description, int coins);
    public Object getObject(String objectName, int coins);

    public User login(String email, String password);

    public int numUsers();
    public int numProducts();

    public int numOrders();

}
