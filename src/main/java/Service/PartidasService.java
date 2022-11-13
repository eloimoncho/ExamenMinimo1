package Service;

import Entity.*;
import Entity.Usuario;
import Manager.*;
import Main.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Api(value = "/objetos", description = "Endpoint to Objetos Service")
@Path("/objetos")


public class PartidasService {

    private PartidasManager pm;

    public PartidasService() {
        this.pm = PartidasManagerImpl.getInstance();
        if (pm.size() == 0) {
            this.pm.addUsuario(1, "Alba");
            this.pm.addUsuario(2, "Marc");
            this.pm.addUsuario(3, "Isaac");

            this.pm.crearJuego(1, "Apolo", 3);
            this.pm.crearJuego(2, "Razz", 3);
            this.pm.crearJuego(3, "Costa", 4);

            this.pm.inicioPartidaPorUsuario(1, 1);
            this.pm.inicioPartidaPorUsuario(1, 2);
            this.pm.inicioPartidaPorUsuario(2, 2);
        }
    }

    //Primera función: inicio partida por usuario
    @POST
    @ApiOperation(value = "Crear Juego", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Creado correctamente", response = Juego.class),
            //@ApiResponse(code = 404, message = "Ya existe un juego con este ID")

    })
    @Path("/juegos/crear")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response crearJuego(Juego juego) {
        this.pm.crearJuego(juego.getJuegoID(), juego.getDescripcion(), juego.getNiveles());
        return Response.status(201).entity(juego).build();
    }

    @PUT
    @ApiOperation(value = "Iniciar un juego", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "El juego se inicia correctamente"),
            @ApiResponse(code = 404, message = "No es posible iniciar el juego, el usuario no existe"),
            @ApiResponse(code = 405, message = "No es posible iniciar el juego, el juego no existe"),
    })
    @Path("/juegos/{usuarioID}/{juegoID}")
    public Response inicioPartidaPorUsuario(@PathParam("usuarioID") int usuarioID, @PathParam("juegoID") int juegoID) {
        int verificador = this.pm.inicioPartidaPorUsuario(usuarioID, juegoID);
        if (verificador == 0) {
            return Response.status(201).build();
        } else if (verificador == -1) {
            return Response.status(404).build();
        } else
            return Response.status(405).build();
    }

    @GET
    @ApiOperation(value = "nivel actual", notes = "")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = String.class, responseContainer = "List"),
            @ApiResponse(code = 404, message = "Este jugador no existe"),
            @ApiResponse(code = 405, message = "Este jugador no está en ninguna partida")
    })
    @Path("/player/{usuarioID}/nivelActual")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getNivelActual(@PathParam("usuarioID") int usuarioID) {
        int nivel = this.pm.nivelActual(usuarioID);
        if (nivel == -1){
            return Response.status(404).build();
        }
        else if (nivel == -2) {
            return Response.status(405).build();
        }
        else
            return Response.status(200).build();
    }

    @GET
    @ApiOperation(value = "puntuacion actual", notes = "")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = String.class, responseContainer = "List"),
            @ApiResponse(code = 404, message = "Este jugador no existe"),
            @ApiResponse(code = 405, message = "Este jugador no está en ninguna partida")
    })
    @Path("/player/{id}/nivelActual")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getpuntuacionActual(@PathParam("id") int usuarioID) {
        int puntos = this.pm.puntuacionActual(usuarioID);
        if (puntos == -1){
            return Response.status(404).build();
        }
        else if (puntos == -2) {
            return Response.status(405).build();
        }
        else
            return Response.status(200).build();
    }
    @PUT
    @ApiOperation(value = "pasar de nivel", notes = "")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Has pasado de nivel!"),
            @ApiResponse(code = 202, message = "Has terminado el juego!"),
            @ApiResponse(code = 404, message = "Este jugador no existe"),
            @ApiResponse(code = 405, message = "Este jugador no está en ninguna partida")
    })
    @Path("/usuario/pasarNivel/{usuarioID}/{puntos}/{fecha}")
    public Response pasarNivel(@PathParam("usuarioID") int usuarioID, @PathParam("puntos") int puntos, @PathParam("fecha") String fecha){
        int nivel = this.pm.pasarNivel(usuarioID,puntos,fecha);
        if (nivel == -1){
            return Response.status(404).build();
        }
        else if (nivel == -2) {
            return Response.status(405).build();
        }
        else if (nivel==0)
            return Response.status(202).build();
        else
            return Response.status(200).build();
    }
    @PUT
    @ApiOperation(value = "Finalizar un juego", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "El juego se inicia correctamente"),
            @ApiResponse(code = 404, message = "No es posible iniciar el juego, el usuario no existe"),
            @ApiResponse(code = 405, message = "No es posible iniciar el juego, el juego no existe"),
    })
    @Path("/juegos/finalizar/{usuarioID}")
    public Response finalizarPartida(@PathParam("usuarioID") int usuarioID){
        int finalizar = this.pm.finalizarPartida(usuarioID);
        if (finalizar == -1){
            return Response.status(404).build();
        }
        else if (finalizar == -2) {
            return Response.status(405).build();
        }
        else
            return Response.status(200).build();
    }
    @GET
    @ApiOperation(value = "partidas jugadas por un usuario", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = Partidas.class, responseContainer="List"),
            @ApiResponse(code = 404, message = "este jugador no existe")
    })
    @Path("/usuario/{id}/partidas")
    @Produces(MediaType.APPLICATION_JSON)
    public Response juegosPorUsuario(@PathParam("id") int id) {
        List<Juego> listJuegos = this.pm.juegosPorUsuario(id);
        if(listJuegos!= null){
            GenericEntity<List<Juego>> entity = new GenericEntity<List<Juego>>(listJuegos) {};
            return Response.status(200).entity(entity).build()  ;
        }
        return Response.status(404).build();
    }

    @GET
    @ApiOperation(value = "juegos jugados por un usuario", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = Partidas.class, responseContainer="List"),
            @ApiResponse(code = 404, message = "este jugador no existe")
    })
    @Path("/usuario/{id}/partidas")
    @Produces(MediaType.APPLICATION_JSON)
    public Response usuariosPorJuego(@PathParam("id") int id) {
        List<Usuario> listUsuarios = this.pm.usuariosPorJuego(id);
        if(listUsuarios!= null){
            GenericEntity<List<Usuario>> entity = new GenericEntity<List<Usuario>>(listUsuarios) {};
            return Response.status(200).entity(entity).build();
        }
        else
            return Response.status(404).build();
    }

    @GET
    @ApiOperation(value = "actividad de una partida", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = Partidas.class, responseContainer="List"),
    })
    @Path("/usuario/{usuarioID}/{juegoID}/partidas")
    @Produces(MediaType.APPLICATION_JSON)
    public Response actividadJuegoPorUsuario(@PathParam("usuarioID") int usuarioID, @PathParam("juegoID") int juegoID) {
        String listadoActividad =  this.pm.actividadJuegoPorUsuario(usuarioID,juegoID);

        GenericEntity<String> entity = new GenericEntity<String>(listadoActividad) {};
        return Response.status(200).entity(entity).build();


    }

}





/*
        //Primera función: Registro
        @POST
        @ApiOperation(value = "Registrar usuario", notes = "asdasd")
        @ApiResponses(value = {
                @ApiResponse(code = 201, message = "Registro correcto"),
                @ApiResponse(code = 404, message = "Registro incorrecto, ya existe un usuario con este email"),
        })
        @Path("/users")
        @Consumes(MediaType.APPLICATION_JSON)
        public Response registerUser(User user){
            int verificador = this.pm.registerUser(user.getUserName(), user.getUserSurname(), user.getDate(), user.getCredenciales().getEmail(), user.getCredenciales().getPassword());
            if(verificador == 0){
                return Response.status(201).build();
            }
            else{
                return Response.status(401).build();
            }

        }

        //Segunda función: lista de usuarios por orden alfabético
        @GET
        @ApiOperation(value = "Lista de usuarios", notes = "asdasd")
        @ApiResponses(value = {
                @ApiResponse(code = 201, message = "Correcto", response = User.class, responseContainer="List"),
        })
        @Path("/listUsers")
        @Produces(MediaType.APPLICATION_JSON)
        public Response getUsersByAlphabet() {

            List<User> listUsers = this.pm.usersByAlphabet();

            GenericEntity<List<User>> entity = new GenericEntity<List<User>>(listUsers) {};
            return Response.status(201).entity(entity).build()  ;
        }

        //Tercera función: login
        @POST
        @ApiOperation(value = "Login", notes = "asdasd")
        @ApiResponses(value = {
                @ApiResponse(code = 201, message = "Login correcto"),
                @ApiResponse(code = 404, message = "Login incorrecto"),
        })
        @Path("/login")
        @Produces(MediaType.APPLICATION_JSON)
        public Response loginUser(Credenciales credenciales) {
            int verificador = this.pm.loginUser(credenciales.getEmail(),credenciales.getPassword());
            if (verificador == 0)
                return Response.status(201).build();
            else
                return Response.status(404).entity(credenciales).build();
        }

        //Cuarta función: lista de objetos
        @GET
        @ApiOperation(value = "Lista de objetos", notes = "asdasd")
        @ApiResponses(value = {
                @ApiResponse(code = 201, message = "Correcto", response = ObjetoTienda.class, responseContainer="List"),
        })
        @Path("/listObjetos")
        @Produces(MediaType.APPLICATION_JSON)
        public Response getAllObjetos() {

            List<ObjetoTienda> listObjetos = this.pm.objectByPrice();
            GenericEntity<List<ObjetoTienda>> entity = new GenericEntity<List<ObjetoTienda>>(listObjetos) {};
            return Response.status(201).entity(entity).build()  ;
        }

        //Quinta función: añadir objeto
        @POST
        @ApiOperation(value = "Añadir un objeto", notes = "asdasd")
        @ApiResponses(value = {
                @ApiResponse(code = 201, message = "Objeto añadido correctamente"),
        })
        @Path("/addObject")
        @Produces(MediaType.APPLICATION_JSON)
        public Response addObject(ObjetoTienda objeto) {
            this.pm.addObject(objeto.getObjectID(),objeto.getObjectName(),objeto.getObjectDescription(),objeto.getObjectCoins());
            return Response.status(201).build();
        }

        //Sexta función: un usuario cpmpra un objeto
        @PUT
        @ApiOperation(value = "Cpmprar un objeto", notes = "asdasd")
        @ApiResponses(value = {
                @ApiResponse(code = 201, message = "Cpmpra exitosa"),
                @ApiResponse(code = 404, message = "No existe este usuario"),
                @ApiResponse(code = 405, message = "No tienes suficientes monedas"),
        })
        @Path("/BuyObject/{userID}/{objectID}")
        public Response buyObjectByUser(@PathParam("userID") String userID, @PathParam("objectID") String objectID) {
            int verificador = this.pm.buyObjectByUser(objectID,userID);
            if (verificador==0) {
                return Response.status(201).build();
            }
            else if (verificador==1) {
                return Response.status(404).build();
            }
            else
                return Response.status(405).build();

        }

        //Séptima función: lista de objetos cpmprados por un usuario
        @GET
        @ApiOperation(value = "Lista de objetos cpmprados", notes = "asdasd")
        @ApiResponses(value = {
                @ApiResponse(code = 201, message = "Correcto", response = ObjetoTienda.class, responseContainer="List"),
        })
        @Path("/objectsBoughtByUser/{userID}")
        @Produces(MediaType.APPLICATION_JSON)
        public Response objectsBoughtByUser(@PathParam("userID") String userID) {

            List<ObjetoTienda> listObjetos = this.pm.objectBoughtByUser(userID);
            GenericEntity<List<ObjetoTienda>> entity = new GenericEntity<List<ObjetoTienda>>(listObjetos) {};
            return Response.status(201).entity(entity).build()  ;
        }
*/


