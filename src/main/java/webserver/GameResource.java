package webserver;

import gameserver.transport.HangmanData;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/game")
public class GameResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getWordTest() {
        return "";
    }

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
            responseBuilder = Response.status(200).entity(data);
        }
        catch(Exception e) {
            e.printStackTrace();
            responseBuilder = Response.status(204).entity("No game data for that user.");
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
            else {
                data = AppConfig.controller.guessLetter(playerId, letter);
            }

            responseBuilder = Response.status(Response.Status.OK).entity(data);

        } catch(Exception e) {
            e.printStackTrace();
            responseBuilder = Response.status(401);
        }
        return responseBuilder.build();
    }
}
