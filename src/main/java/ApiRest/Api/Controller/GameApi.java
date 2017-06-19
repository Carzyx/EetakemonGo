package ApiRest.Api.Controller;

import ApiRest.Api.Controller.Interfaces.IGameApi;
import ApiRest.Filters.CustomFilters.TokenAuthenticated;
import ApiRest.Helpers.HttpResponseHelper;
import ApiRest.Helpers.Interfaces.IHttpResponseHelper;
import Business.Interfaces.IPartyService;
import Business.PartyService;
import Model.Party;
import Model.User;
import com.google.gson.Gson;

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
    private IPartyService _serviceParty = PartyService.getInstance();
    private static final IHttpResponseHelper _httpResponseHelper = new HttpResponseHelper();


    @Path("registerCandidate")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response registerCandidate(@Context HttpHeaders httpHeaders, User candidate) {
        return _httpResponseHelper.getSuccessResponse(_serviceParty.registerCandidate(candidate), httpHeaders);
    }

    @Path("getParty")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getParty(@Context HttpHeaders httpHeaders, User candidate) {
        return _httpResponseHelper.getSuccessResponse(_serviceParty.getParty(candidate), httpHeaders);
    }

    @Path("doAtack")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response doAtack(@Context HttpHeaders httpHeaders, String jsonParty) {
        Party party = new Gson().fromJson(jsonParty, Party.class);
        return _httpResponseHelper.getSuccessResponse(_serviceParty.doAtack(party), httpHeaders);
    }

    @Path("getAllRegisters")
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllRegisters(@Context HttpHeaders httpHeaders) {
        return _httpResponseHelper.getSuccessResponse(_serviceParty.getAllRegisters(), httpHeaders);
    }

    @Path("getAllRegistersByUser/{username}")
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllRegistersByUser(@Context HttpHeaders httpHeaders, @PathParam("username")String username) {
        return _httpResponseHelper.getSuccessResponse(_serviceParty.getAllRegistersByUser(username), httpHeaders);
    }
}
