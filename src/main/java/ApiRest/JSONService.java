package ApiRest;
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
    public JSONService(){
    }
    @GET
    @Path("/getPass/{name}")
    @Produces(MediaType.TEXT_PLAIN)
    public String getPass(@PathParam("name") String name){
        return null;
    }

    @POST
    @Path("/setNewUser")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String setNewUser(){
        return null;
    }
}

