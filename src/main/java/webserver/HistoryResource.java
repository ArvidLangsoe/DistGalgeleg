package webserver;

import gameserver.transport.PlayerHistory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/history")
public class HistoryResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<PlayerHistory> getAllGameData() {
        return AppConfig.controller.getPlayHistoryData();
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
