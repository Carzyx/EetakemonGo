package ApiRest.Api.Controller;

import ApiRest.Api.Controller.Interfaces.IAtackApi;
import ApiRest.Enum.ActionCode;
import ApiRest.Filters.CustomFilters.TokenAuthenticated;
import ApiRest.Helpers.HttpResponseHelper;
import ApiRest.Helpers.Interfaces.IHttpResponseHelper;
import Business.AtackService;
import Business.Interfaces.IAtackService;
import Model.Atack;

import javax.inject.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by Miguel Angel on 10/06/2017.
 */

@Path("/AtackService")
@TokenAuthenticated
@Singleton
public class AtackApi implements IAtackApi {

    //Atack implementation
    private IAtackService _serviceAtack = new AtackService();
    private static final IHttpResponseHelper _httpResponseHelper = new HttpResponseHelper();


    @Override
    @Path("createAtack")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response createAtack(Atack atack) {
        if (_serviceAtack.create(atack)) {
            _httpResponseHelper.getSuccessResponse(ActionCode.OK);
        }
        return _httpResponseHelper.getSuccessResponse(ActionCode.KO);
    }

    @Override
    @Path("updateAtack")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateAtack(@Context HttpHeaders httpHeaders, Atack atack) {
        if (_serviceAtack.updateByName(atack)) {
            return _httpResponseHelper.getSuccessResponse(ActionCode.OK, httpHeaders);
        }
        return _httpResponseHelper.getSuccessResponse(ActionCode.KO, httpHeaders);
    }

    @Override
    @Path("removeAtack")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response removeAtack(@Context HttpHeaders httpHeaders, Atack atack) {
        if (_serviceAtack.removeByName(atack)) {
            return _httpResponseHelper.getSuccessResponse(ActionCode.OK, httpHeaders);
        }
        return _httpResponseHelper.getSuccessResponse(ActionCode.KO, httpHeaders);
    }

    @Override
    @Path("getAllAtacks")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllAtacks(@Context HttpHeaders httpHeaders) {

        return _httpResponseHelper.getSuccessResponse(_serviceAtack.getAll(), httpHeaders);
    }
}
