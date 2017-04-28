package ru.atom.dbhackaton.server.resource;

import com.google.gson.Gson;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import ru.atom.dbhackaton.server.dao.Database;
import ru.atom.dbhackaton.server.dao.LoginedUserDao;
import ru.atom.dbhackaton.server.mm.Connection;
import ru.atom.dbhackaton.server.mm.ThreadSafeQueue;
import ru.atom.dbhackaton.server.model.LoginedUser;
import ru.atom.dbhackaton.server.service.MatchMakerService;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

/**
 * Created by Alex on 20.04.17.
 */

@Path("/")
public class MatchMakerResources {
    private static final Logger log = LogManager.getLogger(ru.atom.dbhackaton.server.resource.MatchMakerResources.class);
    public static MatchMakerService matchMakerService = new MatchMakerService();

    @Path("/join")
    @GET
    @Consumes("application/x-www-form-urlencoded")
    public Response join(@QueryParam("token") String token) {

        //Session session = Database.session();
        //LoginedUser user = LoginedUserDao.getByToken(session, token);

        long gameSessionId = matchMakerService.join(token);
        if (gameSessionId == -1) {
            return Response.status(Response.Status.OK).entity("Please, wait :)").build();
        }

        String GameURL = "wtfis.ru:8090/gs/12345";
        return Response.ok(GameURL).build();
    }

    @Path("/finish")
    @POST
    @Consumes("application/x-www-form-urlencoded")
    public Response finish(@FormParam("json") String json) {
        final Gson gson = new Gson();
        //TODO

        log.info("Result");

        return Response.ok().build();
    }
}

