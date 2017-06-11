package ApiRest.Api.Controller.Interfaces;

import Model.Eetakemon;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by Miguel Angel on 11/06/2017.
 */
public interface IEetakemonApi {
    @Path("createEetakemon")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    Response createEetakemon(@Context HttpHeaders httpHeaders, Eetakemon eetakemon);

    @Path("getAllEetakemons")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    Response getAllEetakemons(@Context HttpHeaders httpHeaders);
}
