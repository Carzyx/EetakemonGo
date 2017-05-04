package ApiRest;
import Controller.EetakemonService;
import Controller.IEetakemonService;
import Controller.IUserService;
import Controller.UserService;
import Dao.AtackDao;
import Dao.EetakemonDao;
import Dao.GenericDaoImpl;
import Dao.UserDao;
import Model.Atack;
import Model.Eetakemon;
import Model.User;

import javax.inject.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
/**
 * Created by Ignacio on 21/04/2017.
 */
@Path("/web")
@Singleton
public class JSONService {

    // User implementation
    private IUserService _serviceUser = new UserService(new UserDao(new GenericDaoImpl<User>()));

    @Path("createUser")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response createUser(User user){
        if(_serviceUser.createUser(user))
        {
            return Response.status(201).entity("User created OK").build();
        }
        return Response.status(200).entity("User created KO").build();
    }

    //SignIn deberia de responder toda la info del usuario que necesitariamos en la cookie


    //Eetakemon implementation
    private IEetakemonService _serviceEetakemon = new EetakemonService(new EetakemonDao(new GenericDaoImpl<Eetakemon>(), new AtackDao(new GenericDaoImpl<Atack>())));


}

