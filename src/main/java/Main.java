import ApiRest.Filters.SignatureControlService;
import Model.User;

/**
 * Created by Miguel Angel on 04/04/2017.
 */
public class Main {

    public static void main(String [] args){

        User user = new User();
        user.setUsername("Miguel");

        SignatureControlService sc = new SignatureControlService();

        //String key = sc.getKeySignature(user.getUsername());

        //boolean ok = sc.isValidSignature(key);
        //boolean test = sc.isValidSignature(key, user.getUsername());

        System.out.println();


    }
}