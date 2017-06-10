package ApiRest;
import ApiRest.Filters.Interfaces.ISignatureControlService;
import ApiRest.Filters.SignatureControlService;
import Business.AtackService;
import Business.EetakemonService;
import Business.Interfaces.IAtackService;
import Business.Interfaces.IEetakemonService;
import Business.Interfaces.IUserService;
import Business.UserService;
import Model.Atack;
import Model.Eetakemon;
import Model.User;

import javax.inject.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Miguel Angel on 21/04/2017.
 */
@Path("/web")
@Singleton
public class JSONService {

    // User implementation
    private static final IUserService _serviceUser = new UserService();
    private static final ISignatureControlService _signatureControl = new SignatureControlService();

    @Path("createUser")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createUser(User user){
        if(_serviceUser.create(user))
        {
            return Response.status(201).entity(user).build();
        }
        return Response.status(200).entity("User created KO").build();
    }

    @Path("singIn")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response.ResponseBuilder singIn(User user){

        user = _serviceUser.signIn(user.getUsername(), user.getPassword());
        if(user.getUsername() != null)
        {
            Map<String, String> authToken = new HashMap<>();
            return Response.ok(user);
           //Response.ok(authToken);
            //return Response.status(201).entity(user).entity(authToken).build();
        }

        //return Response.status(200).entity("User created KO").build();
        return Response.ok();
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
        return _serviceUser.getCompleteUserByUsername(username);
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
        return _serviceEetakemon.getAll();
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
}

