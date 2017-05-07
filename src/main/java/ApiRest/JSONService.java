package ApiRest;
import Controller.AtackService;
import Controller.EetakemonService;
import Controller.Interfaces.IAtackService;
import Controller.Interfaces.IEetakemonService;
import Controller.Interfaces.IUserService;
import Controller.UserService;
import Model.Atack;
import Model.Eetakemon;
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

    @Path("getUserById/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public User getUserById(@PathParam("id") int id) {
        return _serviceUser.getById(id);
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

    @Path("getCompleteUserById/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public User getCompleteUserById(@PathParam("id") int id) {
        return _serviceUser.getCompleteUserById(id);
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

    @Path("getById/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Atack getById(@PathParam("id") int id) {

        return _serviceAtack.getById(id);
    }

    @Path("getAllAtacks")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Atack> getAllAtacks(){

        return _serviceAtack.getAll();

    }


}

