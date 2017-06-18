package ApiRest.Api.Controller;

import ApiRest.Api.Controller.Interfaces.IGameApi;
import ApiRest.Filters.CustomFilters.TokenAuthenticated;
import ApiRest.Helpers.HttpResponseHelper;
import ApiRest.Helpers.Interfaces.IHttpResponseHelper;
import Business.Interfaces.IPartyService;
import Business.Interfaces.PartyService;
import Model.Party;
import Model.User;

import javax.inject.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by Miguel Angel on 18/06/2017.
 */
@Path("/GameService")
@TokenAuthenticated
@Singleton
public class GameApi implements IGameApi {
    //Party implementation
    private IPartyService _serviceParty = new PartyService();
    private static final IHttpResponseHelper _httpResponseHelper = new HttpResponseHelper();


    @Path("registerCandidate")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response registerCandidate(@Context HttpHeaders httpHeaders, User candidate) {
        return _httpResponseHelper.getSuccessResponse(_serviceParty.registerCandidate(candidate), httpHeaders);
    }

    @Path("getParty")
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getParty(@Context HttpHeaders httpHeaders, User candidate) {
        return _httpResponseHelper.getSuccessResponse(_serviceParty.getParty(candidate), httpHeaders);
    }

    @Path("doAtack")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response doAtack(@Context HttpHeaders httpHeaders, Party party) {
        return _httpResponseHelper.getSuccessResponse(_serviceParty.doAtack(party), httpHeaders);
    }
}
