package Service;

import Entity.*;
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
            if (pm.size()==0) {
                this.pm.creacionPartida("Brawl", "Guerra", 10 );
                this.pm.creacionPartida("Agario",  "Guai",2);
                this.pm.creacionPartida("Candy",  "Arcade", 3);
            }
        }

        //Primera función: inicio partida por usuario
        @PUT
        @ApiOperation(value = "Iniciar un juego", notes = "asdasd")
        @ApiResponses(value = {
                @ApiResponse(code = 201, message = "El juego se inicia correctamente"),
                @ApiResponse(code = 404, message = "No es posible iniciar el juego"),

        })
        @Path("/BuyObject/{partidaID}/{usuarioID}")
        public Response inicioPartidaPorUsuario(@PathParam("partidaID") String partidaID, @PathParam("usuarioID") String usuarioID) {
            int verificador = this.pm.inicioPartidaPorUsuario(partidaID,usuarioID);
            if (verificador==0) {
                return Response.status(201).build();
            }
            else {
                return Response.status(404).build();
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
    }

