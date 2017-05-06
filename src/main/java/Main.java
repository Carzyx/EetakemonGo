import Controller.EetakemonService;
import Controller.UserService;
import Dao.*;
import Dao.Entity.UserInfo;
import Model.*;
import com.mysql.jdbc.StringUtils;

import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;

/**
 * Created by Miguel Angel on 04/04/2017.
 */
public class Main {

    public static void main(String [] args){

        UserDao daoService = new UserDao(new GenericDaoImpl<UserInfo>());
        User user = new User("miguel","angel","usercito","1234","test@gmail.test", 1, "imagencita");

    }
}