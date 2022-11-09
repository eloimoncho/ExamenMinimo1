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
                this.om.addObject("APL", "Apolo", "Milkshake thursday", 40);
                this.om.addObject("RZZ", "Razz", "El Dirty",30);
                this.om.addObject("CST", "Costa", "Blue Moon", 55);
            }
        }

        @GET
        @ApiOperation(value = "get all Objects", notes = "asdasd")
        @ApiResponses(value = {
                @ApiResponse(code = 201, message = "Successful", response = ObjetoTienda.class, responseContainer="List"),
        })
        @Path("/")
        @Produces(MediaType.APPLICATION_JSON)
        public Response getAllObjetos() {

            List<ObjetoTienda> listObjetos = this.om.objectByPrice();

            GenericEntity<List<ObjetoTienda>> entity = new GenericEntity<List<ObjetoTienda>>(listObjetos) {};
            return Response.status(201).entity(entity).build()  ;

        }

        @GET
        @ApiOperation(value = "get an Object", notes = "asdasd")
        @ApiResponses(value = {
                @ApiResponse(code = 201, message = "Successful", response = ObjetoTienda.class),
                @ApiResponse(code = 404, message = "Object not found")
        })
        @Path("/{id}")
        @Produces(MediaType.APPLICATION_JSON)
        public Response getObjeto(@PathParam("id") String id) {
            ObjetoTienda o = this.om.get(id);
            if (o == null) return Response.status(404).build();
            else  return Response.status(201).entity(o).build();
        }

        @DELETE
        @ApiOperation(value = "delete a Track", notes = "asdasd")
        @ApiResponses(value = {
                @ApiResponse(code = 201, message = "Successful"),
                @ApiResponse(code = 404, message = "Track not found")
        })
        @Path("/{id}")
        public Response deleteTrack(@PathParam("id") String id) {
            Track t = this.om.getTrack(id);
            if (t == null) return Response.status(404).build();
            else this.om.deleteTrack(id);
            return Response.status(201).build();
        }

        @PUT
        @ApiOperation(value = "update a Track", notes = "asdasd")
        @ApiResponses(value = {
                @ApiResponse(code = 201, message = "Successful"),
                @ApiResponse(code = 404, message = "Track not found")
        })
        @Path("/")
        public Response updateTrack(Track track) {

            Track t = this.om.updateTrack(track);

            if (t == null) return Response.status(404).build();

            return Response.status(201).build();
        }



        @POST
        @ApiOperation(value = "create a new Track", notes = "asdasd")
        @ApiResponses(value = {
                @ApiResponse(code = 201, message = "Successful", response=Track.class),
                @ApiResponse(code = 500, message = "Validation Error")

        })

        @Path("/")
        @Consumes(MediaType.APPLICATION_JSON)
        public Response newTrack(Track track) {

            if (track.getSinger()==null || track.getTitle()==null)  return Response.status(500).entity(track).build();
            this.om.addTrack(track);
            return Response.status(201).entity(track).build();
        }

    }

}
