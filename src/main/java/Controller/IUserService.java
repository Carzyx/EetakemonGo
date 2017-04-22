package Controller;

import Model.User;

/**
 * Created by histo on 07/03/2017.
 */
public interface IUserService {
    boolean createUser(User user);

    User signIn(String username, String password);

    boolean deleteUser(String username, String password);


    /*

    boolean signIn(String username, String password);
    */
}
