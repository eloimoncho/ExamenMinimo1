package Manager;

import java.util.List;
import Entity.*;

public interface PartidasManager {


    public void addUsuario(int usuarioID, String name);
    public Partidas getPartida(int usuarioID);

    public void crearJuego(int juegoID, String descripcion, int numNiveles); //0 cuando la partida se crea correctamente, 1 cuando no
    public int inicioPartidaPorUsuario(int usuarioID, int juegoID);//
    public int nivelActual(int usuarioID); //devuelve el número de nivel del usuario
    public int puntuacionActual(int usuarioID);//devuelve la puntuación actual
    public int pasarNivel(int usuarioID,int puntos, String fecha);//devuelve 0 si se ha realizado satisfactoriamente, -1 si el usuario no existe, -2 si no tiene partida en curso
    public int finalizarPartida(int usuarioID);
    public List<Usuario> usuariosPorJuego(int juegoID);
    public List<Juego> juegosPorUsuario(int usuarioID);
    public String actividadJuegoPorUsuario(int usuarioID, int juegoID);
    public int numJuegos();
    public int numPartidas();
    public int numUsuarios();
    int size();
}
