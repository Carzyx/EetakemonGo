package ApiRest.Helpers.Interfaces;

import ApiRest.Helpers.MyApplicationException;

import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;

/**
 * Created by Miguel Angel on 10/06/2017.
 */
public interface IHttpResponseHelper {
    Response getSuccessResponse(Object element, HttpHeaders httpHeaders) throws MyApplicationException;
    Response getSuccessResponse(Object element, String subjectKey) throws MyApplicationException;
    Response getSuccessResponse(Object element) throws MyApplicationException;
}
