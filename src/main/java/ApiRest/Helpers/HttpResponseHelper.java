package ApiRest.Helpers;

import ApiRest.Helpers.Interfaces.IHttpResponseHelper;
import ApiRest.Filters.Interfaces.ISignatureControlService;
import ApiRest.Filters.SignatureControlService;
import com.google.gson.Gson;
import javafx.util.Pair;

import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;

/**
 * Created by Miguel Angel on 10/06/2017.
 */
public class HttpResponseHelper implements IHttpResponseHelper {

    private static ISignatureControlService _signatureControlService;
    private static String errorMessage = "something error happens";

    public HttpResponseHelper()
    {
        _signatureControlService = new SignatureControlService();
    }

    public Response getSuccessResponse(Object element) throws MyApplicationException {
        try {
            return Response.status(200).entity(new Gson().toJson(element)).build();
        } catch (Exception ex) {
            throw new MyApplicationException(errorMessage, ex);
        }
    }

    public Response getSuccessResponse(Object element, HttpHeaders httpHeaders) throws MyApplicationException {
        try
        {
            String OldAuthToken = httpHeaders.getHeaderString(HttpHeaders.AUTHORIZATION);
            OldAuthToken = OldAuthToken == null ? httpHeaders.getHeaderString("authoritzation") : OldAuthToken;
            Pair<String, String> authToken = _signatureControlService.getExtendExpirationTime(OldAuthToken);
            return Response.status(200).header(authToken.getKey(), authToken.getValue()).entity(new Gson().toJson(element)).build();
        }
        catch (Exception ex)
        {
            throw new MyApplicationException(errorMessage, ex);
        }
    }

    public Response getSuccessResponse(Object element, String subjectKey) throws MyApplicationException {
        try
        {
            Pair<String, String> authToken = _signatureControlService.getKeySignature(subjectKey);
            return Response.status(200).header(authToken.getKey(), authToken.getValue()).entity(new Gson().toJson(element)).build();
        }
        catch (Exception ex)
        {
            throw new MyApplicationException(errorMessage, ex);
        }
    }
}
