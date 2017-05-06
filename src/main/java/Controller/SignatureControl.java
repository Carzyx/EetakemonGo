package Controller;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.impl.crypto.MacProvider;
import java.security.Key;

/**
 * Created by Miguel Angel on 03/05/2017.
 */
public class SignatureControl {

    public String getKeySignature(String username)
    {
        Key key = MacProvider.generateKey();

        String compactJws = Jwts.builder()
            .setSubject(username)
            .signWith(SignatureAlgorithm.HS512, key)
            .compact();

        return compactJws;
    }

    public boolean validateSignature(String key, String username)
    {
        String compactJws =  getKeySignature(username);

        try {
            Jwts.parser().setSigningKey(key).parseClaimsJws(compactJws);
            return true;

            //OK, we can trust this JWT

        } catch (SignatureException e) {

            //don't trust the JWT!
        }
        return false;
    }
}
