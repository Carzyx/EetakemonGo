package ApiRest.Api.Controller.Interfaces;

import Model.Party;
import Model.User;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by Miguel Angel on 18/06/2017.
 */
public interface IGameApi {

    @Path("registerCandidate")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    Response registerCandidate(@Context HttpHeaders httpHeaders, User candidate);

    @Path("getParty")
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    Response getParty(@Context HttpHeaders httpHeaders, User candidate);

    @Path("doAtack")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    Response doAtack(@Context HttpHeaders httpHeaders, Party party);
}
