package ApiRest.Helpers;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * Created by Miguel Angel on 11/06/2017.
 */
@Provider
public class MyApplicationExceptionHandler implements ExceptionMapper<MyApplicationException> {
    @Override
    public Response toResponse(MyApplicationException exception) {
        return Response.status(Response.Status.BAD_REQUEST).entity(exception.getMessage()).build();
    }
}
