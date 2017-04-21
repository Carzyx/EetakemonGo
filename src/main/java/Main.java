import Controller.UserService;
import Dao.GenericDaoImpl;
import Dao.IGenericDao;
import Dao.MySQLRepository;
import Dao.UserDao;
import Model.User;
import com.mysql.jdbc.StringUtils;

import java.util.HashSet;
import java.util.List;

/**
 * Created by Miguel Angel on 04/04/2017.
 */
public class Main {

    public static void main(String [] args) throws IllegalAccessException {

        UserDao userDao = new UserDao(new GenericDaoImpl<User>());
        UserService service = new UserService(userDao);

        User user = new User(
                "mig'uel",
                "angel",
                "SuperUser",
                "miguel1234",
                "miguel@gmail.com"
        );
        User userA = new User(
                "test'A",
                "testApA",
                "SuperTestA",
                "testA1234",
                "testA@gmail.com"
        );
        User userB = new User(
                "test'B",
                "testApB",
                "SuperTestB",
                "testB1234",
                "testB@gmail.com"
        );

        boolean createUser = service.createUser("migu'el",
                "angel",
                "SuperUser",
                "miguel1234",
                "miguel@gmail.com");

        System.out.println("createUser : "+createUser);

        User userSignUp = service.signIn(user.username, user.password);
        boolean isUserSignIn = (userSignUp.username != null);
        System.out.println("signIn : "+isUserSignIn);

        service.createUser("test'A",
                "testApA",
                "SuperTestA",
                "testA1234",
                "testA@gmail.com");

        boolean isUserDeleted = service.deleteUser(userA.username, userA.password);
        System.out.println("isUserDeleted : "+isUserDeleted);
        System.out.print("");
        //PRUEBA EETAKEMON
    }
}