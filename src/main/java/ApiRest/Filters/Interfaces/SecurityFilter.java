package ApiRest.Filters.Interfaces;

import ApiRest.Filters.SignatureControlService;

import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

/**
 * Created by Miguel Angel on 04/06/2017.
 */
@Provider
@PreMatching
public class SecurityFilter implements ContainerRequestFilter {

    private static ISignatureControlService _signatureControl;
    public SecurityFilter()
    {
        _signatureControl = new SignatureControlService();
    }

    @Override
    public void filter(ContainerRequestContext ctx) throws IOException {

        String authHeader = ctx.getHeaderString(HttpHeaders.AUTHORIZATION);
        // Check if the HTTP Authorization header is present and formatted correctly
        if (authHeader == null || authHeader.isEmpty()) {
            throw new NotAuthorizedException("Authorization header must be provided");
        }

        boolean isValid = _signatureControl.isValidSignature(authHeader);
        if(!isValid)
        {
            ctx.abortWith(
                Response.status(Response.Status.UNAUTHORIZED).build());
        }
    }
}
