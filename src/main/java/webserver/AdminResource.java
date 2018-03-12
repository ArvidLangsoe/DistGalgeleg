package webserver;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.rmi.RemoteException;

@Path("/game")
public class AdminResource {

    /*
    * Deleting a player's game history data
    * */
    @DELETE
    @Path("/{id}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response deletePlayerGameData(@PathParam("id") String playerId,
                                         @QueryParam("admin") String adminId,
                                         @QueryParam("history") boolean history,
                                         @QueryParam("game") boolean game) {
        Response.ResponseBuilder responseBuilder;
        try {
            AppConfig.security.isLoggedInAdmin(adminId);
            // Delete a player's game history data
            if(history) {
                try {
                    AppConfig.controller.deleteGameData(adminId, playerId);
                    responseBuilder = Response.status(200).entity("Successfully deleted "+playerId+"'s game data.");
                } catch (Exception e) {
                    e.printStackTrace();
                    responseBuilder = Response.status(401).entity(e);
                }
            }
            // End/delete a player's current active game
            else if(game) {
                try {
                    AppConfig.controller.endGame(adminId, playerId);
                    responseBuilder = Response.status(200).entity("Successfully ended "+playerId+"'s active game.");
                } catch (Exception e) {
                    e.printStackTrace();
                    responseBuilder = Response.status(401).entity(e);
                }
            }
            else {
                responseBuilder = Response.status(405).entity("Must define history or game.");
            }

        } catch (RemoteException e) {
            responseBuilder = Response.status(405).entity(e);
        }
        return responseBuilder.build();
    }
}
