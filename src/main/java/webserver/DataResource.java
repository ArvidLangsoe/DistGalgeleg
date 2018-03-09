package webserver;

import gameserver.server.IGalgeController;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.MalformedURLException;
import java.net.URL;

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
