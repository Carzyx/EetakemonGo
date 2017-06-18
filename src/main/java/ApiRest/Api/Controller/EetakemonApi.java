package ApiRest.Api.Controller;

import ApiRest.Api.Controller.Interfaces.IEetakemonApi;
import ApiRest.Enum.ActionCode;
import ApiRest.Filters.CustomFilters.TokenAuthenticated;
import ApiRest.Helpers.HttpResponseHelper;
import ApiRest.Helpers.Interfaces.IHttpResponseHelper;
import Business.EetakemonService;
import Business.Interfaces.IEetakemonService;
import Model.Eetakemon;

import javax.inject.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by Miguel Angel on 10/06/2017.
 */

@Path("/EetakemonService")
@TokenAuthenticated
@Singleton
public class EetakemonApi implements IEetakemonApi {

    //Eetakemon implementation
    private IEetakemonService _serviceEetakemon = new EetakemonService();
    private static final IHttpResponseHelper _httpResponseHelper = new HttpResponseHelper();


    @Override
    @Path("createEetakemon")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response createEetakemon(@Context HttpHeaders httpHeaders, Eetakemon eetakemon) {
        if (_serviceEetakemon.create(eetakemon)) {
            return _httpResponseHelper.getSuccessResponse(ActionCode.OK, httpHeaders);
        }
        return _httpResponseHelper.getSuccessResponse(ActionCode.KO, httpHeaders);
    }

    @Override
    @Path("getAllEetakemons")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllEetakemons(@Context HttpHeaders httpHeaders) {
        return _httpResponseHelper.getSuccessResponse(_serviceEetakemon.getAll(), httpHeaders);
    }

}
