package Manager;

import java.util.List;
import Entity.*;

public interface PartidasManager {

    public int creacionPartida(String partidaID, String descripcion, int numNiveles); //0 cuando la partida está activa, 1 cuando no
    public int inicioPartidaPorUsuario(String partidaID, String usuarioID);
    public int nivelActual(String usuarioID); //devuelve el número de nivel del usuario
    public int puntuacionActual(String usuarioID);//devuelve la puntuación actual
    public int pasarNivel(String usuarioID,String fecha);//devuelve 0 si se ha realizado satisfactoriamente, -1 si el usuario no existe, -2 si no tiene partida en curso
    public int finalizarPartida(String usuarioID);
    public List<Usuario> usuariosJugadoPartida(String partidaID);
    public List<Partidas> partidasJugadasPorUsuario(String usuarioID);
    public int numPartidas();
    public int numUsuarios();
    int size();
}
