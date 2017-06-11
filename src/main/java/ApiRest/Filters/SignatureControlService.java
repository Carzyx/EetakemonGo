package ApiRest.Filters;
import ApiRest.Filters.Interfaces.ISignatureControlService;
import io.jsonwebtoken.*;
import javafx.util.Pair;
import org.apache.log4j.Logger;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Miguel Angel on 03/05/2017.
 */
public class SignatureControlService implements ISignatureControlService{

    private static String secretKey = "eetakemonGoSecretKey";
    private final static Logger logger = Logger.getLogger(SignatureControlService.class);
    private final static int TTL = 900000; //15 min in ms
    private static String authoritzation = "Authoritzation";

    public boolean isValidSignature(String key, String subjectKey) {
        try {
            boolean isSubjectValid = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(key).getBody().getSubject().equals(subjectKey);

            //OK, we can trust this JWT
            return isSubjectValid;
        }
        catch (Exception ex) {

            //don't trust the JWT!
            logger.error("Error en la validacion del token: " + ex.getMessage());
            return false;
        }
    }

    public boolean isSignatureExpired(String key) {
        try {
            boolean isNotExpired = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(key).getBody().getExpiration().after(new Date(System.currentTimeMillis()));
            //boolean isSubjectValid = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(key).getBody().getSubject().equals(username);

            //OK, we can trust this JWT
            return isNotExpired;
        }
        catch (Exception ex) {

            //don't trust the JWT!
            logger.error("Error en la validacion del token: " + ex.getMessage());
            return false;
        }
    }

    public Pair<String, String> getKeySignature (String subjectKey) {
        return new Pair<>(authoritzation, createKeySignature(subjectKey));
    }

    public Pair<String, String> getExtendExpirationTime (String key) {
       return new Pair<>(authoritzation, extendExpirationTime(key));
    }

    private String extendExpirationTime(String key) {
        String subjectKey = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(key).getBody().getSubject();
        return createKeySignature(subjectKey);
    }

    private String createKeySignature(String subjectKey) {
        Date dateNow = new Date(System.currentTimeMillis());
        Date dateExpiration = new Date(System.currentTimeMillis()+TTL);

        String compactJws = Jwts.builder()
            .setSubject(subjectKey)
            .setIssuedAt(dateNow)
            .setExpiration(dateExpiration)
            .signWith(SignatureAlgorithm.HS512, secretKey)
            .compact();
        return compactJws;
    }

}
