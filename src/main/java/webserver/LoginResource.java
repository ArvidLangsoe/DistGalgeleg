package webserver;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/login")
public class LoginResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public boolean loggedIn(@QueryParam("playerId") String playerId) {
        return true;
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public void login(@QueryParam("playerId") String playerId, @QueryParam("password") String password){
        return;
    }
}
