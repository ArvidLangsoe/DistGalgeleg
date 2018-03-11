package webserver;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.rmi.RemoteException;

@Path("/auth")
public class AuthResource {

    @POST
    @Path("/login")
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(@QueryParam("playerId") String playerId, @QueryParam("password") String password){
        Response.ResponseBuilder responseBuilder;
        boolean loggedIn = AppConfig.controller.login(playerId, password);
        if(loggedIn) {
            boolean admin = false;
            try {
                admin = AppConfig.security.isLoggedInAdmin(playerId);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            responseBuilder = Response.status(200).entity("{ \"loggedIn\": \""+loggedIn+"\", \"playerId\": \""+playerId+"\", \"admin\": \""+admin+"\" }");
            try {
                AppConfig.controller.newGame(playerId);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            responseBuilder = Response.status(401).entity("Authentication failed");
        }
        return responseBuilder.build();
    }

    @GET
    @Path("/admin/{id}")
    public Response isAdmin(@PathParam("id") String playerId) {
        return null;
    }

    @POST
    @Path("/logout/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response logout(@PathParam("id") String playerId) {
        Response.ResponseBuilder responseBuilder = null;
        
        return responseBuilder.build();
    }

}
