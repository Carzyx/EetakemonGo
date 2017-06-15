package ApiRest;

import Business.AtackService;
import Business.EetakemonService;
import Business.Interfaces.IAtackService;
import Business.Interfaces.IEetakemonService;
import Business.Interfaces.ILocation;
import Business.Interfaces.IUserService;
import Business.LocationService;
import Business.UserService;
import Model.Atack;
import Model.Eetakemon;
import Model.Markers;
import Model.User;

import javax.inject.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created by Ignacio on 21/04/2017.
 */
@Path("/web")
@Singleton
public class JSONService {

    // User implementation
    private IUserService _serviceUser = new UserService();

    @Path("createUser")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response createUser(User user){
        if(_serviceUser.create(user))
        {
            return Response.status(201).entity("User created OK").build();
        }
        return Response.status(200).entity("User created KO").build();
    }
    @Path("LogIn")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response logIn(User user){
        if(_serviceUser.getByName(user.getName()).getPassword().equals(user.getPassword()))
        {
            user=_serviceUser.getByName(user.getName());
            return Response.status(200).entity(_serviceUser.getCompleteUserByName(user.getName())).build();
        }
        return Response.status(201).entity(null).build();
    }


    @Path("removeUserByUsernameAndPassword")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response removeUserByUsernameAndPassword(User user){
        if(_serviceUser.removeByUsernameAndPassword(user))
        {
            return Response.status(201).entity("User removed OK").build();
        }
        return Response.status(200).entity("User removed KO").build();
    }

    @Path("updateUser")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response updateUser(User user){
        if(_serviceUser.updateByName(user))
        {
            return Response.status(201).entity("User updated OK").build();
        }
        return Response.status(200).entity("User updated KO").build();
    }

    @Path("updateByUsernameAndPassword")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response updateByUsernameAndPassword(User user){
        if(_serviceUser.updateByUsernameAndPassword(user))
        {
            return Response.status(201).entity("User updated OK").build();
        }
        return Response.status(200).entity("User updated KO").build();
    }

    @Path("getUserByUsername/{username}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public User getUserByUsername(@PathParam("username") String username) {
        return _serviceUser.getByName(username);
    }

    @Path("addAEetakemonsToUser")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
        public Response addAEetakemonsToUser(User user) {

        if (_serviceUser.addAEetakemonsToUser(user)) {
            return Response.status(201).entity("Eetakemons added OK").build();
        }
        return Response.status(200).entity("Eetakemons added KO").build();
    }

    @Path("getCompleteUserByUsername/{username}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public User getCompleteUserByUsername(@PathParam("username") String username) {
        return _serviceUser.getCompleteUserByName(username);
    }

    @Path("getAllUsers")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> getAllUsers()
    {
        return _serviceUser.getAll();
    }





    //SignIn deberia de responder toda la info del usuario que necesitariamos en la cookie


    //Eetakemon implementation
    private IEetakemonService _serviceEetakemon = new EetakemonService();

    @Path("createEetakemon")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response createEetakemon(Eetakemon eetakemon){
        if(_serviceEetakemon.create(eetakemon))
        {
            return Response.status(201).entity("Eetakemon created OK").build();
        }
        return Response.status(200).entity("Eetakemon created KO").build();
    }

    @Path("getAllEetakemons")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Eetakemon> getAllEetakemons() {
        List<Eetakemon> list=_serviceEetakemon.getAll();
        return list;
    }







    //Atack implementation
    private IAtackService _serviceAtack = new AtackService();

    @Path("createAtack")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response createAtack(Atack atack){
        if(_serviceAtack.create(atack))
        {
            return Response.status(201).entity("Atack created OK").build();
        }
        return Response.status(200).entity("Atack created KO").build();
    }

    @Path("updateAtack")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response updateAtack(Atack atack){
        if(_serviceAtack.updateByName(atack))
        {
            return Response.status(201).entity("Atack updated OK").build();
        }
        return Response.status(200).entity("Atack updated KO").build();
    }

    @Path("removeAtack")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response removeAtack(Atack atack){
        if(_serviceAtack.removeByName(atack))
        {
            return Response.status(201).entity("Atack removed OK").build();
        }
        return Response.status(200).entity("Atack removed KO").build();
    }

    @Path("getAllAtacks")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Atack> getAllAtacks(){

        return _serviceAtack.getAll();

    }

    //LocationService implementation
    private ILocation _serviceLocation=new LocationService();
    @Path("markers")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public List<Markers> getMarkers(Markers markers){
        return _serviceLocation.getMarkers(markers);
    }

    @Path("nearMarkers")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public List<Markers> getNearMarkers(Markers markers){
        return _serviceLocation.getNearMarkers(markers);
    }


}

