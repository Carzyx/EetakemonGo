package Controller;

import Model.User;

/**
 * Created by histo on 07/03/2017.
 */
public interface IUserService {
    boolean createUser(String name, String surname, String username, String password, String email,int rol);

    User signIn(String username, String password);

    boolean deleteUser(String username, String password);


    /*

    boolean signIn(String username, String password);
    */
}
