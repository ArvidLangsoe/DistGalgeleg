package webserver;

import galgeleg.IGalgelogik;
import gameserver.server.IGalgeController;

import javax.annotation.PostConstruct;
import javax.print.attribute.standard.Media;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.Naming;
import java.rmi.RemoteException;

@Path("/")
public class GalgeResource {

    private static IGalgeController controller;

    static {
        URL url = null;
        try {
            url = new URL("http://localhost:9901/hangman?wsdl");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        QName qname = new QName("http://server.gameserver/", "GalgeControllerService");
        Service service = Service.create(url, qname);
        controller = service.getPort(IGalgeController.class);
    }

    /*
    * Create JSON with all available resources
    * */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getResources() {
        controller.newGame("Jeppe");
        return controller.getFullWord("Jeppe");
    }

    @GET
    @Path("{id}/hangman")
    @Produces(MediaType.APPLICATION_JSON)
    public String getGameInfo(@PathParam("id") String playerId) {
        return "";
    }

    @POST
    @Path("{id}/hangman/")
    @Produces(MediaType.APPLICATION_JSON)
    public String guessLetter(@PathParam("id") String playerId, @QueryParam("letter") String letter) {
        return "";
    }

    @GET
    @Path("data")
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllGameData() {
        return "";
    }

    @GET
    @Path("data/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getPlayerGameData(@PathParam("id") String playerId) {
        return "";
    }

    @DELETE
    @Path("data/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getPlayerGameData(@PathParam("id") String playerId, @QueryParam("admin") String adminId) {
        return "";
    }

    @POST
    @Path("admin")
    @Produces(MediaType.APPLICATION_JSON)
    public String createAdmin(@QueryParam("adminId") String adminId, @QueryParam("playerId") String playerId) {
        return "";
    }

    @GET
    @Path("admin")
    @Produces(MediaType.APPLICATION_JSON)
    public String getAdmins(@QueryParam("adminId") String adminId) {
        return "";
    }

    @GET
    @Path("login")
    @Produces(MediaType.APPLICATION_JSON)
    public boolean loggedIn(@QueryParam("playerId") String playerId) {
        return true;
    }

    @POST
    @Path("login")
    @Produces(MediaType.APPLICATION_JSON)
    public void login(@QueryParam("playerId") String playerId, @QueryParam("password") String password){
        return;
    }

    /*
    * OLD below
    * */
    /*

    @GET
    @Path("word/full")
    @Produces(MediaType.TEXT_PLAIN)
    public String getWord() throws RemoteException {
        return logik.getOrdet();
    }

    @GET
    @Path("word/visible")
    @Produces(MediaType.TEXT_PLAIN)
    public String getVisibleWord() throws RemoteException {
        return logik.getSynligtOrd();
    }

    @GET
    @Path("word/letters")
    @Produces(MediaType.TEXT_PLAIN)
    public String getUsedLetters() throws RemoteException {
        return logik.getBrugteBogstaver().toString();
    }

    @GET
    @Path("word/reset")
    public Response reset() throws RemoteException {
        logik.nulstil();
        Response.ResponseBuilder responseBuilder = Response.status(Response.Status.OK);
        return responseBuilder.entity("Game has been reset").build();
    }

    /*
    @POST
    @Path("guessletter/{letter}")
    @Produces(MediaType.TEXT_PLAIN)
    public String guessLetter(@QueryParam("letter") String letter) throws RemoteException {
        System.out.println(letter);
        logik.gætBogstav(letter);
        return Boolean.toString(logik.erSidsteBogstavKorrekt());
    }

    @POST
    @Path("word/guess/{letter}")
    @Produces(MediaType.TEXT_PLAIN)
    public String guessLetter(@QueryParam("letter") String letter) throws RemoteException {
        logik.gætBogstav("o");
        return Boolean.toString(logik.erSidsteBogstavKorrekt());
    }

    @POST
    @Path("word/guess")
    @Produces(MediaType.TEXT_PLAIN)
    public String guessLetter2(String letter) throws RemoteException {
        logik.gætBogstav(letter);
        return logik.getSynligtOrd();
    }
    */
}
