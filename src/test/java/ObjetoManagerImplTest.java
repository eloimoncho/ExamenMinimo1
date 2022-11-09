import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import Entity.*;
import Manager.*;

import java.util.ArrayList;
import java.util.List;

public class ObjetoManagerImplTest {

    ObjetoManager om;

    @Before
    public void setUp() {
        om = new ObjetoManagerImpl();
        
        //Registrar usuarios
        om.registerUser("Eloi", "Moncho", "28/08/2001","eloi.moncho@etudiantat.upc.edu" ,"28082001" );
        om.registerUser("Victor",  "Fernandez", "13/06/2001","victor.fernanadez@estudiantat.upc.edu", "13062001");
        om.registerUser("Marc",  "Moran", "28/10/2001", "marc.moran@estudiantat.upc.edu", "28102001");

        //Añadir objetos
        om.addObject("APL", "Apolo", "Milkshake thursday", 40);
        om.addObject("RZZ", "Razz", "El Dirty",30);
        om.addObject("CST", "Costa", "Blue Moon", 55);

    }

    @After
    public void tearDown() {
        this.om = null;
    }

    //TEST
    @Test
    public void testRegisterUser() {
        //Usuario nuevo
        Assert.assertEquals(3, this.om.numUsuarios());
        int verificador=this.om.registerUser("Alba","Serra", "29/06/2001", "alba.serra@estudiantat.upc.edu","29062001");
        Assert.assertEquals(4, this.om.numUsuarios());
        Assert.assertEquals(0, verificador);
        //Mail ya registrado
        verificador=this.om.registerUser("Marcus","Morancho", "28/10/2001", "marc.moran@estudiantat.upc.edu","30122001");
        Assert.assertEquals(4, this.om.numUsuarios());
        Assert.assertEquals(1, verificador);

    }

    @Test
    public void testUsersByAlphabet() {
        List<User> usuaris = this.om.usersByAlphabet();

        Assert.assertEquals("Moncho", usuaris.get(0).getUserSurname());
        Assert.assertEquals("Eloi", usuaris.get(0).getUserName());

        Assert.assertEquals("Fernandez", usuaris.get(1).getUserSurname());
        Assert.assertEquals("Victor", usuaris.get(1).getUserName());

        Assert.assertEquals("Moran", usuaris.get(2).getUserSurname());
        Assert.assertEquals("Marc", usuaris.get(2).getUserName());
    }

    @Test
    public void testloginUser(){
        // Login correcto
        int verificador = this.om.loginUser("marcmoran@gmail.com", "28102001");
        Assert.assertEquals(0, verificador);

        // Login incorrecto
        verificador = this.om.loginUser("marcmoran@gmail.com", "12345678");
        Assert.assertEquals(1, verificador);
    }

    @Test
    public void testAddObject(){
        Assert.assertEquals(3,this.om.numObjetos());
        this.om.addObject("OKT","OktoberFest","Cultura cervecera",20);
        Assert.assertEquals(4,this.om.numObjetos());
    }

    @Test
    public void testObjectByPrice() {
        List<ObjetoTienda> products = this.om.objectByPrice();

        Assert.assertEquals("AAA", products.get(0).getObjectID());
        Assert.assertEquals(13, products.get(0).getObjectCoins(), 0);

        Assert.assertEquals("RZZ", products.get(1).getObjectID());
        Assert.assertEquals(10, products.get(1).getObjectCoins(), 0);

        Assert.assertEquals("CST", products.get(2).getObjectID());
        Assert.assertEquals(15, products.get(2).getObjectCoins(), 0);

    }

    @Test
    public void testBuyObjectByUser() {
        //Compra correcta verificador = 0
        int verificador = this.om.buyObjectByUser("APL","1");
        Assert.assertEquals(2,verificador);

        //El usuario no existe verificador 1
        verificador = this.om.buyObjectByUser("APL","8");
        Assert.assertEquals(1,verificador);

        //Saldo insuficiente verificador 2
        verificador = this.om.buyObjectByUser("CST","1");
        Assert.assertEquals(2,verificador);
    }
     @Test
    public void testObjectBoughtByUser(){
        List<ObjetoTienda> objetosComprados = this.om.objectBoughtByUser("1");
        Assert.assertEquals(0,objetosComprados.size());
     }

}
