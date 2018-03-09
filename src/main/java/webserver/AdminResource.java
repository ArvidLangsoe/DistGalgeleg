package webserver;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/admin")
public class AdminResource {

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public String createAdmin(@QueryParam("adminId") String adminId, @QueryParam("playerId") String playerId) {
        return "";
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getAdmins(@QueryParam("adminId") String adminId) {
        return "";
    }
}
