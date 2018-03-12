package webserver;

import gameserver.transport.PlayerHistory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/history")
public class HistoryResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllGameData() {
        Response.ResponseBuilder responseBuilder;
        List<PlayerHistory> historyList = AppConfig.controller.getPlayHistoryData();
        if(historyList != null) {
            responseBuilder = Response.status(200).entity(historyList);
        } else {
            responseBuilder = Response.status(204).entity("No data found");
        }

        return responseBuilder.build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPlayerGameData(@PathParam("id") String playerId) {
        Response.ResponseBuilder responseBuilder;
        PlayerHistory playerHistory = AppConfig.controller.getPlayerHistoryData(playerId);
        if(playerHistory != null) {
            responseBuilder = Response.status(200).entity(playerHistory);
        } else {
            responseBuilder = Response.status(204).entity("No data for that user found");
        }
        return responseBuilder.build();
    }
}
