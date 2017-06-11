package ApiRest.Api.Controller.Interfaces;

import Model.Atack;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by Miguel Angel on 11/06/2017.
 */
public interface IAtackApi {
    @Path("createAtack")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    Response createAtack(Atack atack);

    @Path("updateAtack")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    Response updateAtack(@Context HttpHeaders httpHeaders, Atack atack);

    @Path("removeAtack")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    Response removeAtack(@Context HttpHeaders httpHeaders, Atack atack);

    @Path("private/getAllAtacks")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    Response getAllAtacks(@Context HttpHeaders httpHeaders);
}
