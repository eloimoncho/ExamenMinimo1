import java.util.HashMap;
import java.util.List;
import java.util.Queue;
import Entity.*;
import Entity.Object;

public class ObjectManagerImpl implements ObjectManager {

    List<Object> listProducts;
    HashMap<String, User> users;

    @Override
    public List<Object> objectsByPrice() {
        return null;
    }

    @Override
    public void addOrder(Order order) {

    }


    @Override
    public void addUser(int userID, String name, String surname, String date, String email, String password, int coins) {
        
    }

    @Override
    public void addProduct(String productId, String name, double price) {

    }

    @Override
    public Product getProduct(String productId) {
        return null;
    }

    @Override
    public int numUsers() {
        return 0;
    }

    @Override
    public int numProducts() {
        return 0;
    }

    @Override
    public int numOrders() {
        return 0;
    }

    @Override
    public int numSales(String b001) {
        return 0;
    }
}

