package Manager;

import Entity.*;
import java.util.*;
import org.apache.log4j.Logger;

public class PartidasManagerImpl implements PartidasManager {

    List<Partidas> listaPartidas;
    List<Usuario> listaUsuarios;
    List<Juego> listaJuegos;
    List<Actividad> listaActividad;
    private static PartidasManager instance;
    final static Logger logger = Logger.getLogger(PartidasManagerImpl.class);

    public static PartidasManager getInstance() {
        if (instance==null) instance = new PartidasManagerImpl();
        return instance;
    }
    public int size() {
        int ret = this.listaPartidas.size();
        logger.info("size " + ret);

        return ret;
    }

    //Constructores

    public PartidasManagerImpl() {
        this.listaPartidas = new ArrayList<>();
        this.listaUsuarios = new ArrayList<>();
        this.listaJuegos = new ArrayList<>();
        this.listaActividad = new ArrayList<>();
    }

    //Funciones de PartidasManager

    public void addUsuario(int usuarioID, String name){
        Usuario usuario = new Usuario(usuarioID,name);
        this.listaUsuarios.add(usuario);
    }
    public Juego getJuego(int juegoID){
        int numJuegos=this.listaJuegos.size();
        for (int i=0; i<numJuegos; i++){
            if (this.listaJuegos.get(i).getJuegoID()==juegoID){
                return this.listaJuegos.get(i);
            }
        }
        Juego noExiste= new Juego(-1,"noExiste",0);
        return noExiste;
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
    @Override
    public Partidas getPartida(int usuarioID){
        int numPartidas = this.listaPartidas.size();
        for (int i=0; i<numPartidas; i++){
            if (this.listaPartidas.get(i).getUsuarioID() == usuarioID){ //Encontramos el usuario
                return this.listaPartidas.get(i);
            }
        }
        Partidas noExiste = new Partidas(-1,-1);
        return noExiste;
    }


    @Override
    public void crearJuego(int juegoID, String descripcion, int numNiveles) {
        Juego juego= new Juego(juegoID,descripcion,numNiveles);
        this.listaJuegos.add(juego);
    }

    @Override
    public int inicioPartidaPorUsuario(int usuarioID, int juegoID) {
        Juego miJuego= getJuego(juegoID);
        Usuario miUsuario = getUsuario(usuarioID);
        if (miJuego.getJuegoID()==-1){
            return -2; //no existe este juego
        }
        if (miUsuario.getUsuarioID()==-1){
            return -1; //no existe este usuario
        }
        Partidas aux3 = new Partidas(usuarioID,juegoID);
        this.listaPartidas.add(aux3);
        return 0;
    }

    @Override
    public int nivelActual(int usuarioID) {
        int miNivel=0;
        Partidas partida = new Partidas();
        partida=getPartida(usuarioID);
        Usuario miUsuario = getUsuario(usuarioID);
        if (miUsuario.getUsuarioID() == -1) {
            return -1;  //no existe este usuario
        }
        if(partida.getJuegoID()==-1){
            return -2; //el usuario no está jugando ninguna partida
        }
        miNivel=partida.getNivelActual();
        return miNivel;

    }

    @Override
    public int puntuacionActual(int usuarioID) {
        int puntos=0;
        Partidas partida = new Partidas();
        partida=getPartida(usuarioID);
        Usuario miUsuario = getUsuario(usuarioID);
        if (miUsuario.getUsuarioID() == -1) {
            return -1;  //no existe este usuario
        }
        if(partida.getJuegoID()==-1){
            return -2; //el usuario no está jugando ninguna partida
        }
        puntos=partida.getPuntos();
        return puntos;

    }

    @Override
    public int pasarNivel(int usuarioID, int puntos, String fecha) {
        int nivelActual= nivelActual(usuarioID);
        if(nivelActual==-1 || nivelActual==-2){
            return nivelActual;
        }
        Partidas p=new Partidas();
        p=getPartida(usuarioID);
        int juegoID = p.getJuegoID();
        Juego j=new Juego();
        j=getJuego(juegoID);
        int maxNiveles=j.getNiveles();
        if(maxNiveles == nivelActual){
            int puntosActual = p.getPuntos()+100;
            p.setPuntos(puntosActual);
            finalizarPartida(usuarioID);
            Actividad actividad = new Actividad(nivelActual, puntosActual, fecha);
            p.addActividad(actividad);
            return 0;// Ha llegado al máximo de niveles
        }
        nivelActual=nivelActual+1;
        int puntosActual = p.getPuntos() + puntos;
        Actividad actividad = new Actividad(nivelActual, puntosActual, fecha);
        p.addActividad(actividad);
        p.setNivelActual(nivelActual);
        p.setPuntos(puntosActual);
        p.setFecha(fecha);
        return nivelActual;

    }

    @Override
    public int finalizarPartida(int usuarioID) {
        boolean finalizar = true;
        Partidas partida = new Partidas();
        partida=getPartida(usuarioID);
        Usuario miUsuario = getUsuario(usuarioID);
        if (miUsuario.getUsuarioID() == -1) {
            return -1;  //no existe este usuario
        }
        if(partida.getJuegoID()==-1){
            return -2; //el usuario no está jugando ninguna partida
        }
        partida.setActivo(false);
        return 0;
    }


    @Override
    public List<Usuario> usuariosPorJuego(int juegoID) {
        List<Usuario> resultado = new ArrayList<>();
        for(int i=0; i<this.listaPartidas.size();i++){
            Usuario aux1 = new Usuario();
            if(this.listaPartidas.get(i).getJuegoID() == juegoID){
                aux1=getUsuario(this.listaPartidas.get(i).getUsuarioID());
                resultado.add(aux1);
            }
        }
        return resultado;
    }

    @Override
    public List<Juego> juegosPorUsuario(int usuarioID) {
        List<Juego> listJuegos = new ArrayList<>();
        for(int i=0;i<this.listaPartidas.size();i++){
            Juego aux1;
            if(this.listaPartidas.get(i).getUsuarioID() == usuarioID){
                aux1=getJuego(this.listaPartidas.get(i).getJuegoID());
                listJuegos.add(aux1);
            }
        }
        return listJuegos;
    }

    @Override
    public String actividadJuegoPorUsuario(int usuarioID, int juegoID) {
        List<Actividad> actividad = new ArrayList<>();
        for(int i=0;i<this.listaPartidas.size();i++){
            if(this.listaPartidas.get(i).getUsuarioID() == usuarioID && this.listaPartidas.get(i).getJuegoID() == juegoID){
                Usuario usuario = getUsuario(usuarioID);
                Juego juego = getJuego(juegoID);
                String listadoActividad="Actvidad: "+ usuario.getNombre()+", "+ juego.getDescripcion() + "--> ";
                for(int j=0;j<this.listaPartidas.get(i).getListaActividad().size();j++){
                    listadoActividad=listadoActividad + "(level: " + this.listaPartidas.get(i).getListaActividad().get(j).getNivel()
                            + ", points: " + this.listaPartidas.get(i).getListaActividad().get(j).getPuntos()
                            + ", date: " + this.listaPartidas.get(i).getListaActividad().get(j).getFecha() + ") ";
                }
                return listadoActividad;
            }
        }
        return "This user hasn't played this game";
    }

    //Funciones de implementación
    public int numJuegos(){
        return this.listaJuegos.size();
    }
    @Override
    public int numPartidas() {
        return this.listaPartidas.size();
    }

    @Override
    public int numUsuarios() {
        return this.listaUsuarios.size();
    }

    //Funciones del ObjetoManager
    /*
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
*/
}

