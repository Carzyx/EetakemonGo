package ApiRest.Filters;

import ApiRest.Filters.Interfaces.ISignatureControlService;
import ApiRest.Filters.CustomFilters.TokenAuthenticated;
import org.apache.log4j.Logger;

import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

/**
 * Created by Miguel Angel on 04/06/2017.
 */
//To check access before Http Request
@Provider
@TokenAuthenticated
public class SecurityFilter implements ContainerRequestFilter {

    private static ISignatureControlService _signatureControl;
    final static Logger logger = Logger.getLogger(SecurityFilter.class);

    public SecurityFilter()
    {
        _signatureControl = new SignatureControlService();
    }

    @Override
    public void filter(ContainerRequestContext ctx) throws IOException {

        String authHeader = ctx.getHeaderString(HttpHeaders.AUTHORIZATION);
        // Check if the HTTP Authorization header is present and formatted correctly
        if (authHeader == null || authHeader.isEmpty()) {
            logger.info("Authorization header must be provided by request: " + ctx.getRequest());
            throw new NotAuthorizedException("Authorization header must be provided");
        }

        boolean isValid = _signatureControl.isSignatureExpired(authHeader);
        if(!isValid)
        {
            ctx.abortWith(
                Response.status(Response.Status.UNAUTHORIZED).build());
        }
    }
}
