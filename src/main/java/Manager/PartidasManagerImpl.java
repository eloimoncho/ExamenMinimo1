package Manager;

import Entity.*;
import java.util.*;
import org.apache.log4j.Logger;

public class PartidasManagerImpl implements PartidasManager {

    List<Partidas> listaPartidas;
    HashMap<String, Usuarios> listaUsuarios;
    List<Juego> listaJuegos;
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
        this.listaUsuarios = new HashMap<>();
    }

    //Funciones de PartidasManager

    @Override
    public int inicioPartidaPorUsuario(String partidaID, String usuarioID) {
        int posibleJugar=1;

        int numUsers = this.listaUsuarios.size();
        for (int i=0; i<numUsers; i++){
            String idHashmap = Integer.toString(i);
            if ((Objects.equals(this.listaUsuarios.get(idHashmap).getUsuarioID(), usuarioID)) && this.listaUsuarios.get(idHashmap).getJugandoPartida()!="0"){ //El usuario no está jugando otra partida
                int numPartidas=this.listaPartidas.size();
                for (int j=0;j<numPartidas;j++) {
                    if ((Objects.equals(this.listaPartidas.get(j).getPartidaID(), partidaID)) && this.listaPartidas.get(j).getEstadoPartida()==0){ // La partida está en curso
                        posibleJugar=0;
                    }
                }
            }
        }
        return posibleJugar;
    }

    @Override
    public int nivelActual(String usuarioID) {
        int nivel=0;
        for (int i=0;i<this.listaPartidas.size();i++){
            if(Objects.equals(this.listaUsuarios.get(usuarioID).getJugandoPartida(),this.listaPartidas.get(i).getPartidaID())){
                nivel = this.listaUsuarios.get(usuarioID).getNivelUsuario();
            }
        }
        return nivel;
    }

    @Override
    public int puntuacionActual(String usuarioID) {
        int puntuacion =  this.listaUsuarios.get(usuarioID).getPuntos();
        return puntuacion;
    }

    @Override
    public int pasarNivel(String usuarioID, String fecha) {
        int nuevoNivel = this.listaUsuarios.get(usuarioID).getNivelUsuario();
        for (int i = 0; i<this.listaPartidas.size();i++){
            if (Objects.equals(this.listaUsuarios.get(usuarioID).getJugandoPartida(), this.listaPartidas.get(i).getPartidaID())){
                if (this.listaPartidas.get(i).getNiveles() >nuevoNivel){
                    nuevoNivel=nuevoNivel+1;
                }
                else{
                    int puntos = this.listaUsuarios.get(i).getPuntos() + 100;
                    this.listaUsuarios.get(usuarioID).setPuntos(puntos);
                    String partidaJugando="0";
                    this.listaUsuarios.get(usuarioID).setJugandoPartida(partidaJugando);
                    nuevoNivel=0;
                }
            }
        }
        return nuevoNivel;
    }

    @Override
    public int crearJuego(int partidaID, String descripcion, int numNiveles) {
        return 0;
    }

    @Override
    public int inicioPartidaPorUsuario(int usuarioID, int juegoID) {
        Juego aux1 = new Juego();
        Juego miJuego= aux1.getJuego(juegoID);
        Usuario aux2 = new Usuario();
        Usuario miUsuario = aux2.getUsuario(usuarioID);
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

    }

    @Override
    public int puntuacionActual(int usuarioID) {
        return 0;
    }

    @Override
    public int pasarNivel(int usuarioID, int puntos, String fecha) {
        return 0;
    }

    @Override
    public int finalizarPartida(String usuarioID) { //retorna 0
        int numUsers = this.listaUsuarios.size();
        for (int i = 0; i < numUsers; i++) {
            String idHashmap = Integer.toString(i);
            if ((Objects.equals(this.listaUsuarios.get(idHashmap).getUsuarioID(), usuarioID))) {
                if (Objects.equals(this.listaUsuarios.get(usuarioID).getJugandoPartida(), "0")) {
                    return -1; // El usuario no está jugando ninguna partida
                } else {
                    if (this.listaPartidas.get(i).getEstadoPartida() == 0) {
                        this.listaUsuarios.get(usuarioID).setJugandoPartida("0");
                        return 0; //Se ha finalizado la partida
                    } else {
                        return -2; //La partida no està en curs
                    }
                }
            }
        }
        return 0;
    }

    @Override
    public List<Usuarios> usuariosPorJuego(int partidaID) {
        return null;
    }

    @Override
    public List<Juegos> juegosPorUsuario(int usuarioID) {
        return null;
    }

    @Override
    public List<Partidas> actividadJuegoPorUsuario(int usuarioID, int partidaID) {
        return null;
    }


    @Override
    public List<Usuario> usuariosJugadoPartida(String partidaID) {
        List<Usuario> listaUsuariosPartida= new ArrayList<>();
        for (int i=0;i<this.listaUsuarios.size();i++){
            String idHashMap=Integer.toString(i);
            if (Objects.equals(this.listaUsuarios.get(idHashMap).getJugandoPartida(),partidaID)){
                Usuario aux=new Usuario(listaUsuarios.get(idHashMap).getUsuarioID(),listaUsuarios.get(idHashMap).getFecha());
                listaUsuariosPartida.add(aux);
            }
        }
        return listaUsuariosPartida;
    }

    @Override
    public List<Partidas> partidasJugadasPorUsuario(String usuarioID) {
        return listaUsuarios.get(usuarioID).getListaPartidas();
    }



    //Funciones de implementación
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

