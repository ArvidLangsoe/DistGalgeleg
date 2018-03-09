package webserver;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/data")
public class DataResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllGameData() {
        return "";
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getPlayerGameData(@PathParam("id") String playerId) {
        return "";
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getPlayerGameData(@PathParam("id") String playerId, @QueryParam("admin") String adminId) {
        return "";
    }

}
