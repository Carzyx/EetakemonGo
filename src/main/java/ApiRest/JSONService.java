package ApiRest;
import Controller.Interfaces.IUserService;
import Controller.UserService;
import Model.User;

import javax.inject.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

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

    //SignIn deberia de responder toda la info del usuario que necesitariamos en la cookie


    //Eetakemon implementation


}

