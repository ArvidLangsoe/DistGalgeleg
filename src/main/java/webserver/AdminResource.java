package webserver;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.rmi.RemoteException;
import java.util.Set;

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

        // Delete a player's game history data
        if(history) {
            try {
                AppConfig.controller.deleteGameData(adminId, playerId);
                responseBuilder = Response.status(200).entity("Successfully deleted "+playerId+"'s history game data.");
            } catch (Exception e) {
                e.printStackTrace();
                responseBuilder = Response.status(401);
            }
        }
        // End/delete a player's current active game
        else if(game) {
            try {
                AppConfig.controller.endGame(adminId, playerId);
                responseBuilder = Response.status(200).entity("Successfully ended "+playerId+"'s currently active game.");
            } catch (Exception e) {
                e.printStackTrace();
                responseBuilder = Response.status(401);
            }
        }
        else {
            responseBuilder = Response.status(405).entity("Must define history or game.");
        }

        return responseBuilder.build();
    }

    @GET
    @Path("/history")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllGames(@QueryParam("admin") String adminId) {
        Response.ResponseBuilder responseBuilder;
        Set<String> allGames;

        if(adminId != null) {
            try {
                allGames = AppConfig.controller.getAllGames(adminId);
                responseBuilder = Response.status(200).entity(allGames);
            } catch (Exception e) {
                e.printStackTrace();
                responseBuilder = Response.status(401);
            }
        }
        else {
            responseBuilder = Response.status(405);
        }

        return responseBuilder.build();
    }
}
