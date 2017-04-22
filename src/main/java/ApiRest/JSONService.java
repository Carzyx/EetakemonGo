package ApiRest;
import Controller.IUserService;
import Controller.UserService;
import Dao.GenericDaoImpl;
import Dao.UserDao;
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
    private UserDao userDao = new UserDao(new GenericDaoImpl<User>());
    private UserService service = new UserService(userDao);
    public JSONService(){
    }
    @GET
    @Path("/getPass/{name}/{pass}")
    @Produces(MediaType.TEXT_PLAIN)
    public String getPass(@PathParam("name") String name,@PathParam("pass") String pass){
        service.signIn(name,pass);
        return null;
    }
/*
    @GET
    @Path("/setNewUser/{name}/{pass}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String setNewUser(@PathParam("name") String name,@PathParam("pass") String pass){
        return service.createUser(name,pass);
    }*/
}

