import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import Entity.*;
import Manager.*;

import java.util.ArrayList;
import java.util.List;

public class PartidasManagerImplTest {

    PartidasManager pm;

    @Before
    public void setUp() {
        pm = new PartidasManagerImpl();
        //Añadir objetos
        this.pm.addUsuario(1,"Alba");
        this.pm.addUsuario(2,"Marc");
        this.pm.addUsuario(3,"Isaac");

        this.pm.crearJuego(1, "Apolo", 2);
        this.pm.crearJuego(2, "Razz", 3);
        this.pm.crearJuego(3, "Costa", 4);

        this.pm.inicioPartidaPorUsuario(1,1);
        this.pm.inicioPartidaPorUsuario(2,2);

    }

    @After
    public void tearDown() {
        this.pm = null;
    }

    //TEST
    @Test
    public void crearJuegoTest() {
        Assert.assertEquals(3, this.pm.numJuegos());
        this.pm.crearJuego(4,"Bar", 3);
        Assert.assertEquals(4, this.pm.numJuegos());
    }
    @Test
    public void inicioPartidaPorUsuarioTest(){
        int verificador = this.pm.inicioPartidaPorUsuario(1,2);
        Assert.assertEquals(0,verificador);
    }
    @Test
    public void nivelActualTest(){
        int miNivel=this.pm.nivelActual(2);
        Assert.assertEquals(1,miNivel);
    }

    @Test
    public void puntuacionActualTest(){
        int puntos=this.pm.puntuacionActual(2);
        Assert.assertEquals(50,puntos);
    }

    @Test
    public void finalizarPartidaTest(){
        int verificador = this.pm.finalizarPartida(2);
        Assert.assertEquals(0,verificador);
        verificador = this.pm.finalizarPartida(5);
        Assert.assertEquals(-1,verificador);

        verificador=this.pm.finalizarPartida(3);
       Assert.assertEquals(-2,verificador);
    }
    @Test
    public void usuariosPorJuegoTest(){
        this.pm.addUsuario(4,"Oriol");
        this.pm.addUsuario(5,"Marc");
        this.pm.inicioPartidaPorUsuario(4,1);
        this.pm.inicioPartidaPorUsuario(5,1);

        List<Usuario> usuarios = this.pm.usuariosPorJuego(1);

        Assert.assertEquals(1,usuarios.get(0).getUsuarioID());
        Assert.assertEquals(4,usuarios.get(1).getUsuarioID());
        Assert.assertEquals(5,usuarios.get(2).getUsuarioID());
    }
/*
    @Test
    public void testUsersByAlphabet() {
        List<User> usuaris = this.pm.usersByAlphabet();

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
        int verificador = this.pm.loginUser("marcmoran@estudiantat.upc.edu", "28102001");
        Assert.assertEquals(0, verificador);

        // Login incorrecto
        verificador = this.pm.loginUser("marcmoran@estudiantat.upc.edu", "12345678");
        Assert.assertEquals(1, verificador);
    }

    @Test
    public void testAddObject(){
        Assert.assertEquals(3,this.pm.numObjetos());
        this.pm.addObject("OKT","OktoberFest","Cultura cervecera",20);
        Assert.assertEquals(4,this.pm.numObjetos());
    }

    @Test
    public void testObjectByPrice() {
        List<ObjetoTienda> products = this.pm.objectByPrice();

        Assert.assertEquals("CST", products.get(0).getObjectID());
        Assert.assertEquals(55, products.get(0).getObjectCoins(), 0);

        Assert.assertEquals("APL", products.get(1).getObjectID());
        Assert.assertEquals(40, products.get(1).getObjectCoins(), 0);

        Assert.assertEquals("RZZ", products.get(2).getObjectID());
        Assert.assertEquals(30, products.get(2).getObjectCoins(), 0);

    }

    @Test
    public void testBuyObjectByUser() {
        //Cpmpra correcta verificador = 0
        int verificador = this.pm.buyObjectByUser("APL","1");
        Assert.assertEquals(0,verificador);

        //El usuario no existe verificador 1
        verificador = this.pm.buyObjectByUser("APL","8");
        Assert.assertEquals(1,verificador);

        //Saldo insuficiente verificador 2
        verificador = this.pm.buyObjectByUser("CST","1");
        Assert.assertEquals(2,verificador);
    }
     @Test
    public void testObjectBoughtByUser(){
        List<ObjetoTienda> objetosCpmprados = this.pm.objectBoughtByUser("1");
        Assert.assertEquals(0,objetosCpmprados.size());
     }


 */
}
