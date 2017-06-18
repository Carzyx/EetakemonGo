package ApiRest.Api.Controller;

import ApiRest.Enum.ActionCode;
import ApiRest.Helpers.HttpResponseHelper;
import ApiRest.Helpers.Interfaces.IHttpResponseHelper;
import ApiRest.Api.Controller.Interfaces.IUserApi;
import ApiRest.Filters.CustomFilters.TokenAuthenticated;
import Business.Interfaces.IUserService;
import Business.UserService;
import Model.User;

import javax.inject.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by Miguel Angel on 10/06/2017.
 */

@Path("/UserService")
@Singleton
public class UserApi implements IUserApi {

    // User implementation
    private static final IUserService _serviceUser = new UserService();
    private static final IHttpResponseHelper _httpResponseHelper = new HttpResponseHelper();

    @Override
    @Path("createUser")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createUser(User user){
        if(_serviceUser.create(user))
        {
            return _httpResponseHelper.getSuccessResponse(ActionCode.OK);
        }
        return _httpResponseHelper.getSuccessResponse(ActionCode.KO);
    }

    @Override
    @Path("singIn")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response singIn(User user){

        user = _serviceUser.signIn(user.getUsername(), user.getPassword());
        if(user.getUsername() != null)
        {
            return _httpResponseHelper.getSuccessResponse(user, user.getUsername());
        }
        return _httpResponseHelper.getSuccessResponse(ActionCode.KO);
    }


    @Override
    @Path("removeUserByUsernameAndPassword")
    @POST
    @TokenAuthenticated
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response removeUserByUsernameAndPassword(@Context HttpHeaders httpHeaders, User user){
        if(_serviceUser.removeByUsernameAndPassword(user))
        {
            return _httpResponseHelper.getSuccessResponse(ActionCode.OK, httpHeaders);
        }
        return _httpResponseHelper.getSuccessResponse(ActionCode.KO, httpHeaders);
    }

    @Override
    @Path("updateUser")
    @POST
    @TokenAuthenticated
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response updateUser(@Context HttpHeaders httpHeaders, User user){
        if(_serviceUser.updateByName(user))
        {
            return _httpResponseHelper.getSuccessResponse(ActionCode.OK, httpHeaders);
        }
        return _httpResponseHelper.getSuccessResponse(ActionCode.KO, httpHeaders);
    }

    @Override
    @Path("updateByUsernameAndPassword")
    @POST
    @TokenAuthenticated
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response updateByUsernameAndPassword(@Context HttpHeaders httpHeaders, User user){
        if(_serviceUser.updateByUsernameAndPassword(user))
        {
            return _httpResponseHelper.getSuccessResponse(ActionCode.OK, httpHeaders);
        }
        return _httpResponseHelper.getSuccessResponse(ActionCode.KO, httpHeaders);
    }

    @Override
    @Path("getUserByUsername/{username}")
    @GET
    @TokenAuthenticated
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserByUsername(@Context HttpHeaders httpHeaders, @PathParam("username") String username) {
        User user = _serviceUser.getByName(username);
        return _httpResponseHelper.getSuccessResponse(user, httpHeaders);
    }


    @Override
    @Path("addAEetakemonsToUser")
    @POST
    @TokenAuthenticated
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addAEetakemonsToUser(@Context HttpHeaders httpHeaders, User user) {

        if (_serviceUser.addAEetakemonsToUser(user)) {
            return _httpResponseHelper.getSuccessResponse(ActionCode.OK, httpHeaders);
        }
        return _httpResponseHelper.getSuccessResponse(ActionCode.KO, httpHeaders);
    }

    @Override
    @Path("getCompleteUserByNme/{name}")
    @GET
    @TokenAuthenticated
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCompleteUserByUsername(@Context HttpHeaders httpHeaders, @PathParam("name") String username) {
        return _httpResponseHelper.getSuccessResponse(_serviceUser.getCompleteUserByUsername(username), httpHeaders);
    }

    @Override
    @Path("getAllUsers")
    @GET
    @TokenAuthenticated
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllUsers(@Context HttpHeaders httpHeaders)
    {
        return  _httpResponseHelper.getSuccessResponse(_serviceUser.getAll(), httpHeaders);
    }
}
