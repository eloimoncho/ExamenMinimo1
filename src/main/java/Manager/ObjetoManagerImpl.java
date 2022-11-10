package Manager;

import Entity.*;
import java.util.*;
import org.apache.log4j.Logger;

public class ObjetoManagerImpl implements ObjetoManager {

    List<ObjetoTienda> listObjetos;
    HashMap<String, User> users;
    private static ObjetoManager instance;
    final static Logger logger = Logger.getLogger(ObjetoManagerImpl.class);

    public static ObjetoManager getInstance() {
        if (instance==null) instance = new ObjetoManagerImpl();
        return instance;
    }
    public int size() {
        int ret = this.listObjetos.size();
        logger.info("size " + ret);

        return ret;
    }

    //Constructores

    public ObjetoManagerImpl() {
        this.listObjetos = new ArrayList<>();
        this.users = new HashMap<>();
    }

    //Funciones de implementación
    @Override
    public int numObjetos() {
        return this.listObjetos.size();
    }

    @Override
    public int numUsuarios() {
        return this.users.size();
    }

    //Funciones del ObjetoManager
    @Override
    public int registerUser(String name, String surname, String birthDate, String email, String password) {
        String ID = Integer.toString(this.users.size());
        User U = new User(ID, name, surname, birthDate, new Credenciales(email, password));
        // Búsqueda en el Hashmap de "usuarios" para encontrar si ya hay algún usuario con el mismo email.
        // "0" se puede, "1" ya hay un usuario con ese mail.
        int verificador = 0;
        int numUsers = this.users.size();
        for (int i=0; i<numUsers; i++){
            String idHashmap = Integer.toString(i);
            if (Objects.equals(this.users.get(idHashmap).getCredenciales().getEmail(), email)){
                verificador = 1;
            }
        }
        if (verificador==0){
            this.users.put(ID, U);
        }
        return verificador;
    }

    @Override
    public List<User> usersByAlphabet() {
        List<User> aux= new ArrayList<>(this.users.values());
        aux.sort((User u1, User u2)->{
            int aux1 = String.CASE_INSENSITIVE_ORDER.compare(u1.getUserSurname(), u2.getUserSurname());
            if (aux1==0){
                aux1 = String.CASE_INSENSITIVE_ORDER.compare(u1.getUserName(), u2.getUserName());
            }
            return aux1;
        });
        return aux;
    }
    @Override
    public int loginUser(String email, String password) {
        int loginPossible=1;
        // Búsqueda en el Hashmap de credenciales por si hay alguna que coincide con las nuestras.
        // "0" se puede, "1" ya hay un usuario con ese mail.
        int numUsers = this.users.size();
        for (int i=0; i<numUsers; i++){
            String idHashmap = Integer.toString(i);
            if (Objects.equals(this.users.get(idHashmap).getCredenciales().getEmail(), email) && Objects.equals(this.users.get(idHashmap).getCredenciales().getPassword(),password)){
                loginPossible = 0;
            }
        }
        return loginPossible;
    }
    @Override
    public List<ObjetoTienda> objectByPrice() {
        this.listObjetos.sort((ObjetoTienda o2, ObjetoTienda o1)->Double.compare(o1.getObjectCoins(), o2.getObjectCoins()));
        return this.listObjetos;
    }



    @Override
    public void addObject(String objectID, String name, String description, double coins) {
        ObjetoTienda objeto = new ObjetoTienda(objectID, name, description, coins);
        this.listObjetos.add(objeto);
    }

    @Override
    public int buyObjectByUser(String objectID, String userID) {
        int verificador = 0;
        // Verificamos que exista el usuario.
        int numeroUsuarios = this.users.size(); // Size = 3 es que hay ID = 0,1,2.
        if ((0 <= Integer.parseInt(userID))&&(Integer.parseInt(userID) < numeroUsuarios)) {  // Existe.
            // Quiere decir que el usuario existe. Seguimos.
            // Localizamos el objeto y verificamos que el usuario tiene saldo suficiente para comprarlo.
            int numObj = this.listObjetos.size();
            for (int i=0; i<numObj; i++) {
                // Localizamos nuestro objeto.
                if (Objects.equals(this.listObjetos.get(i).getObjectID(), objectID)) {
                    if (this.listObjetos.get(i).getObjectCoins() <= this.users.get(userID).getCoins()) {
                        // Quiere decir que el usuario lo puede comprar.
                        // Pasamos ya a hacer la compra (es decir, descontar el precio del sueldo del usuario y añadir el objeto a su lista).
                        this.users.get(userID).addObjetoComprado(this.listObjetos.get(i));
                        this.users.get(userID).descontarDinero(this.listObjetos .get(i).getObjectCoins());
                    }
                    else {
                        // Quiere decir que el saldo del usuario no es suficiente, por lo tanto no se puede comprar.
                        verificador = 2;
                    }
                }
            }
        }
        else {
            // Quiere decir que el usuario no existe. Paramos.
            verificador = 1;
        }
        return verificador;
    }


    @Override
    public List<ObjetoTienda> objectBoughtByUser(String userID) {
        return users.get(userID).getObjectsUser();
    }

}

