package Service;

import Entity.*;
import Manager.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Api(value = "/objetos", description = "Endpoint to Objetos Service")
@Path("/objetos")


public class ObjetoService {

        private ObjetoManager om;

        public ObjetoService() {
            this.om = ObjetoManagerImpl.getInstance();
            if (om.size()==0) {
                this.om.registerUser("Eloi", "Moncho", "28/08/2001","eloi.moncho@etudiantat.upc.edu" ,"28082001" );
                this.om.registerUser("Victor",  "Fernandez", "13/06/2001","victor.fernanadez@estudiantat.upc.edu", "13062001");
                this.om.registerUser("Marc",  "Moran", "28/10/2001", "marc.moran@estudiantat.upc.edu", "28102001");

                this.om.addObject("APL", "Apolo", "Milkshake thursday", 40);
                this.om.addObject("RZZ", "Razz", "El Dirty",30);
                this.om.addObject("CST", "Costa", "Blue Moon", 55);
            }
        }

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
            int verificador = this.om.registerUser(user.getUserName(), user.getUserSurname(), user.getDate(), user.getCredenciales().getEmail(), user.getCredenciales().getPassword());
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

            List<User> listUsers = this.om.usersByAlphabet();

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
        public Response loginUser(User user) {
            int verificador = this.om.loginUser(user.getCredenciales().getEmail(),user.getCredenciales().getPassword());
            if (verificador == 0)
                return Response.status(201).build();
            else
                return Response.status(404).entity(user).build();
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

            List<ObjetoTienda> listObjetos = this.om.objectByPrice();
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
            this.om.addObject(objeto.getObjectID(),objeto.getObjectName(),objeto.getObjectDescription(),objeto.getObjectCoins());
            return Response.status(201).build();
        }

        //Sexta función: un usuario compra un objeto
        @PUT
        @ApiOperation(value = "Comprar un objeto", notes = "asdasd")
        @ApiResponses(value = {
                @ApiResponse(code = 201, message = "Compra exitosa"),
                @ApiResponse(code = 404, message = "No existe este usuario"),
                @ApiResponse(code = 405, message = "No tienes suficientes monedas"),
        })
        @Path("/BuyObject/{userID}/{objectID}")
        public Response buyObjectByUser(@PathParam("userID") String userID, @PathParam("objectID") String objectID) {
            int verificador = this.om.buyObjectByUser(objectID,userID);
            if (verificador==0) {
                return Response.status(201).build();
            }
            else if (verificador==1) {
                return Response.status(404).build();
            }
            else
                return Response.status(405).build();

        }

        //Séptima función: lista de objetos comprados por un usuario
        @GET
        @ApiOperation(value = "Lista de objetos comprados", notes = "asdasd")
        @ApiResponses(value = {
                @ApiResponse(code = 201, message = "Correcto", response = ObjetoTienda.class, responseContainer="List"),
        })
        @Path("/objectsBoughtByUser/{userID}")
        @Produces(MediaType.APPLICATION_JSON)
        public Response objectsBoughtByUser(@PathParam("userID") String userID) {

            List<ObjetoTienda> listObjetos = this.om.objectBoughtByUser(userID);
            GenericEntity<List<ObjetoTienda>> entity = new GenericEntity<List<ObjetoTienda>>(listObjetos) {};
            return Response.status(201).entity(entity).build()  ;
        }

    }

