package ApiRest.Api.Controller.Interfaces;

import ApiRest.Filters.CustomFilters.TokenAuthenticated;
import Model.User;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by Miguel Angel on 10/06/2017.
 */
public interface IUserApi {
    @Path("createUser")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    Response createUser(User user);

    @Path("singIn")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    Response singIn(User user);

    @Path("removeUserByUsernameAndPassword")
    @POST
    @TokenAuthenticated
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    Response removeUserByUsernameAndPassword(@Context HttpHeaders httpHeaders, User user);

    @Path("updateUser")
    @POST
    @TokenAuthenticated
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    Response updateUser(@Context HttpHeaders httpHeaders, User user);

    @Path("updateByUsernameAndPassword")
    @POST
    @TokenAuthenticated
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    Response updateByUsernameAndPassword(@Context HttpHeaders httpHeaders, User user);

    @Path("getUserByUsername/{username}")
    @GET
    @TokenAuthenticated
    @Produces(MediaType.APPLICATION_JSON)
    Response getUserByUsername(@Context HttpHeaders httpHeaders, @PathParam("username") String username);

    @Path("addAEetakemonsToUser")
    @POST
    @TokenAuthenticated
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    Response addAEetakemonsToUser(@Context HttpHeaders httpHeaders, User user);

    @Path("getCompleteUserByUsername/{username}")
    @GET
    @TokenAuthenticated
    @Produces(MediaType.APPLICATION_JSON)
    Response getCompleteUserByUsername(@Context HttpHeaders httpHeaders, @PathParam("username") String username);

    @Path("getAllUsers")
    @GET
    @TokenAuthenticated
    @Produces(MediaType.APPLICATION_JSON)
    Response getAllUsers(@Context HttpHeaders httpHeaders);
}
