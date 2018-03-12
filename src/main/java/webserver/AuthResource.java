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
        boolean loggedIn = false;
        try {
            loggedIn = AppConfig.security.login(playerId, password);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        if(loggedIn) {
            boolean admin = false;
            try {
                admin = AppConfig.security.isLoggedInAdmin(playerId);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            responseBuilder = Response.status(200).entity("{ \"loggedIn\": "+loggedIn+", \"playerId\": \""+playerId+"\", \"admin\": "+admin+" }");
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
    @Path("/login/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response isAdmin(@PathParam("id") String playerId) {
        Response.ResponseBuilder responseBuilder;
        try {
            boolean loggedIn = AppConfig.security.isLoggedIn(playerId);
            if (loggedIn)
                responseBuilder = Response.status(200).entity(playerId + " is logged in.");
            else
                responseBuilder = Response.status(200).entity(playerId + " is not logged in.");
        } catch (RemoteException e) {
            e.printStackTrace();
            responseBuilder = Response.status(404).entity("");
        }

        return responseBuilder.build();
    }

    @POST
    @Path("/logout/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response logout(@PathParam("id") String playerId) {
        Response.ResponseBuilder responseBuilder;
        try {
            AppConfig.security.logout(playerId);
            responseBuilder = Response.status(200).entity("Successfully logged out");
        } catch (RemoteException e) {
            e.printStackTrace();
            responseBuilder = Response.status(404).entity("Error");
        }
        return responseBuilder.build();
    }
}
