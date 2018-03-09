package webserver;

import gameserver.transport.HangmanData;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/game")
public class GameResource {

    /*
    * Get player's game data
    * */
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getGameInfo(@PathParam("id") String playerId) {
        HangmanData data;
        Response.ResponseBuilder responseBuilder;
        try {
            data = AppConfig.controller.getGameData(playerId);
            System.out.println(data);
            responseBuilder = Response.status(200).entity(data);
        }
        catch(Exception e) {
            e.printStackTrace();
            responseBuilder = Response.status(404).entity("User not found");
        }
        return responseBuilder.build();
    }

    /*
    * Create a new game or guess a letter
    * */
    @POST
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response postGame(@PathParam("id") String playerId, @QueryParam("letter") String letter, @QueryParam("new") boolean newGame) {
        HangmanData data;
        Response.ResponseBuilder responseBuilder;
        try {
            if(newGame) {
                data = AppConfig.controller.newGame(playerId);
            }
            
            data = AppConfig.controller.guessLetter(playerId, letter);
            responseBuilder = Response.status(Response.Status.OK).entity(data);

        } catch(Exception e) {
            e.printStackTrace();
            responseBuilder = Response.status(404).entity("User not found");
        }
        return responseBuilder.build();
    }
}
