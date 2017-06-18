package ApiRest.Filters.Interfaces;

import javafx.util.Pair;

/**
 * Created by Miguel Angel on 04/06/2017.
 */
public interface ISignatureControlService {

    boolean isValidSignature(String key, String subjectKey);

    boolean isSignatureExpired(String key);

    Pair<String, String> getKeySignature(String subjectKey);

    Pair<String, String> getExtendExpirationTime(String key);
}
