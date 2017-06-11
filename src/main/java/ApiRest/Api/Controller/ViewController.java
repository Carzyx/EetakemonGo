package ApiRest.Api.Controller;


import org.glassfish.jersey.server.mvc.Viewable;

import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

/**
 * Created by Miguel Angel on 11/06/2017.
 */
@Path("/ViewService")
@Singleton
public class ViewController {

    @GET
    @Path("index")
    public Viewable index() {
        return new Viewable("/Index");

    }

    @GET
    @Path("index2")
    public Viewable index2() {
        return new Viewable("Index");

    }

    @GET
    @Path("index4")
    public Viewable index4() {
        return new Viewable("Index.jsp");

    }

    @GET
    @Path("index3")
    public Viewable index3() {
        return new Viewable("/Index.jsp");

    }
}
