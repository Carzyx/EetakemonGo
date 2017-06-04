package ApiRest.Filters;
import ApiRest.Filters.Interfaces.ISignatureControlService;
import io.jsonwebtoken.*;
import org.apache.log4j.Logger;

import java.util.Date;

/**
 * Created by Miguel Angel on 03/05/2017.
 */
public class SignatureControlService implements ISignatureControlService{

    private static String secretKey = "eetakemonGoSecretKey";
    final static Logger logger = Logger.getLogger(SignatureControlService.class);
    final static int TTL = 15000;

    public String getKeySignature(String username)
    {
        Date dateNow = new Date(System.currentTimeMillis());
        Date dateExpiration = new Date(System.currentTimeMillis()+TTL);

        String compactJws = Jwts.builder()
            .setSubject(username)
            .setIssuedAt(dateNow)
            .setExpiration(dateExpiration)
            .signWith(SignatureAlgorithm.HS512, secretKey)
            .compact();
        return compactJws;
    }

    public boolean isValidSignature(String key, String username)
    {
        try {
            boolean isSubjectValid = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(key).getBody().getSubject().equals(username);

            //OK, we can trust this JWT
            return isSubjectValid;
        }
        catch (Exception ex) {

            //don't trust the JWT!
            logger.error("Error en la validacion del token: " + ex.getMessage());
            return false;
        }
    }

    public boolean isValidSignature(String key)
    {
        try {
            boolean isTest = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(key).getBody().getExpiration().before(new Date(System.currentTimeMillis()));
            //boolean isSubjectValid = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(key).getBody().getSubject().equals(username);

            //OK, we can trust this JWT
            return isTest;
        }
        catch (Exception ex) {

            //don't trust the JWT!
            logger.error("Error en la validacion del token: " + ex.getMessage());
            return false;
        }
    }

    public String extendExpirationTime(String key)
    {
        Date dateExpiration = new Date(System.currentTimeMillis()+TTL);

        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(key).getBody().setExpiration(dateExpiration).getSubject();
    }
}
