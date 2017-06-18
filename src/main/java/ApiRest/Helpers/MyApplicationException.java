package ApiRest.Helpers;

import java.io.Serializable;

/**
 * Created by Miguel Angel on 11/06/2017.
 */
public class MyApplicationException extends Error implements Serializable {
    //TODO - Principalmente extendia de Exception, pero esta obliga a implementarla en los metodos Rest, esta ok Error?
    private static final long serialVersionUID = 1L;

    public MyApplicationException() {
        super();
    }

    public MyApplicationException(String msg) {
        super(msg);
    }

    public MyApplicationException(String msg, Exception e) {
        super(msg, e);
    }
}