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
        /*boolean createUser = service.createUser("Prueba",
                "angel",
                "MyUser",
                "miguel1234",
                "miguel@gmail.com",
                1);*/
        User user =new User("Prueba","sadsadas","MyUser","miguel1234","miguel1234",1);
        User userSignUp = service.signIn(user.getUsername(), user.getPassword());
        boolean isUserSignIn = (userSignUp.getUsername() != null);
        System.out.println("signIn : "+isUserSignIn);
        service.createUser("test'A",
                "testApA",
                "SuperTestA",
                "testA1234",
                "testA@gmail.com",
                1);

        boolean isUserDeleted = service.deleteUser("SuperTestA", "testA1234");
        System.out.println("isUserDeleted : "+isUserDeleted);
        System.out.print("");

    }
}