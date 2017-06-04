package ApiRest.Filters.Interfaces;

/**
 * Created by Miguel Angel on 04/06/2017.
 */
public interface ISignatureControlService {

    String getKeySignature(String username);
    boolean isValidSignature(String key, String username);
    boolean isValidSignature(String key);
    String extendExpirationTime(String key);
}
